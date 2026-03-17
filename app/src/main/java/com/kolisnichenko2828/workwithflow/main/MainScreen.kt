package com.kolisnichenko2828.workwithflow.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kolisnichenko2828.workwithflow.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val numbers by viewModel.numbers.collectAsStateWithLifecycle()

    LifecycleStartEffect(Unit) {
        viewModel.startNumberGenerating()

        onStopOrDispose {
            viewModel.stopNumberGenerating()
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Text(text = stringResource(R.string.list_size, numbers.size))
        }

        items(
            items = numbers
        ) { number ->
            Text(text = stringResource(R.string.number, number))
        }
    }
}