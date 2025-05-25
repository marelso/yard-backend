package io.marelso.yard.service

import io.marelso.yard.domain.Schedule
import io.marelso.yard.domain.dto.CreateScheduleDTO
import io.marelso.yard.domain.factory.ScheduleFactory
import io.marelso.yard.repository.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val repository: ScheduleRepository,
    private val factory: ScheduleFactory
) {
    fun create(dto: CreateScheduleDTO): Schedule = repository.save(factory.from(dto))

    fun delete(id: String) = repository.deleteById(id)
}