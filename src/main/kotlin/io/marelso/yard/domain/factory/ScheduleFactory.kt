package io.marelso.yard.domain.factory

import io.marelso.yard.domain.Schedule
import io.marelso.yard.domain.dto.CreateScheduleDTO
import org.springframework.stereotype.Component

@Component
class ScheduleFactory {
    fun from(dto: CreateScheduleDTO) = Schedule(
        type = dto.type,
        amount = dto.amount,
        date = dto.date.toString(),
    )
}