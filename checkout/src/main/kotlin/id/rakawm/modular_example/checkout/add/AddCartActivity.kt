package id.rakawm.modular_example.checkout.add

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.rakawm.modular_example.checkout.ModularCheckout
import id.rakawm.modular_example.checkout.R
import id.rakawm.modular_example.checkout.cart.CartActivity

/**
 * Created by rakawm on 8/15/17.
 */
class AddCartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)
        initAction()
    }

    private fun initAction() {
        findViewById(R.id.continue_shopping_button).setOnClickListener {
            finish()
        }

        findViewById(R.id.shopping_cart_button).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            finish()
        }
    }
}