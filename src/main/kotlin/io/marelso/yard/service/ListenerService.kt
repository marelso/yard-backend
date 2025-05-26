package io.marelso.yard.service

import io.marelso.yard.domain.DeviceListener
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class ListenerService<T> {

    private val listeners: MutableList<DeviceListener> = mutableListOf()

    fun subscribe(reference: String): SseEmitter = DeviceListener(
        reference = reference,
        client = SseEmitter()
    ).apply {
        listeners.add(this)

        client.onCompletion { listeners.remove(this) }
        client.onTimeout { listeners.remove(this) }
    }.client

    fun notifyListeners(reference: String, data: T) {
        listeners.filter { it.reference == reference }
            .forEach { listener ->
            notifyClient(listener.client, data)
        }
    }

    private fun notifyClient(client: SseEmitter, data: T) {
        try {
            client.send(data as JvmType.Object)
        } catch (exception: Exception) {
            client.complete()
        }
    }
}