package com.belsoft.cosulbio.database

import android.content.Context

class DbRepository private constructor(appContext: Context): IDbRepository {

    private val userDao: UserDao = RoomDatabaseApp.getInstance(appContext).userDao()

    override fun addQuote(quote: User): Long {
        return userDao.addUser(quote)
    }

    override fun getQuotes(): List<User> {
        return userDao.getUsers()
    }

    override fun findById(id: Long): User {
        return userDao.findUserById(id)
    }

    override fun deleteUser(user: User) {
        return userDao.deleteUser(user)
    }

    override fun deleteAllUsers() {
        return userDao.deleteAllUsers()
    }

    companion object {
        @Volatile private var instance: DbRepository? = null

        fun getInstance(appContext: Context): DbRepository {
            return instance ?: synchronized(this) {
                instance ?: DbRepository(appContext).also { instance = it }
            }
        }
    }
}