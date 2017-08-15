package id.rakawm.modular_example.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.rakawm.modular_example.auth.data.AuthDataHandler
import id.rakawm.modular_example.auth.login.LoginActivity
import id.rakawm.modular_example.auth.login.LoginResultListener
import id.rakawm.modular_example.utils.PreferenceHelper

/**
 * Created by rakawm on 8/13/17.
 */
class ModularAuth private constructor(builder: Builder){
    companion object {
        private var INSTANCE : ModularAuth? = null

        fun getInstance(): ModularAuth {
            return INSTANCE?: throw RuntimeException("ModularAuth isn't initialized.")
        }
    }

    val context: Context
    val authDataHandler: AuthDataHandler
    var loginListener: LoginResultListener? = null

    init {
        context = builder.context
        authDataHandler = AuthDataHandler(PreferenceHelper(builder.context))

        INSTANCE = this
    }

    /**
     * Start login page.
     */
    fun startLoginPage(activity: Activity, loginResultListener: LoginResultListener) {
        this.loginListener = loginResultListener
        activity.startActivity(Intent(context, LoginActivity::class.java))
    }

    /**
     * Check if user is already logged in.
     */
    fun isLoggedIn(): Boolean {
        return authDataHandler.isLoggedIn()
    }

    class Builder(val context: Context) {
        fun build() = ModularAuth(this)
    }
}