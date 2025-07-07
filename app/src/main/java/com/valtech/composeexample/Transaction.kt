package com.valtech.composeexample

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.valtech.composeexample.data.ProductTransaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    var totalItems by remember { mutableIntStateOf(0) }
    var totalPrice by remember { mutableDoubleStateOf(0.0) }

    val productTrans = listOf(
        ProductTransaction(1, "Beras", 35000.00, R.drawable.beras),
        ProductTransaction(2, "Telur", 27000.00, R.drawable.telur),
        ProductTransaction(3, "Cabai", 75000.00, R.drawable.cabai),
        ProductTransaction(4, "Tomat", 15000.00, R.drawable.tomato),
        ProductTransaction(5, "Bawang Merah", 25000.00, R.drawable.onion),
        ProductTransaction(6, "Bawang", 27000.00, R.drawable.bawang),
        ProductTransaction(7, "Daun Bawang", 17000.00, R.drawable.daunbawang),
        ProductTransaction(8, "Labu", 35000.00, R.drawable.labu),
        ProductTransaction(9, "Jahe", 30000.00, R.drawable.jahe),
        ProductTransaction(10, "Pala", 50000.00, R.drawable.pala),
        ProductTransaction(11, "Vanili", 1000000.00, R.drawable.vanilla),
    )

    val addToCart: (Double, Int) -> Unit = { price, qty ->
        totalItems = qty
        totalPrice = price
    }

    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = { Text(text = "Transaction") },
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
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text("Search") },
            placeholder = { Text("Search for products...") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)

        ) {
            items(
                count = productTrans.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }.size
            ) { index ->
                val product = productTrans.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }[index]

                ProductTransactionListItem(product = product, onQuantityChange = { quantity ->
                    val newPrice = (totalPrice + (product.price * quantity))
                    val newQty = (totalItems + quantity)
                    addToCart(newPrice, newQty)
                }, modifier = Modifier)
            }
        }
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
                        text = "Amount Item: $totalItems",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = totalPrice.toRupiahFormats(),
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            // val price = totalPrice.toFloat()
                            navHostController.navigate("Checkout/$totalItems/$totalPrice")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "Checkout")
                    }
                }
            }
        }
    }
}

@Composable
fun ProductTransactionListItem(
    product: ProductTransaction,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var productQty by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.clickable {
                // onItemClick(product)
            }
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.price.toRupiahFormats(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (productQty > 0) {
                        productQty--
                        onQuantityChange(productQty)
                    }
                },
                modifier = Modifier.wrapContentSize(),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
            ) {
                Text("-")
            }
            Text(
                text = productQty.toString(),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Button(
                onClick = {
                    productQty++
                    onQuantityChange(productQty)
                },
                modifier = Modifier.wrapContentSize(),
            ) {
                Text("+")
            }
        }
    }
}

fun Double.toRupiahFormats(): String {
    val formattedValue = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("en", "IN"))
        .format(this)
    return formattedValue
}
