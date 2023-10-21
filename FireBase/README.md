## Setup bottom navigation and fragment

## 1. Create fragment navigation
![img.png](img.png)

<navigation xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/my_nav"
app:startDestination="@id/recipiesFragment">

    <fragment
        android:id="@+id/recipiesFragment"
        android:name="com.example.modernfoodrecipy.RecipiesFragment"
        android:label="fragment_recipies"
        tools:layout="@layout/fragment_recipies" />
    <fragment
        android:id="@+id/favariteRecipiesFragment"
        android:name="com.example.modernfoodrecipy.FavoriteRecipesFragment"
        android:label="fragment_favarite_recipies"
        tools:layout="@layout/fragment_favorite_recipes" />
    <fragment
        android:id="@+id/foodJokeFragment"
        android:name="com.example.modernfoodrecipy.FoodJokeFragment"
        android:label="fragment_food_joke"
        tools:layout="@layout/fragment_food_joke" />

## 2. Create bottom navigation
In res/menu

    <item
        android:id="@+id/recipiesFragment"
        android:icon="@drawable/icon_book"
        android:title="@string/recipe_fragment" />
    <item
        android:id="@+id/favariteRecipiesFragment"
        android:icon="@drawable/icon_star"
        android:title="@string/favorite_recipe_fragment" />

    <item
        android:id="@+id/foodJokeFragment"
        android:icon="@drawable/icon_jok"
        android:title="@string/joke_fragment" />

## 3. In Activity create navigation host
    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toTopOf="@+id/bottomNavigationView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/my_nav" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNavigationView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:menu="@menu/bottom_nav_menu" />

## 5. Controller navigation at activity
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup bottom navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }