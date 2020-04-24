package com.belsoft.cosulbio.database

interface IDbRepository {

    fun addUser(user: User): Long

    fun getUsers(): List<User>

    fun findUserById(id: Long): User

    fun findUserByUsernameAndPassword(username: String, password: String): User?

    fun deleteUser(user: User)

    fun deleteAllUsers()

}