package com.belsoft.cosulbio.database

import android.content.Context

class DbRepository private constructor(appContext: Context): IDbRepository {

    private val quoteDao: QuoteDao = RoomDatabaseApp.getInstance(appContext).quoteDao()

    override fun addQuote(quote: User): Long {
        return quoteDao.addQuote(quote)
    }

    override fun getQuotes(): List<User> {
        return quoteDao.getQuotes()
    }

    override fun findById(id: Long): User {
        return quoteDao.findById(id)
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