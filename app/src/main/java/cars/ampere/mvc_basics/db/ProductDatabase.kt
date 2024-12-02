package cars.ampere.mvc_basics.db

import cars.ampere.mvc_basics.model.Product

class ProductDatabase {

    private var productList: ArrayList<Product> = ArrayList()

    fun createProduct(name: String, price: Float): Product? {
        return when {
            name.isEmpty() ||
                    price < 0.0f -> null

            else -> Product(0, name, price)
        }
    }
}