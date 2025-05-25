package io.marelso.yard.controller

import io.marelso.yard.service.ScheduleService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/schedule")
class ScheduleController(private val scheduleService: ScheduleService) {
}