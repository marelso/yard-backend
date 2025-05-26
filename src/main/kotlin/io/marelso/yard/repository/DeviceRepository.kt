package io.marelso.yard.repository

import io.marelso.yard.domain.Device
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository: MongoRepository<Device, String> {
    fun findByReference(reference: String): Device?
    fun findDeviceByScheduleIds(scheduleId: String): Device?
}