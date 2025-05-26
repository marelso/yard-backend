package io.marelso.yard.controller

import io.marelso.yard.domain.Schedule
import io.marelso.yard.domain.dto.CreateScheduleDTO
import io.marelso.yard.service.ScheduleService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/api/v1/schedule")
class ScheduleController(private val scheduleService: ScheduleService) {
    @PostMapping
    fun post(
        @RequestParam("reference") reference: String,
        @RequestBody dto: CreateScheduleDTO
    ) = ResponseEntity.ok(scheduleService.create(reference, dto))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: String) = scheduleService.delete(id)

    @GetMapping("/subscribe", MediaType.TEXT_EVENT_STREAM_VALUE)
    fun subscribe(@RequestParam("reference") reference: String): SseEmitter = scheduleService.subscribe(reference)

    @GetMapping
    fun get(
        @RequestParam("reference") reference: String
    ): ResponseEntity<List<Schedule>> = ResponseEntity.ok(scheduleService.list(reference))
}