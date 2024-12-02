package cars.ampere.mvc_basics.db

import cars.ampere.mvc_basics.model.Product

class ProductDatabase {

    private var productList: ArrayList<Product> = ArrayList()

    fun createProduct(name: String, price: Float): Product? {


        return Product(0, name, price)
    }
}