# Checkout Module

Checkout module is used to handle shopping cart and payment related action.

## Initializer

```kotlin
ModularCheckout.Builder(this)
    .build()
````

## Usage

### Add to cart

```kotlin
ModularCheckout.getInstance().addToCart(ACTIVITY, ITEM_NAME, ITEM_PRICE)
```

### Start shopping cart page

```kotlin
ModularCheckout.getInstance().startShoppingCartPage(ACTIVITY)
```

### Set checkout listener

```kotlin
ModularCheckout.getInstance().setCheckoutListener(object : CheckoutListener{
            override fun onPaymentCompleted() {
                // Payment completed action
            }
})
```