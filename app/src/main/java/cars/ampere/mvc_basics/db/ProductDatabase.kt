package cars.ampere.mvc_basics.db

import cars.ampere.mvc_basics.model.Product
import java.util.UUID

class ProductDatabase {

    private var productList: ArrayList<Product> = ArrayList()

    fun createProduct(name: String, price: Float): Product? {
        return when {
            name.isEmpty() ||
                    price < 0.0f -> null
            else -> Product(UUID.randomUUID(), name, price)
        }
        productList.add(Product(UUID.randomUUID(),name, price))
    }

    fun request_products_from_database(productList: ArrayList<Product>): ArrayList<Product> {
        return  productList
    }

    fun request_a_product_from_the_data_base_wiht_an_id(id: UUID): Product? {
        return productList.find {
            it.id == id
        }
    }
}