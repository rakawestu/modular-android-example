package id.rakawm.modular_example.checkout.cart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import id.rakawm.modular_example.checkout.ModularCheckout
import id.rakawm.modular_example.checkout.R
import id.rakawm.modular_example.checkout.cart.adapter.CartItemAdapter
import id.rakawm.modular_example.utils.ProductUtils

/**
 * Created by rakawm on 8/14/17.
 */
class CartActivity: AppCompatActivity() {
    private lateinit var cartItemContainer: RecyclerView
    private lateinit var totalText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        cartItemContainer = findViewById(R.id.cart_item_container) as RecyclerView
        totalText = findViewById(R.id.total_price) as TextView
        initContainer()
        initData()
    }

    private fun initContainer() {
        cartItemContainer.layoutManager = LinearLayoutManager(this)
        cartItemContainer.adapter = CartItemAdapter({ price ->
            totalText.text = ProductUtils.getFormattedPrice(price)
        })
    }

    private fun initData() {
        val items = ModularCheckout.getInstance().checkoutDataHandler.getCartItems()
        getAdapter()?.setData(items.toMutableList())
        totalText.text = ProductUtils.getFormattedPrice(getAdapter()?.getTotalPrice()?: 0)
    }

    private fun getAdapter(): CartItemAdapter? {
        return cartItemContainer.adapter as? CartItemAdapter
    }
}