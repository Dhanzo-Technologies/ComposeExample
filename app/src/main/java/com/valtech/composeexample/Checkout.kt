package com.valtech.composeexample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun POSCheckout(
    navHostController: NavHostController,
    totalItemsParam: Int,
    totalPriceParam: Double,
    modifier: Modifier = Modifier,
) {
    val paymentMethods = listOf(
        PaymentMethod("Google Pay", R.drawable.google_pay_icon),
        PaymentMethod("PhonePe", R.drawable.phonepe_icon),
        PaymentMethod("Paytm", R.drawable.paytm_icon),
        PaymentMethod("Net Banking", R.drawable.pay_online_icon)
    )

    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(null) }
    var openDialog by remember { mutableStateOf(false) }

    var totalItems by remember { mutableIntStateOf(totalItemsParam) }
    var totalPrice by remember { mutableDoubleStateOf(totalPriceParam) }

    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = { Text(text = "Checkout") },
            backgroundColor = Color(0xFFD8BFD8),
            contentColor = Color.White,
            navigationIcon = {
                androidx.compose.material.Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp)
                        .clickable {
                            navHostController.popBackStack()
                        },
                    tint = Color.Black
                )
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Choose Payment Method",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        paymentMethods.forEach { method ->
            PaymentOptionItem(
                paymentMethod = method,
                isSelected = selectedMethod == method,
                onClick = { selectedMethod = method },
                Modifier
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { /* Action when clicked */ }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Number of Items: $totalItems",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = totalPrice.toRupiahFormatCheckout(),
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            openDialog = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "Pay")
                    }
                }
            }
        }
        if (openDialog) {
            TransactionSuccessDialog(navHostController) {
                openDialog = false
            }
        }
    }
}

@Composable
fun PaymentOptionItem(
    paymentMethod: PaymentMethod,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() }
            .background(if (isSelected) Color(0xFFE0F7FA) else Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = paymentMethod.iconRes),
                contentDescription = paymentMethod.name,
                modifier = Modifier
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(paymentMethod.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

data class PaymentMethod(val name: String, val iconRes: Int)

fun Double.toRupiahFormatCheckout(): String {
    val formattedValue = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("en", "IN"))
        .format(this)
    return formattedValue
}

@Composable
fun TransactionSuccessDialog(navHostController: NavHostController, onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Transaction Successful", textAlign = TextAlign.Center)
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.transactionsukses),
                    contentDescription = "Transaction Success Image",
                    modifier = Modifier
                        .size(72.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Thank you for your transaction!",
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismissRequest()
                    navHostController.navigate("PrintReceipt")
                    // val intentPrintStruk = Intent(context, PrintReceipt::class.java)
                    // context.startActivity(intentPrintStruk)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Print Receipt")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismissRequest()
                    navHostController.navigate("Home")
                    // Implement your logic for closing the dialog
                    // val intent = Intent(context, MainActivity::class.java)
                    // context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Continue Shopping")
            }
        }
    )
}
