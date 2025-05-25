package io.marelso.yard.controller

import io.marelso.yard.domain.dto.DeviceDTO
import io.marelso.yard.service.DeviceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/device")
class DeviceController(private val deviceService: DeviceService) {
    @PostMapping
    fun post(@RequestBody dto: DeviceDTO): DeviceDTO = deviceService.create(dto)


    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("reference") reference: String) = deviceService.delete(reference)
}