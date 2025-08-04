package com.valtech.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class Report : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POSReport()
        }
    }
}

@Composable
fun POSReport(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(title = { Text(text = "Report") })
    }
}
