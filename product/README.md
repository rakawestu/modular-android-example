# Product Module

Product module is used to handle product related action in the application.

## Initializer

```kotlin
ModularProduct.Builder(this)
    .build()
```

## Usage

### Start product catalog page

```kotlin
ModularProduct.getInstance().startProductCatalog(this, object : ProductActionListener {
            override fun onProductBuyActionClicked(product: Product) {
                // Action when product buy button is clicked
            }

            override fun onShoppingCartActionClicked() {
                // Action when shopping cart button is clicked
            }
})
```