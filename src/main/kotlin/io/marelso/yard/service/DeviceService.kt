package io.marelso.yard.service

import io.marelso.yard.domain.Device
import io.marelso.yard.domain.dto.DeviceCreateDTO
import io.marelso.yard.domain.dto.DeviceDTO
import io.marelso.yard.domain.factory.DeviceFactory
import io.marelso.yard.repository.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val scheduleService: ScheduleService
) {
    private val factory: DeviceFactory = DeviceFactory()

    fun create(dto: DeviceCreateDTO): DeviceDTO {
        val schedules = dto.schedules.map(scheduleService::create)
        val device = deviceRepository.save(factory.from(dto, schedules))

        return factory.from(device)
    }

    fun delete(reference: String) = findByReference(reference).id?.let(deviceRepository::deleteById)

    private fun findByReference(reference: String): Device {
        return deviceRepository.findByReference(reference) ?: throw RuntimeException("Device with id $reference not found")
    }

    fun findByScheduleId(scheduleId: String): Device {
        return deviceRepository.findDeviceBySchedulesId(scheduleId) ?: throw RuntimeException("Device with id $scheduleId not found")
    }
}