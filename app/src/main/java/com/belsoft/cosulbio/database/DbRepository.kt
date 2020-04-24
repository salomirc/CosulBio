package com.belsoft.cosulbio.database

import android.content.Context

class DbRepository private constructor(appContext: Context): IDbRepository {

    private val userDao: UserDao = RoomDatabaseApp.getInstance(appContext).userDao()

    override fun addUser(user: User): Long {
        return userDao.addUser(user)
    }

    override fun getUsers(): List<User> {
        return userDao.getUsers()
    }

    override fun findUserById(id: Long): User {
        return userDao.findUserById(id)
    }

    override fun findUserByUsernameAndPassword(username: String, password: String): User? {
        return userDao.findUserByUsernameAndPassword(username, password)
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