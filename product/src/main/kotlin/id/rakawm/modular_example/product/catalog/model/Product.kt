package id.rakawm.modular_example.product.catalog.model

import java.io.Serializable

/**
 * Created by rakawm on 8/13/17.
 */
data class Product(val name: String,
                   val image: String,
                   val price: Int) : Serializable