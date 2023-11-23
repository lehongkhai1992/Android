package com.example.myapplication

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