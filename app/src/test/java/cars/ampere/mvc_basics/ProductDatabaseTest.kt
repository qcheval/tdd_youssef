package cars.ampere.mvc_basics

import cars.ampere.mvc_basics.db.ProductDatabase
import cars.ampere.mvc_basics.model.Product
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
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)

        // when i request all products from database
        val all_products = database.request_products_from_database(database.productList)

        // then database should return all products.
        // List size is the same as the number of products created in the given above
        assert(all_products == database.productList.size)

    }

    @Test
    fun get_product_by_id_returns_the_right_product() {
        // given a database with at least one product created
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)

        // when i request a product from the database with a specific id
        val product_test = database.request_a_product_from_the_data_base_wiht_an_id(product1.id)

        // then the database returns the right product (similar id, name and price)
        assert(product1 == product_test)
    }

    @Test
    fun database_should_return_product_starting_with_string() {
        // given
        // when
        // then
    }

    @Test
    fun database_should_return_products_with_higher_price_than() {
        // given
        // when
        // then
    }

    @Test
    fun database_should_return_products_with_lower_price_than() {
        // given
        // when
        // then
    }

    // endregion

    //region: Update

    @Test
    fun database_should_update_product_name_given_an_id_and_new_name() {
        // given
        // when
        // then
    }

    @Test
    fun database_should_update_product_price_given_an_id_and_new_price() {
        // given
        // when
        // then
    }

    // endregion

    // region: Delete
    @Test
    fun database_should_delete_a_product_given_an_id() {
        // given
        // when
        // then
    }
}