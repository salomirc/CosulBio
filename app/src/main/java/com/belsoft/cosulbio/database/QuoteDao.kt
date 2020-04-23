package com.belsoft.cosulbio.database

import androidx.room.*

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table")
    fun getQuotes(): List<User>

    @Query("SELECT * FROM quote_table WHERE id LIKE :id LIMIT 1")
    fun findById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addQuote(quote: User): Long

    @Delete
    fun deleteQuote(quote: User)

    @Query("DELETE FROM quote_table")
    fun deleteAllQuotes()
}