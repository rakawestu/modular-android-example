package id.rakawm.modular_example.product.catalog.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.rakawm.modular_example.product.R
import id.rakawm.modular_example.product.catalog.model.Product
import id.rakawm.modular_example.utils.ProductUtils


/**
 * Created by rakawm on 8/13/17.
 */
class ProductAdapter(private val data: MutableList<Product>,
                     private val itemClickAction: (Int) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
        holder?.initProduct(data[position])
    }

    fun getItem(position: Int): Product {
        return data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, itemClickAction)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ProductViewHolder(itemView: View?,
                            private val itemClickAction: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView?.findViewById(R.id.product_name)
        val image: ImageView? = itemView?.findViewById(R.id.product_image)
        val price: TextView? = itemView?.findViewById(R.id.product_price)

        fun initProduct(product: Product) {
            name?.text = product.name
            price?.text = ProductUtils.getFormattedPrice(product.price)
            itemView?.setOnClickListener{
                itemClickAction(adapterPosition)
            }
        }
    }
}