package cars.ampere.mvc_basics.model

import java.util.UUID

data class Product(val id: UUID, var name: String, var price: Float)