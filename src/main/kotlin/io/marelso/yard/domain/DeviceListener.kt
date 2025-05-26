package io.marelso.yard.domain

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

data class DeviceListener(
    val reference: String,
    val client: SseEmitter
)
