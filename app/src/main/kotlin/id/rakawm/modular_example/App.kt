package id.rakawm.modular_example

import android.app.Application
import id.rakawm.modular_example.auth.ModularAuth
import id.rakawm.modular_example.checkout.ModularCheckout
import id.rakawm.modular_example.product.ModularProduct

/**
 * Created by rakawm on 8/13/17.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initModularAuth()
        initModularProduct()
        initModularCheckout()
    }

    private fun initModularAuth() {
        ModularAuth.Builder(this)
            .build()
    }

    private fun initModularProduct(){
        ModularProduct.Builder(this)
            .build()
    }

    private fun initModularCheckout() {
        ModularCheckout.Builder(this)
            .build()
    }
}