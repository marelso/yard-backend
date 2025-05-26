package io.marelso.yard.domain.factory

import io.marelso.yard.domain.Device
import io.marelso.yard.domain.Schedule
import io.marelso.yard.domain.dto.DeviceCreateDTO
import io.marelso.yard.domain.dto.DeviceDTO

class DeviceFactory {
    fun from(device: Device, schedules: List<Schedule> = listOf()): DeviceDTO = DeviceDTO(
        reference = device.reference,
        name = device.name,
        schedules = schedules
    )

    fun from(dto: DeviceCreateDTO, schedules: List<String> = listOf()): Device = Device(
        reference = dto.reference,
        name = dto.name,
        scheduleIds = schedules
    )

    fun from(dto: DeviceDTO, schedules: List<Schedule>): Device = Device(
        reference = dto.reference,
        name = dto.name,
        scheduleIds = schedules.map { it.id.orEmpty() }.filter { it.isNotBlank() }
    )
}