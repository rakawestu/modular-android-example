package id.rakawm.modular_example.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

/**
 * Created by rakawm on 8/13/17.
 */
object ProductUtils {
    private fun getCurrencyFormat(): NumberFormat {
        val df = NumberFormat.getCurrencyInstance()
        val dfs = DecimalFormatSymbols()
        dfs.currencySymbol = "$"
        dfs.groupingSeparator = '.'
        dfs.monetaryDecimalSeparator = '.'
        (df as DecimalFormat).decimalFormatSymbols = dfs
        return df
    }

    fun getFormattedPrice(price: Int) : String {
        return getCurrencyFormat().format(price)
    }
}