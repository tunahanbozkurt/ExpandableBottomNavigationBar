# ExpandableBottomNavigationBar

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

### Usage
You can use set the menu items like below.
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
