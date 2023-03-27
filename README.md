# ExpandableBottomNavigationBar

### Gradle

Make sure that the repositories section includes Jitpack
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

### Usage

```xml
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
