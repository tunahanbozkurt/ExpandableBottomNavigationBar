# ExpandableBottomNavigationBar


<img align="center" width="350" src="./gif/bottom_nav.gif">

### Usage
You can set the menu items like below.
```xml
<!-- example_menu.xml -->
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@+id/homeFragment"
        android:icon="@drawable/baseline_home_24"
        android:menuCategory="secondary"
        android:title="@string/home"
        app:ebnb_selectedIconColor="#12CDD9"
        app:ebnb_selectedTextColor="#12CDD9"
        app:ebnb_unSelectedIconColor="@color/primary_grey"
        app:ebnb_unSelectedTextColor="@color/primary_grey" />

    <item
        android:id="@+id/searchFragment"
        android:icon="@drawable/baseline_search_24"
        android:title="@string/search"
        app:ebnb_selectedIconColor="@color/primary_blue"
        app:ebnb_selectedTextColor="@color/primary_blue"
        app:ebnb_unSelectedIconColor="@color/primary_grey"
        app:ebnb_unSelectedTextColor="@color/primary_grey" />

    <item
        android:id="@+id/downloadFragment"
        android:icon="@drawable/baseline_download_24"
        android:title="@string/download"
        app:ebnb_selectedIconColor="@color/primary_blue"
        app:ebnb_selectedTextColor="@color/primary_blue"
        app:ebnb_unSelectedIconColor="@color/primary_grey"
        app:ebnb_unSelectedTextColor="@color/primary_grey" />

    <item
        android:id="@+id/profileFragment"
        android:icon="@drawable/baseline_person_24"
        android:title="@string/profile"
        app:ebnb_selectedIconColor="@color/primary_blue"
        app:ebnb_selectedTextColor="@color/primary_blue"
        app:ebnb_unSelectedIconColor="@color/primary_grey"
        app:ebnb_unSelectedTextColor="@color/primary_grey" />
</menu>
```
You can add the view like below.
```xml
<com.example.expandable_bottom_navigation_bar.ExpandableBottomNavigationBar
        android:id="@+id/bottom_navigation_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/primary_dark
        app:ebnb_itemBackgroundShape="@drawable/ebnb_item_background"
        app:ebnb_menuResource="@menu/example_menu"
        android:paddingVertical="16dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
```
Finally, you can setup the navigation in two ways.

First, you can simply use ```setupWithNavController()``` extension. It saves and restores fragment states by default if you need to more control you can use second option.
```kotlin
val bottomNavigation = binding.bottomNavigationView

val navHostFragment = supportFragmentManager
    .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

val navController = navHostFragment.navController

bottomNavigation.setupWithNavController(navController)
```

Second, you can use ```setupCustomNavigationWithOptions()``` extension. You can set custom nav options to achieve the behaviour, you want to create.
```kotlin
bottomNavigation.setupCustomNavigationWithOptions(
            navController,
            NavOptions
                .Builder()
                .apply {
                    // Your options here
                }
                .build()
        )
```

### Gradle

Make sure that the repositories section includes Jitpack.
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the library to the dependencies:

```gradle
implementation 'com.github.tunahanbozkurt:ExpandableBottomNavigationBar:1.1'
```

### Source
Check out for more [chip-navigation-bar](https://material.io/design/components/bottom-navigation.html](https://github.com/ismaeldivita/chip-navigation-bar)) by [ismaeldivita](https://github.com/ismaeldivita)

### License
The MIT License (MIT)

Copyright (c) 2023 Tunahan Bozkurt

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
