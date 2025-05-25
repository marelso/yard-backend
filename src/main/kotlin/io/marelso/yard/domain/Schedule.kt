package io.marelso.yard.domain

data class Schedule(
    val id: String? = null,
    val type: ScheduleType? = null,
    val amount: Int? = null,
    val date: String? = null,
)
