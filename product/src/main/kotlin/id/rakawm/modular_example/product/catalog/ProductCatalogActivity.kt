package id.rakawm.modular_example.product.catalog

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import id.rakawm.modular_example.product.ModularProduct
import id.rakawm.modular_example.product.R
import id.rakawm.modular_example.product.catalog.adapter.ProductAdapter
import id.rakawm.modular_example.product.catalog.model.Product
import id.rakawm.modular_example.product.details.ProductDetailsActivity


/**
 * Created by rakawm on 8/13/17.
 */
class ProductCatalogActivity: AppCompatActivity() {
    private lateinit var productContainer: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_catalog)
        productContainer = findViewById(R.id.product_container) as RecyclerView
        initToolbar()
        initData()
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun initData() {
        productContainer.layoutManager = GridLayoutManager(this, 2)
        productContainer.adapter = ProductAdapter(getData(), { position ->
            val product = getAdapter().getItem(position)
            val intent = Intent(this@ProductCatalogActivity, ProductDetailsActivity::class.java)
            intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT, product)
            startActivity(intent)
        })
    }

    private fun getAdapter() : ProductAdapter {
        return productContainer.adapter as ProductAdapter
    }

    private fun getData(): MutableList<Product> {
        return mutableListOf(
            Product("Product 1", "", 10000),
            Product("Product 2", "", 20000),
            Product("Product 3", "", 30000),
            Product("Product 4", "", 40000)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_product, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_cart -> ModularProduct.getInstance().buyActionListener?.onShoppingCartActionClicked()
        }
        return super.onOptionsItemSelected(item)
    }
}