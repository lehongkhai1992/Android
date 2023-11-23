### setup project

plugins {
id 'kotlin-kapt'
}

android {

buildFeatures {
dataBinding true
}
}

dependencies {
def lifecycle_version = "2.6.1"
def room_version = "2.5.2"

// ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
// LiveData
implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

// Annotation processor
kapt "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"

//coroutine
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
//room
implementation "androidx.room:room-runtime:$room_version"
kapt "androidx.room:room-compiler:$room_version"
}

### Room Entity class

One entity class for each table

- We need to create entity classes for each database table.
- Moreover, to qualify a class as a room entity class, we need to annotate it with @Entity .
- Holding data is the only purpose of these classes. So, we will create them as Kotlin data classes.

Name of the database table

- Database table will have the same name as the class name.
- And, if we need a different name, we could give that name as the value of the “tableName”
  property.

Name of the table columns

- Columns will have same names as the data class’s variable names.
- But, if we want different names we can provide them using @ColumnInfo

Primary Key
Then, use the @PrimaryKey on the variable selected as the primary key of the table.
If you want the primary key to auto generate set autoGenerate = true

### Create room data class

// need to have annotate @Entity, can give table name or not, if y not give tableName it will get
name same with class name
@Entity(tableName = "subcriber_data_table")
data class Subcribers (

    // @PrimaryKey: can give primary key and auto generation or not 
    // @ColumnInfo:  give the name if you want
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subcriber_id")
    val id: Int,

    @ColumnInfo(name = "subcriber_name")
    val name: String,

    @ColumnInfo(name = "subcriber_email")
    val email: String,

    )

### Create interface dao

@Dao
interface SubcriberDao {

    @Insert
    suspend fun insertSubcriber(subcribers: Subcribers)

    @Update
    suspend fun updateSubcriber(subcribers: Subcribers)

    @Delete
    suspend fun deleteSubcriber(subcribers: Subcribers)

    @Query("DELETE FROM subcriber_data_table")
    suspend fun deleteAll()

    // do not need to call suppend function 
    //this function return LiveData, room lib do its work from background thread
    @Query("SELECT * FROM subcriber_data_table")
    fun getAllSubcriber(): LiveData<List<Subcribers>>

}

### Create Room Database Class

Create abstract class for room database class
// need annotate @Database
// entities = entity class
// version: very importance when migration
@Database(entities = [Subscribers::class], version = 1)

abstract class SubscriberDatabase:RoomDatabase() {
abstract val subscriberDao: SubscriberDao

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null
        fun getInstance(context: Context): SubscriberDatabase? {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return INSTANCE
            }
        }
    }

}

### Create repository

follow MVVM pattern
MVVM stands for Model, View, ViewModel.

# Model: This is the data layer of the application. Data layer

# classes should not directly communicate with the View.

# Communications should happen through the ViewModels.

# View: This is the presentation(User Interface) layer of the

# application . It observes(LiveData or Flow) the ViewModel. It

# should not hold any application logic related codes.

# ViewModel: ViewModel means "a model for a view". Usually

# each activity has its own ViewModel. ViewModel acts as a link

# between the Model and the View(activity and fragments). It’s

# responsible for transforming the data from the Model and

# from the view to the model. In some situations, It also uses

# callbacks to update the View.

class SubscriberRepository(private val dao: SubscriberDao) {
// no require to call this getAllSubscriber() function from a background thread
val subscribers = dao.getAllSubscriber()

    suspend fun insert(subscribers: Subscribers) {
        dao.insertSubscriber(subscribers)
    }

    suspend fun update(subscribers: Subscribers) {
        dao.updateSubscriber(subscribers)
    }

    suspend fun delete(subscribers: Subscribers) {
        dao.deleteSubscriber(subscribers)
    }
    
    suspend fun deleteAll() {
        dao.deleteAll()
    }

}

### Create ViewModel

### Create VieModel Factory

### SingleLiveEvent

https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150

source code
/**
* Used as a wrapper for data that is exposed via a LiveData that represents an event.
  */
  open class Event<out T>(private val content: T) {

  var hasBeenHandled = false
  private set // Allow external read but not write

  /**
    * Returns the content and prevents its use again.
      */
      fun getContentIfNotHandled(): T? {
      return if (hasBeenHandled) {
      null
      } else {
      hasBeenHandled = true
      content
      }
      }

  /**
    * Returns the content, even if it's already been handled.
      */
      fun peekContent(): T = content
    }

### Check validation email address
if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches())

### Using Flow Instead Of LiveData






