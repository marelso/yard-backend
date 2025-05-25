package io.marelso.yard.domain

import java.time.OffsetDateTime

data class Schedule(
    val id: String? = null,
    val type: String? = null,
    val amount: Int? = null,
    val date: OffsetDateTime? = null,
)
