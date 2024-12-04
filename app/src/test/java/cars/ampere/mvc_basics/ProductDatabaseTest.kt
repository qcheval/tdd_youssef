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
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)

        // when i request all products from database
        val productList = database.getProductList()
        val productListTest = listOf(product1, product2)

        // then database should return all products.
        // List size is the same as the number of products created in the given above
        assert(productList.size == 2)
        assert(productList == productListTest)

    }

    @Test
    fun get_product_by_id_returns_the_right_product() {
        // given a database with at least one product created
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)

        // when i request a product from the database with a specific id
        val productTest = database.requestProductFromDataBaseWithId(product1!!.id)

        // then the database returns the right product (similar id, name and price)
        assert(product1 == productTest)
    }

    @Test
    fun database_should_return_product_starting_with_string() {

        // given
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)
        val product3 = database.createProduct("youssef2", 100.0f)
        val product4 = database.createProduct("youpii", 10000.0f)
        val product5 = database.createProduct("yauhoo", 10000.0f)
        val product6 = database.createProduct("yoaahoo", 10000.0f)
        val product7 = database.createProduct("yauyou", 10000.0f)
        val product8 = database.createProduct("y", 10000.0f)

        // when
        val testProductList1 = database.grepForProducts("you")
        val testProductList2 = listOf(product2, product3, product4,product7)
        // then
        assert (testProductList1  == testProductList2)

    }

    @Test
    fun database_should_return_products_with_higher_price_than() {
        // given
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)
        val product3 = database.createProduct("youssef2", 100.0f)
        val product4 = database.createProduct("youpii", 10000.0f)
        val product5 = database.createProduct("yauhoo", 101.0f)
        val product6 = database.createProduct("yoaahoo", 99.0f)
        val product7 = database.createProduct("yauyou", 200.0f)
        val product8 = database.createProduct("y", 100000.0f)

        // when
        val testProductList1 = database.higherPrice(100.0f)
        val testProductList2 = listOf(product4, product5, product7, product8)

        // then
        for (i in 0..<testProductList1.size){
            assert(testProductList1[i].id == testProductList2[i]!!.id)
            assert(testProductList1[i].name == testProductList2[i]!!.name)
            assert(testProductList1[i].price == testProductList2[i]!!.price)
        }

    }

    @Test
    fun database_should_return_products_with_lower_price_than() {
        // given
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)
        val product3 = database.createProduct("youssef2", 100.0f)
        val product4 = database.createProduct("youpii", 10000.0f)
        val product5 = database.createProduct("yauhoo", 101.0f)
        val product6 = database.createProduct("yoaahoo", 99.0f)
        val product7 = database.createProduct("yauyou", 200.0f)
        val product8 = database.createProduct("y", 100000.0f)

        // when
        val testProductList1 = database.lowerPrice(100.0f)
        val testProductList2 = listOf(product1, product6)

        // then
        for (i in 0..<testProductList1.size){
            assert(testProductList1[i].id == testProductList2[i]!!.id)
            assert(testProductList1[i].name == testProductList2[i]!!.name)
            assert(testProductList1[i].price == testProductList2[i]!!.price)
        }
    }

    // endregion

    //region: Update

    @Test
    fun database_should_update_product_name_given_an_id_and_new_name() {
        // given
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)

        // when
        database.changeName(product1!!.id, "youssef")
        database.changeName(product2!!.id, "quentin")

        // then
        assert(product1.name == "youssef")
        assert(product2.name == "quentin")
    }

    @Test
    fun database_should_update_product_price_given_an_id_and_new_price() {
        // given
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)

        // when
        database.changePrice(product1!!.id, 100.0f)
        database.changePrice(product2!!.id, 10.0f)

        // then
        assert(product1.price== 100.0f)
        assert(product2.price== 10.0f)
    }

    // endregion

    // region: Delete
    @Test
    fun database_should_delete_a_product_given_an_id() {
        // given
        val database: ProductDatabase = ProductDatabase()
        val product1 = database.createProduct("quentin", 10.0f)
        val product2 = database.createProduct("youssef", 100.0f)
        val product3 = database.createProduct("youssef2", 100.0f)

        // when
        database.deleteItem(product2!!.id)
        val testProductList1 = database.getProductList()
        val testProductList2 = listOf(product1, product3)

        // then
        for (i in 0..<testProductList1.size){
            assert(testProductList1[i].id == testProductList2[i]!!.id)
            assert(testProductList1[i].name == testProductList2[i]!!.name)
            assert(testProductList1[i].price == testProductList2[i]!!.price)
        }
    }
}