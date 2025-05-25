package io.marelso.yard.service

import io.marelso.yard.domain.Schedule
import io.marelso.yard.domain.dto.CreateScheduleDTO
import io.marelso.yard.domain.factory.ScheduleFactory
import io.marelso.yard.repository.ScheduleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class ScheduleService(
    private val repository: ScheduleRepository,
    private val factory: ScheduleFactory
) {
    private val emitter: MutableList<SseEmitter> = mutableListOf()

    fun create(dto: CreateScheduleDTO): Schedule = repository.save(factory.from(dto)).apply {
        emitter.forEach { client ->
            notifyClient(client, this)
        }
    }

    private fun notifyClient(client: SseEmitter, schedule: Schedule) {
        try {
            client.send(schedule)
        } catch (exception: Exception) {
            client.complete()
            emitter.remove(client)
        }
    }

    fun delete(id: String) = findById(id).apply {
        repository.deleteById(id)

        emitter.forEach { client ->
            notifyClient(client, this)
        }
    }

    private fun findById(id: String) = repository.findByIdOrNull(id) ?: throw RuntimeException("Schedule with id $id not found")

    fun subscribe(): SseEmitter = SseEmitter().apply {
        emitter.add(this)

        onCompletion { emitter.remove(this) }
        onTimeout { emitter.remove(this) }
    }
}