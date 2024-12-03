package cars.ampere.mvc_basics.db

import cars.ampere.mvc_basics.model.Product
import java.util.UUID

class ProductDatabase {

    private var productList: ArrayList<Product> = ArrayList()

    fun getProductList(): ArrayList<Product>{
        return productList
    }

    fun createProduct(name: String, price: Float): Product? {
        val product = when {
            name.isEmpty() || price < 0.0f -> null
            else -> Product(UUID.randomUUID(), name, price)
        }
        product?.let {
            productList.add(it)
        }
        return product
    }

    fun requestProductFromDataBaseWithId(id: UUID): Product? {
        return productList.find {
            it.id == id
        }
    }

    fun higherPrice(p: Float): ArrayList<Product>{
        val tempProductList: ArrayList<Product> = ArrayList()
        for (i in 0..(productList.size-1)) {
            if(productList[i].price > p) {
                tempProductList.add(productList[i])
            }
        }
            return tempProductList
    }

    fun lowerPrice(p: Float): ArrayList<Product>{
        val tempProductList: ArrayList<Product> = ArrayList()
        for (i in 0..(productList.size-1)) {
            if(productList[i].price < p) {
                tempProductList.add(productList[i])
            }
        }
        return tempProductList
    }

    fun changeName (id: UUID, newName: String) {
        for (i in 0..(productList.size-1)) {
            if (id == productList[i].id) {
                productList[i].name = newName
            }
        }
    }

    fun changePrice (id: UUID, newPrice: Float) {
        for (i in 0..(productList.size-1)) {
            if (id == productList[i].id) {
                productList[i].price = newPrice
            }
        }
    }

    fun deleteItem(id: UUID) {
        productList.removeIf {
            it.id == id
        }
    }

}

