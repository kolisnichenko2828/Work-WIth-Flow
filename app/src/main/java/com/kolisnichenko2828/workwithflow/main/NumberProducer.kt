package com.kolisnichenko2828.workwithflow.main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class NumberProducer(val scope: CoroutineScope) {

    private var job: Job? = null
    private val _number = MutableSharedFlow<Int>()
    val number: SharedFlow<Int> = _number.asSharedFlow()

    fun start() {
        if (job != null) return

        job = scope.launch {
            while (true) {
                delay(1000)
                _number.emit(Random.nextInt(0, 100))
            }
        }
    }

    fun stop() {
        job?.cancel()
        job = null
    }
}