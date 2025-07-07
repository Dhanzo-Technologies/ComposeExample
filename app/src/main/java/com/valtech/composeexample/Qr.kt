package com.valtech.composeexample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun QrContent(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Vivek Patel",
            fontSize = 24.sp,
            letterSpacing = 10.sp,
            modifier = Modifier.padding(8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.kasirpraktis),
            contentDescription = "Transaction Success Image",
        )
        Button(
            onClick = {
                navHostController.popBackStack()
            },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(text = "Close")
        }
    }
}
