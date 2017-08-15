package id.rakawm.modular_example.product

import id.rakawm.modular_example.product.catalog.model.Product

/**
 * Created by rakawm on 8/13/17.
 */
interface ProductActionListener {
    fun onProductBuyActionClicked(product: Product)
    fun onShoppingCartActionClicked()
}