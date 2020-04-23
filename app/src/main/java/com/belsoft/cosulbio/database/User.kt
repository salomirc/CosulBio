package com.belsoft.cosulbio.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "email")
    val email: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0
)