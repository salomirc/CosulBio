package com.belsoft.cosulbio.database

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM users_table")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users_table WHERE id LIKE :id LIMIT 1")
    fun findUserById(id: Long): User

    @Query("SELECT * FROM users_table WHERE username LIKE :username AND password LIKE:password LIMIT 1")
    fun findUserByUsernameAndPassword(username: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(user: User): Long

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM users_table")
    fun deleteAllUsers()
}