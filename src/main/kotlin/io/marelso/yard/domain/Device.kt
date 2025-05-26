package io.marelso.yard.domain

data class Device(
    val id: String? = null,
    val reference: String? = null,
    val name: String? = null,
    val scheduleIds: List<String> = listOf(),
)
