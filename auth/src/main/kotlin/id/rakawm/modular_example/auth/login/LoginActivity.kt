package id.rakawm.modular_example.auth.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.rakawm.modular_example.auth.ModularAuth
import id.rakawm.modular_example.auth.R

/**
 * Created by rakawm on 8/13/17.
 */
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById(R.id.login_button).setOnClickListener {
            ModularAuth.getInstance().loginListener?.onLoginSuccess()
            ModularAuth.getInstance().authDataHandler.setLoggedIn(true)
            finish()
        }
    }
}