package com.kolisnichenko2828.workwithflow.main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlin.random.Random

class NumberProducer(scope: CoroutineScope) {
    private val _number: Flow<Int> = flow {
        while (true) {
            delay(1000)
            emit(Random.nextInt(0, 100))
        }
    }
    val number: SharedFlow<Int> = _number
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed()
        )
}