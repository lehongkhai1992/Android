## Section First

        val bundle = Bundle()
        bundle.putString("key", binding.editTextText.text.toString())
        binding.button.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editTextText.text.toString())) {
                val bundle = bundleOf("user_input" to binding.editTextText.text.toString())
                it.findNavController().navigate(R.id.action_homeFragment_to_secondragment, bundle)
            } else {
                Toast.makeText(activity, "Please insert your name", Toast.LENGTH_LONG).show()
            }
        }

## Section Second

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        // Inflate the layout for this fragment
        val bundle = arguments!!.getString ("user_input", "12")
        Log.d("Second"," $bundle")
        binding.textView.text = bundle.toString()

## Restrictions
Fragments are maintained in a stack per Android's guideline https://developer.android.com/guide/navigation/navigation-principles#navigation_state_is_represented_as_a_stack_of_destinations . A lot of questions get asked about how to maintain only one instance of a fragment, or to pull out a fragment in the middle of the stack. That is outside Android navigation guidelines, and also this library. You may want to rethink your UX.


## Sample
With [Material Design Bottom Navigation pattern](https://www.google.com/design/spec/components/bottom-navigation.html), and other tabbed navigation, managing multiple stacks of fragments can be a real headache.  The example file shows best practice for navigating deep within a tab stack.

## Gradle

```groovy
implementation 'com.ncapdevi:frag-nav:3.2.0'   //or or `compile` if on older gradle version
```

## How do I implement it?

### Initialize using a builder and one of two methods
```kotlin
    private val fragNavController: FragNavController = FragNavController(supportFragmentManager, R.id.container)
```
#### 1.
Create a list of fragments and pass them in
```kotlin
 val fragments = listOf(
                RecentsFragment.newInstance(0),
                FavoritesFragment.newInstance(0),
                NearbyFragment.newInstance(0),
                FriendsFragment.newInstance(0),
                FoodFragment.newInstance(0),
                RecentsFragment.newInstance(0),
                FavoritesFragment.newInstance(0),
                NearbyFragment.newInstance(0),
                FriendsFragment.newInstance(0),
                FoodFragment.newInstance(0),
                RecentsFragment.newInstance(0),
                FavoritesFragment.newInstance(0))

fragNavController.rootFragments = fragments
```

#### 2.


Allow for dynamically creating the base class by implementing the NavListener in your class and overriding the getRootFragment method

```java
public class YourActivity extends AppCompatActivity implements FragNavController.RootFragmentListener {
```

```java
fragNavController.rootFragmentListener = this
```

```kotlin

    override val numberOfRootFragments: Int = 5

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            INDEX_RECENTS -> return RecentsFragment.newInstance(0)
            INDEX_FAVORITES -> return FavoritesFragment.newInstance(0)
            INDEX_NEARBY -> return NearbyFragment.newInstance(0)
            INDEX_FRIENDS -> return FriendsFragment.newInstance(0)
            INDEX_FOOD -> return FoodFragment.newInstance(0)
        }
        throw IllegalStateException("Need to send an index that we know")
    }

```

#### 3.
```java
        fragNavController.initialize(FragNavController.TAB3, savedInstanceState)
```

### SaveInstanceState

Send in  the supportFragment Manager, a list of base fragments, the container that you'll be using to display fragments.
After that, you have four main functions that you can use
In your activity, you'll also want to override your onSaveInstanceState like so

```kotlin
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        fragNavController.onSaveInstanceState(outState!!)

    }
```

### Switch tabs
Tab switching is indexed to try to prevent you from sending in wrong indices. It also will throw an error if you try to switch to a tab you haven't defined a base fragment for.

```java
fragNavController.switchTab(NavController.TAB1);
```

### Push a fragment
You can only push onto the currently selected index
```java
        fragNavController.pushFragment(FoodFragment.newInstance())
```

### Pop a fragment
You can only pop from the currently selected index. This can throw an UnsupportedOperationException if trying to pop the root fragment
```java
        fragNavController.popFragment();
```
### Pop multiple fragments
You can pop multiple fragments at once, with the same rules as above applying.  If the pop depth is deeper than possible, it will stop when it gets to the root fragment
```java
       fragNavController.popFragments(3);
```
### Replacing a fragment
You can only replace onto the currently selected index
```java
        fragNavController.replaceFragment(Fragment fragment);
```
### You can also clear the stack to bring you back to the base fragment
```java
        fragNavController.clearStack();
```
### You can also navigate your DialogFragments using
```java
        showDialogFragment(dialogFragment);
        clearDialogFragment();
        getCurrentDialogFrag()
```