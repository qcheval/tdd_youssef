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

    fun higherPrice(p: Float): ArrayList<Product> {
        return ArrayList(
            productList.filter {
                it.price > p
            }
        )
    }

    fun lowerPrice(p: Float): ArrayList<Product> {
        return ArrayList(
            productList.filter {
                it.price < p
            }
        )
    }

    fun changeName(id: UUID, newName: String) {
        productList.find {
            it.id == id }?.let{
            it.name = newName
        }
    }

    fun changePrice (id: UUID, newPrice: Float) {
        productList.find {
            it.id == id }?.let{
            it.price = newPrice
        }
    }

    fun deleteItem(id: UUID) {
        productList.removeIf {
            it.id == id
        }
    }

    fun grepForProducts(s: String): ArrayList<Product> {
        return ArrayList(
            productList.filter {
                it.name.contains(s)
            }
        )
    }

}