package id.rakawm.modular_example.checkout

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.rakawm.modular_example.checkout.add.AddCartActivity
import id.rakawm.modular_example.checkout.cart.CartActivity
import id.rakawm.modular_example.checkout.cart.model.CartItem
import id.rakawm.modular_example.checkout.data.CheckoutDataHandler
import id.rakawm.modular_example.utils.PreferenceHelper

/**
 * Created by rakawm on 8/13/17.
 */
class ModularCheckout private constructor(builder: Builder){
    companion object {
        private var INSTANCE : ModularCheckout? = null

        fun getInstance() : ModularCheckout {
            return INSTANCE?: throw RuntimeException("ModularCheckout isn't initialized yet.")
        }
    }

    val context: Context
    var checkoutDataHandler: CheckoutDataHandler
    private var checkoutListener: CheckoutListener? = null

    init {
        context = builder.context
        checkoutDataHandler = CheckoutDataHandler(PreferenceHelper(context))

        INSTANCE = this
    }

    fun startShoppingCartPage(activity: Activity) {
        activity.startActivity(Intent(activity, CartActivity::class.java))
    }

    fun addToCart(activity: Activity, name: String, price: Int) {
        // Save cart
        val items = checkoutDataHandler.getCartItems().toMutableList()
        items.add(CartItem(name, price, 1))
        checkoutDataHandler.saveCartItems(items.toList())

        activity.startActivity(Intent(activity, AddCartActivity::class.java))
    }

    fun getCheckoutListener(): CheckoutListener? {
        return checkoutListener
    }

    fun setCheckoutListener(checkoutListener: CheckoutListener) {
        this.checkoutListener = checkoutListener
    }

    class Builder(val context: Context) {
        fun build() = ModularCheckout(this)
    }
}