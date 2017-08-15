package id.rakawm.modular_example.checkout.payment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.rakawm.modular_example.checkout.ModularCheckout
import id.rakawm.modular_example.checkout.R

/**
 * Created by rakawm on 8/15/17.
 */
class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        findViewById(R.id.button_complete_payment).setOnClickListener {
            ModularCheckout.getInstance().getCheckoutListener()?.onPaymentCompleted()
            finish()
        }
    }
}