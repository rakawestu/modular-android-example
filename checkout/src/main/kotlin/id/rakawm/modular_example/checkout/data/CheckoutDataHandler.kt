package id.rakawm.modular_example.checkout.data

import com.google.gson.Gson
import id.rakawm.modular_example.checkout.cart.model.CartItem
import id.rakawm.modular_example.utils.PreferenceHelper

/**
 * Created by rakawm on 8/15/17.
 */
class CheckoutDataHandler(private val preferenceHelper: PreferenceHelper) {
    companion object {
        private const val KEY_CART_ITEMS = "cart.items"
    }

    fun getCartItems(): List<CartItem> {
        return preferenceHelper.getListObject(KEY_CART_ITEMS, listOf(), CartItem::class.java)
    }

    fun saveCartItems(items: List<CartItem>) {
        preferenceHelper.putListObject(KEY_CART_ITEMS, items)
    }
}