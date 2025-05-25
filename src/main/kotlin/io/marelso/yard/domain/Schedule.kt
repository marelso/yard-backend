package io.marelso.yard.domain

import java.time.OffsetDateTime

data class Schedule(
    val id: String? = null,
    val type: ScheduleType? = null,
    val amount: Int? = null,
    val date: OffsetDateTime? = null,
)
