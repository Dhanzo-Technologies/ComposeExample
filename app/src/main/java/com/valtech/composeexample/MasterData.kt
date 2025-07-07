package com.valtech.composeexample

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.valtech.composeexample.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun POSMasterData(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val products = listOf(
        Product(1, "Beras", 35000.00, R.drawable.beras),
        Product(2, "Telur", 27000.00, R.drawable.telur),
        Product(3, "Cabai", 75000.00, R.drawable.cabai),
        Product(4, "Tomat", 15000.00, R.drawable.tomato),
        Product(5, "Bawang Merah", 25000.00, R.drawable.onion),
        Product(6, "Bawang", 27000.00, R.drawable.bawang),
        Product(7, "Daun Bawang", 17000.00, R.drawable.daunbawang),
        Product(8, "Labu", 35000.00, R.drawable.labu),
        Product(9, "Jahe", 30000.00, R.drawable.jahe),
        Product(10, "Pala", 50000.00, R.drawable.pala),
        Product(11, "Vanili", 1000000.00, R.drawable.vanilla),
    )
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = { Text(text = "Master Data") },
            backgroundColor = Color(0xFFD8BFD8),
            contentColor = Color.White,
            navigationIcon = {
                Icon(
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
            placeholder = { Text("Search products...") },
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
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                count = products.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }.size
            ) { index ->
                val product = products.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }[index]

                ProductListItem(product = product, onItemClick = { selectedProduct ->
                    navHostController.navigate("ProductDetails")
//                    val intent = Intent(context, DetailProduct::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
//                    intent.putExtra("productId", selectedProduct.id)
//                    intent.putExtra("productName", selectedProduct.name)
//                    intent.putExtra("productPrice", selectedProduct.price)
//                    intent.putExtra("productImageResId", selectedProduct.imageResId)
//                    context.startActivity(intent)
                }, modifier = Modifier)
            }
        }
    }
}

@Composable
fun ProductListItem(
    product: Product,
    onItemClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.clickable {
                onItemClick(product)
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
        Row {
            ClickableText(
                text = AnnotatedString(product.name),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f),
                onClick = {
                    onItemClick(product)
                }
            )
            ClickableText(
                text = AnnotatedString(product.price.toRupiahFormat()),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(end = 24.dp),
                onClick = {
                    onItemClick(product)
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

fun Double.toRupiahFormat(): String {
    val formattedValue = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("en", "IN"))
        .format(this)
    return formattedValue
}

@Composable
fun FloatingActionButton(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                navHostController.navigate("AddProduct")
            },
            modifier = Modifier
                .padding(16.dp)
                .padding(end = 30.dp)
                .padding(bottom = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Product",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun MasterDataScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        POSMasterData(navHostController, Modifier)
        FloatingActionButton(navHostController, Modifier)
    }
}
