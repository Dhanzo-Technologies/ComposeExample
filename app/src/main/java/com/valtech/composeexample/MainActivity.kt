package com.valtech.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavHost(navController)
            // POSHomePage()
        }
    }
}

@Composable
fun AppNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navHostController, startDestination = "Home", modifier = modifier) {
        composable("Home") { POSHomePage(navHostController) }
        composable("MasterData") { MasterDataScreen(navHostController) }
        composable("AddProduct") { AddProductsScreen(navHostController) }
        composable("ProductDetails") { ProductDetails(navHostController) }
        composable("Transaction") { TransactionScreen(navHostController) }
        composable(
            "Checkout/{totalItemsParam}/{totalPriceParam}",
            arguments = listOf(
                navArgument("totalItemsParam") {
                    type = NavType.IntType
                },
                navArgument("totalPriceParam") {
                    type = NavType.FloatType
                }
            )
        ) { backStackEntry ->
            val totalItemsParam = backStackEntry.arguments?.getInt("totalItemsParam", 0) ?: 0
            val totalPriceParam = backStackEntry.arguments?.getFloat("totalPriceParam", 0.0f) ?: 0.0f
            POSCheckout(navHostController, totalItemsParam, totalPriceParam.toDouble(), Modifier)
        }
        composable("QrContent") { QrContent(navHostController) }
        composable("PrintReceipt") { ReceiptScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun POSHomePage(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val formattedTrans = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        .format(10000000)
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Valtech POS",
                        letterSpacing = 5.sp,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                )
            )
        }
    ) { contentPending ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPending),
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            TotalIncomeView(totalIncome = 1500000.0)
            Spacer(modifier = Modifier.height(65.dp))
            // val context = LocalContext.current
            // val intentQr = Intent(context, Qr::class.java)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuIcon(
                    icon = painterResource(id = R.drawable.masterdata),
                    label = "Master Data",
                    onClick = {
                        navHostController.navigate("MasterData")
                    }
                )
                MenuIcon(
                    icon = painterResource(id = R.drawable.transaction),
                    label = "Transaction",
                    onClick = {
                        navHostController.navigate("Transaction")
                    }
                )
                MenuIcon(
                    icon = painterResource(id = R.drawable.qrscan),
                    label = "QR",
                    onClick = {
                        navHostController.navigate("QrContent")
                    }
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Order History",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .padding(start = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                        Text(
                            text = "Transaction 1",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                        Text(
                            text = formattedTrans,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Transaction 2",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(8.dp)

                        )
                        Text(
                            text = formattedTrans,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(8.dp)

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MenuIcon(icon: Painter, label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color.LightGray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label)
        }
    }
}

@Composable
fun TotalIncomeView(totalIncome: Double, modifier: Modifier = Modifier) {
    val formattedTotalIncome = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        .format(totalIncome)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(80.dp)
            .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(100.dp)
                .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Total Income: $formattedTotalIncome",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
