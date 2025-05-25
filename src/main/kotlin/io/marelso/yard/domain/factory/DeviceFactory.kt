package io.marelso.yard.domain.factory

import io.marelso.yard.domain.Device
import io.marelso.yard.domain.dto.DeviceDTO

class DeviceFactory {
    fun from(device: Device): DeviceDTO = DeviceDTO(
        reference = device.reference,
        name = device.name,
        schedules = device.schedules
    )

    fun from(dto: DeviceDTO): Device = Device(
        reference = dto.reference,
        name = dto.name,
        schedules = dto.schedules
    )
}