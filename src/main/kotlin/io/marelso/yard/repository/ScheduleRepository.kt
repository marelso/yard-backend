package io.marelso.yard.repository

import io.marelso.yard.domain.Schedule
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository: MongoRepository<Schedule, String> {
}