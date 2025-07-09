package com.valtech.composeexample

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.valtech.composeexample.data.ProductDetail
import com.valtech.composeexample.ui.theme.PosFinalProjectTheme

const val TEST: Boolean = false

@Composable
fun ProductDetails(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val progressBar: Boolean = true

    // val productId = "" // intent.getIntExtra("productId", 0)
    val productName = "" // intent.getStringExtra("productName")
    val productPrice = 10.01 // intent.getDoubleExtra("productPrice", 0.0)
    val productImageResId = "" // intent.getIntExtra("productImageResId", 0)

    if (progressBar) {
        print("progressBar")
    }

    val productDetail = ProductDetail(
        nama = productName ?: "",
        harga = productPrice.toRupiahFormat(),
        deskripsi = "Product stock is still available. Please add more products when the product is out of stock.",
        gambarUri = "content://com.deaenita.posfinalproject/$productImageResId",
    )
    PosFinalProjectTheme {
        POSDetailProduct(navHostController, productDetail = productDetail, modifier)
    }
}

@Composable
fun POSDetailProduct(
    navHostController: NavHostController,
    productDetail: ProductDetail,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = { Text(text = "Detail Product") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Image(
                painter = painterResource(R.drawable.sembako),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = productDetail.nama,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = productDetail.harga,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = productDetail.deskripsi,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
