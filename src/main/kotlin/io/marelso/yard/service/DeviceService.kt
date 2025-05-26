package io.marelso.yard.service

import io.marelso.yard.domain.Device
import io.marelso.yard.domain.Schedule
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
        val device = deviceRepository.save(factory.from(dto))

        return if (dto.schedules.isNotEmpty() && device.reference?.isNotBlank() == true) {
            val schedules = dto.schedules.map {
                scheduleService.create(device.reference, it)
            }

            return update(device.copy(
                scheduleIds = schedules.map { it.id.orEmpty() }.filter { it.isNotBlank() }
            ), schedules)
        } else factory.from(device = device)
    }

    fun update(device: Device, schedules: List<Schedule> = listOf()): DeviceDTO = factory.from(deviceRepository.save(device), schedules)

    fun delete(reference: String) = findByReference(reference).id?.let(deviceRepository::deleteById)

    fun findByReference(reference: String): Device {
        return deviceRepository.findByReference(reference) ?: throw RuntimeException("Device with id $reference not found")
    }

    fun findByScheduleId(scheduleId: String): Device {
        return deviceRepository.findDeviceByScheduleIds(scheduleId) ?: throw RuntimeException("Device with id $scheduleId not found")
    }
}