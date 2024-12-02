package cars.ampere.mvc_basics

import cars.ampere.mvc_basics.db.ProductDatabase
import org.junit.Test

class ProductDatabaseTest {

    @Test
    fun product_is_created_correctly() {
        // given
        val database: ProductDatabase = ProductDatabase()

        // when
        val product = database.createProduct("chat", 10.0f)

        // then
        assert(product?.name == "chat")
        assert(product?.price == 10.0f)
    }

    @Test
    fun cannot_create_a_product_with_empty_name() {
        // given
        val database: ProductDatabase = ProductDatabase()

        // when
        val product = database.createProduct("", 10.0f)

        // then
        assert(product == null)
    }

    @Test
    fun cannot_create_a_product_with_negative() {
// given
        val database: ProductDatabase = ProductDatabase()

        // when
        val product = database.createProduct("", -10.0f)

        // then
        assert(product == null)
    }



}