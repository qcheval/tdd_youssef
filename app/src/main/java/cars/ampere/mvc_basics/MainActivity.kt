package cars.ampere.mvc_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import cars.ampere.mvc_basics.db.ProductDatabase
import cars.ampere.mvc_basics.model.Product
import cars.ampere.mvc_basics.ui.theme.MVC_BasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productDatabase = ProductDatabase()

        for (i in 1..30) {
            productDatabase.createProduct("Produit $i", 10.0f + i)
        }

        setContent {
            Main(productDatabase.getProductList())
        }
    }
}

@Composable
fun Main(products: List<Product>) {
    Column(modifier = Modifier.fillMaxSize()) { //column pour organiser les éléments de haut en bas
        ListDesProduits(
            products = products,
            modifier = Modifier.weight(1f) //fait de la place pour les boutons
        )
        Acheter()
    }
}

@Composable
fun ListDesProduits(products: List<Product>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth() //prend tt la lrger
            .padding(10.dp), //marge de 10 autoueur de la liste
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product -> //afficher each produit dans la liste défilante de lazyColumn
            RowProduit(product)
        }
    }
}

@Composable
fun RowProduit(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween //espace entre name et price
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