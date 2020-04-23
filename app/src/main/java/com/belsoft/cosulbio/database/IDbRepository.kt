package com.belsoft.cosulbio.database

interface IDbRepository {

    fun addQuote(quote: User): Long

    fun getQuotes(): List<User>

    fun findById(id: Long): User

    fun deleteUser(user: User)

    fun deleteAllUsers()

}