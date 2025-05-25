package io.marelso.yard.service

import io.marelso.yard.domain.Device
import io.marelso.yard.domain.dto.DeviceDTO
import io.marelso.yard.domain.factory.DeviceFactory
import io.marelso.yard.repository.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(private val deviceRepository: DeviceRepository) {
    private val factory: DeviceFactory = DeviceFactory()

    fun create(dto: DeviceDTO): DeviceDTO = factory.from(deviceRepository.save(factory.from(dto)))

    fun delete(reference: String) = findByReference(reference).id?.let(deviceRepository::deleteById)

    private fun findByReference(reference: String): Device {
        return deviceRepository.findByReference(reference) ?: throw RuntimeException("Device with id $reference not found")
    }
}