package io.marelso.yard.domain.dto

import io.marelso.yard.domain.Schedule

data class DeviceDTO(
    val reference: String? = null,
    val name: String? = null,
    val schedules: List<Schedule> = listOf()
)
