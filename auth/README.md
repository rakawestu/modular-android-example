# Auth Module

Authentication module used for login and user data related action.

## Initializer

```kotlin
ModularAuth.Builder(CONTEXT)
    .build()
```

## Usage

### Start login page

```kotlin
ModularAuth.getInstance().startLoginPage(ACTIVITY, object: LoginResultListener{
    override fun onLoginSuccess() {
        // Handle login success
    }

    override fun onLoginFailure() {
        // Handle login failure
    }
})
```

### Check if user is logged in

```kotlin
if (ModularAuth.getInstance().isLoggedIn()) {
    // User is already logged in
}
```