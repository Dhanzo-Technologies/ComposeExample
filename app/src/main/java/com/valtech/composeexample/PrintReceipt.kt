package com.valtech.composeexample

// import androidx.compose.material3.icons.Icons
// import androidx.compose.material3.icons.filled.Download
// import androidx.compose.material3.icons.filled.Print
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun ReceiptScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Purchase Receipt",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
        DashedDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Store Name: StoreName",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Store Address: StoreAddress",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        DashedDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Date: ${getCurrentDateTime()}")
            Text("Receipt Number: 123456")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Name Customer: John Doe", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        DashedDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Items", modifier = Modifier.weight(1.4f))
            Text(text = "Qty", textAlign = TextAlign.Center, modifier = Modifier.weight(0.4f))
            Text(text = "Price", modifier = Modifier.weight(0.2f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        ProductItem("Product 1", 2, 10.0)
        ProductItem("Product 2", 1, 20.0)
        Spacer(modifier = Modifier.height(10.dp))
        DashedDivider()

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Total", modifier = Modifier.weight(1.4f), fontWeight = FontWeight.Bold)
            Text(
                text = "3",
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.4f),
                fontWeight = FontWeight.Bold
            )
            Text(text = "\$40", modifier = Modifier.weight(0.2f), fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))
        DashedDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text("Pay: 50")
        Text("Return: 20")
        Spacer(modifier = Modifier.height(10.dp))
        DashedDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Thank You For Your Visit",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(10.dp))

        DashedDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Powered by Valtech Mobility India",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon(
            // imageVector = Icons.Default.Print,
            // contentDescription = "Print",
            // modifier = Modifier.size(32.dp)
            // )
            Spacer(modifier = Modifier.width(16.dp))
            // Icon(
            // imageVector = Icons.Default.Download,
            // contentDescription = "Download",
            // modifier = Modifier.size(32.dp)
            // )
        }
    }
}

@Composable
fun ProductItem(name: String, quantity: Int, price: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, modifier = Modifier.weight(1.4f))
        Text(text = "$quantity", textAlign = TextAlign.Center, modifier = Modifier.weight(0.4f))
        Text(text = "\$${price * quantity}", modifier = Modifier.weight(0.2f))
    }
}

fun getCurrentDateTime(): String {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    return sdf.format(Date())
}

@Composable
fun DashedDivider(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .height(2.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(MaterialTheme.shapes.small)
    )
}
