package com.github.florent37.bus

internal class ObserverFunctions<T> {
    private val functions = mutableListOf<(T) -> Unit>()

    internal fun add(block: (T) -> Unit) {
        functions.add(block)
    }

    internal fun ping(value: T) {
        functions.forEach {
            try {
                it(value)
            } catch (e: Throwable) {
                println(e.message)
            }
        }
    }
}

internal class KeyObservers<T : Any> {
    private val observers = mutableMapOf<Any, ObserverFunctions<T>>()

    fun add(observer: Any, block: (T) -> Unit) {
        observers.getOrPut(observer) { ObserverFunctions<T>() }.add(block)
    }

    fun remove(observer: Any) {
        observers.remove(observer)
    }

    fun ping(value: T) {
        observers.values.forEach { it.ping(value) }
    }
}

object DefaultBus : Bus()

open class Bus() {

    companion object {
        fun getDefault() = DefaultBus
    }

    private val keyObservers : MutableMap<String, KeyObservers<Any>> = mutableMapOf()

    fun post(key: String, value: Any) {
        keyObservers[key]?.ping(value)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> addObserver(observer: Any, key: String, block: (T) -> Unit) {
        val function: ((Any) -> Unit)? = block as? (Any) -> Unit
        function?.let {
            keyObservers[key]?.add(observer, function)
        }
    }

    fun <T> removeObserver(observer: Any, key: String) {
        keyObservers[key]?.remove(observer)
    }

    fun <T> removeObserver(observer: Any) {
        keyObservers.forEach {
            it.value.remove(observer)
        }
    }

}