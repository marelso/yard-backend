package io.marelso.yard.domain.dto

import io.marelso.yard.domain.Schedule

data class DeviceDTO(
    val reference: String? = null,
    val name: String? = null,
    val schedules: List<Schedule> = listOf()
)

data class DeviceCreateDTO(
    val reference: String? = null,
    val name: String? = null,
    val schedules: List<CreateScheduleDTO> = listOf()
)
