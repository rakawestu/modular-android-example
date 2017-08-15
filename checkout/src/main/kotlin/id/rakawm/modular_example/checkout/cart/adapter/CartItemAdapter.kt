package id.rakawm.modular_example.checkout.cart.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import id.rakawm.modular_example.checkout.R
import id.rakawm.modular_example.checkout.cart.model.CartItem
import id.rakawm.modular_example.utils.ProductUtils

/**
 * Created by rakawm on 8/15/17.
 */
class CartItemAdapter(private val onTotalPriceUpdated: (Int) -> Unit) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {
    private var data: MutableList<CartItem> = mutableListOf()

    override fun getItemCount(): Int {
        return data.size
    }

    fun getTotalPrice(): Int {
        var total: Int = 0
        data.forEach { item ->
            total += item.quantity * item.price
        }
        return total
    }

    fun setData(data: MutableList<CartItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CartItemViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_cart, parent, false)
        return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder?, position: Int) {
        val item = data[position]
        holder?.initViewHolder(item, {
            onTotalPriceUpdated(getTotalPrice())
        })
    }

    class CartItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView?.findViewById(R.id.item_name)
        val price: TextView? = itemView?.findViewById(R.id.item_price)
        val quantity: EditText? = itemView?.findViewById(R.id.item_count)

        fun initViewHolder(item: CartItem, onUpdated: () -> Unit) {
            name?.text = item.name
            price?.text = ProductUtils.getFormattedPrice(item.price)
            quantity?.setText(item.quantity.toString())
            quantity?.addTextChangedListener(object: TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    val quantity = p0?.toString()?.toIntOrNull()
                    if (quantity != null) {
                        item.quantity = quantity
                        onUpdated()
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    // Do nothing
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    // Do nothing
                }
            })
        }
    }
}