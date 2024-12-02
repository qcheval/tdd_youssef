package cars.ampere.mvc_basics

import cars.ampere.mvc_basics.db.ProductDatabase
import org.junit.Test
import java.util.UUID

class ProductDatabaseTest {

    //region: Creation Tests

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

    @Test
    fun creates_a_unique_id_for_each_product() {
        val uuid = UUID.randomUUID()
        println(uuid)
    }

    // end region

    //region: Read tests


    @Test
    fun database_returns_all_products() {
        // given a database with at least 2 products created

        // when i request all products from database

        // then database should return all products.
        // List size is the same as the number of products created in the given above

    }
    @Test
    fun get_product_by_id_returns_the_right_product() {
        // given a database with at least one product created

        // when i request a product from the database with a specific id

        // then the database returns the right product (similar id, name and price)
    }


    // endregion
}