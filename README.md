# Marvel Android Test
A little Android app using [Marvel API](https://developer.marvel.com/docs)

## Architecture
MVI : Model View Intention

The view **observe** an immutable state holds by a **viewModel** and can dispatch **actions**.

The viewModel handle **actions**, manage the work based on **action type** and create a **new state** with updated models.

As the view observe the **state**, it automatically updates when the **state** is changed 

## Libraries
* [AndroidX](https://developer.android.com/jetpack/androidx) : New support library for android and much more...
* [Fresco](https://frescolib.org/) : Image library powered by Facebook
* [CoffeeScene](https://github.com/geronimoagency/CoffeeScene-Android) : Manage view scene with great animations by Jérôme Caudoux
* [Retrofit](https://square.github.io/retrofit/) : Manage calls and network services
* [KotlinX](https://github.com/Kotlin/kotlinx.coroutines) : Coroutines, a simpler and lighter alternative to manage threads
* [Gson](https://github.com/google/gson) : A google library to deal with json objects and convert it to Java Objects
* [OkHttp](https://square.github.io/okhttp/) : The well know http client to make network query

## Project organization
The project is divided in 4 parts :
- Api module that contains all submodules related to network calls, models, inteceptors and so on
- Base module that contains abstract classes and interfaces
- Helpers module where there are all functions overloads and generics class
- Screen modules that correspond to a screen inside the app with all the logic related to it (state, viewmodel, activity, view, adapter...)

At the root of the project there are the Application class and the Splash Screen view. A complex Splash Screen could be in a separate module like other screens.

## How to build
To build this app you will need to :
- Create a folder `keystore` in app project folder
- Create a file named `keystore.properties` inside it like this
```
#RELEASE
releaseStoreFile=keystore/YOUR_RELEASE_KEYSTORE_FILE_NAME
releaseStorePassword=YOUR_RELEASE_KEYSTORE_STORE_PASSWORD
releaseKeyAlias=YOUR_KEY_ALIAS
releaseKeyPassword=YOUR_RELEASE_KEYSTORE_KEY_PASSWORD
#DEBUG
debugStoreFile=keystore/YOUR_DEBUG_KEYSTORE_FILE_NAME
debugStorePassword=YOUR_DEBUG_KEYSTORE_STORE_PASSWORD
debugKeyAlias=YOUR_KEY_ALIAS
debugKeyPassword=YOUR_DEBUG_KEYSTORE_KEY_PASSWORD

apiUrl="https://gateway.marvel.com:443/v1/public/"
apiPublicKey="YOUR_PUBLIC_KEY"
apiPrivateKey="YOUR_PRIVATE_KEY"
``` 
- Generate **keystores** for debug and release, save it in `keystore` folder and add needed infos in `keystore.properties` file OR comment the signing part in app `build.gradle` file
- Create an account on [Marvel API website](https://developer.marvel.com/documentation/getting_started) and get your public and private keys [here](https://developer.marvel.com/account#)
- Add your public and private keys in `keystore.properties` file
- Run the app :)

> /!\ All files in `keystore` folder are ignored by my git configuration and you DO NOT TRY TO PUSH IT ON A PUBLIC REPO /!\

