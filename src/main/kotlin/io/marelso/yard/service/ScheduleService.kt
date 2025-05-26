package io.marelso.yard.service

import io.marelso.yard.domain.Device
import io.marelso.yard.domain.Schedule
import io.marelso.yard.domain.dto.CreateScheduleDTO
import io.marelso.yard.domain.factory.ScheduleFactory
import io.marelso.yard.repository.ScheduleRepository
import org.springframework.context.annotation.Lazy
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class ScheduleService(
    private val repository: ScheduleRepository,
    @Lazy private val deviceService: DeviceService,
    private val factory: ScheduleFactory
) {
    private val listenerService = ListenerService<Schedule>()

    fun create(reference: String, dto: CreateScheduleDTO): Schedule {
        val device: Device = deviceService.findByReference(reference)
        val schedule = repository.save(factory.from(dto))

        deviceService.update(device.copy(
            scheduleIds = device.scheduleIds.toMutableList().apply {
                add(schedule.id.orEmpty())
            }
        ))

        return schedule.apply {
            listenerService.notifyListeners(device.reference.orEmpty(), this)
        }
    }

    fun delete(id: String) = findById(id).apply {
        val device = deviceService.findByScheduleId(id)
        deviceService.update(device.copy(
            scheduleIds = device.scheduleIds.toMutableList().apply {
                remove(id)
            }
        ))
        repository.deleteById(id)

        listenerService.notifyListeners(device.reference.orEmpty(), this)
    }

    private fun findById(id: String) = repository.findByIdOrNull(id) ?: throw RuntimeException("Schedule with id $id not found")

    fun subscribe(reference: String): SseEmitter = listenerService.subscribe(reference)

    fun list(reference: String): List<Schedule> = deviceService.findByReference(reference).scheduleIds.map(::findById)
}