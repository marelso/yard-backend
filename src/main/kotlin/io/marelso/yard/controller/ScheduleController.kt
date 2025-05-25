package io.marelso.yard.controller

import io.marelso.yard.domain.dto.CreateScheduleDTO
import io.marelso.yard.service.ScheduleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/schedule")
class ScheduleController(private val scheduleService: ScheduleService) {
    @PostMapping
    fun post(@RequestBody dto: CreateScheduleDTO) = ResponseEntity.ok(scheduleService.create(dto))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: String) = scheduleService.delete(id)
}