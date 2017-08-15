package id.rakawm.modular_example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import id.rakawm.modular_example.auth.ModularAuth
import id.rakawm.modular_example.auth.login.LoginResultListener
import id.rakawm.modular_example.checkout.CheckoutListener
import id.rakawm.modular_example.checkout.ModularCheckout
import id.rakawm.modular_example.product.ModularProduct
import id.rakawm.modular_example.product.ProductActionListener
import id.rakawm.modular_example.product.catalog.model.Product

/**
 * Created by rakawm on 8/9/17.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfUserIsAlreadyLoggedIn()
        initCheckoutListener()
    }

    private fun checkIfUserIsAlreadyLoggedIn() {
        if (ModularAuth.getInstance().isLoggedIn()) {
            // Start product catalog
            startProductCatalog()
        } else {
            // Start login page
            ModularAuth.getInstance().startLoginPage(this, object : LoginResultListener{
                override fun onLoginSuccess() {
                    // Handle login success
                    startProductCatalog()
                }

                override fun onLoginFailure() {
                    // Handle login failure
                }
            })
        }
    }

    private fun startProductCatalog() {
        ModularProduct.getInstance().startProductCatalog(this, object : ProductActionListener {
            override fun onProductBuyActionClicked(product: Product) {
                ModularCheckout.getInstance().addToCart(this@MainActivity, product.name, product.price)
            }

            override fun onShoppingCartActionClicked() {
                ModularCheckout.getInstance().startShoppingCartPage(this@MainActivity)
            }
        })
    }

    private fun initCheckoutListener() {
        ModularCheckout.getInstance().setCheckoutListener(object : CheckoutListener{
            override fun onPaymentCompleted() {
                Toast.makeText(this@MainActivity, "Payment completed. ", Toast.LENGTH_SHORT).show()
            }
        })
    }
}