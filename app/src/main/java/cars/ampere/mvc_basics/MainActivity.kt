package cars.ampere.mvc_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cars.ampere.mvc_basics.db.ProductDatabase
import cars.ampere.mvc_basics.model.Product

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productDatabase = ProductDatabase()

        for (i in 1..30) {
            productDatabase.createProduct("Produit $i", 10.0f + i)
        }

        setContent {
            Main(productDatabase)
        }
    }
}

@Composable
fun Main(productDatabase: ProductDatabase) {
    val productsList = remember { mutableStateOf(productDatabase.getProductList()) }
    val selectedProduct = remember { mutableStateOf<Product?>(null) }

    Column( modifier = Modifier
                .fillMaxSize()) {
        ListDesProduits( products_list = productsList.value,
                         selectedProduct = selectedProduct.value,
                         modifier = Modifier.weight(1f),
                         onProductClick = { product -> selectedProduct.value = product })
        Acheter()
    }
}

@Composable
fun ListDesProduits( products_list: List<Product>,
                     selectedProduct: Product?,
                     modifier: Modifier = Modifier,
                     onProductClick: (Product) -> Unit) {
    LazyColumn( modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(products_list) { product ->
            RowProduit( product = product, isSelected = (product == selectedProduct), onRowClick = onProductClick)
        }
    }
}

@Composable
fun RowProduit(product: Product, isSelected: Boolean, onRowClick: (Product) -> Unit) {
    Row( modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onRowClick(product)
                       }
            .then(
                if (isSelected) Modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
                else Modifier),
         horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
        Text(text = "${product.price} €", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun Acheter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { /* x */ }) {
            Text("Add")
        }
        Button(onClick = { /* x */ }) {
            Text("Remove")
        }
    }
}