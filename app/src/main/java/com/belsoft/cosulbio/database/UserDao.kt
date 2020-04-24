package com.belsoft.cosulbio.database

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM users_table")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users_table WHERE id LIKE :id LIMIT 1")
    fun findUserById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(user: User): Long

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM users_table")
    fun deleteAllUsers()
}