package io.marelso.yard.domain.dto

import io.marelso.yard.domain.ScheduleType
import java.time.OffsetDateTime

data class CreateScheduleDTO(
    val type: ScheduleType? = null,
    val amount: Int? = null,
    val date: OffsetDateTime? = null
)
