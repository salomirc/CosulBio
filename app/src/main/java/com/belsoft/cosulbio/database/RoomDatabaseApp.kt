package com.belsoft.cosulbio.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Annotates class to be a Room Database with a table (entity) of the User class
@Database(entities = [User::class],version = 3)
abstract class RoomDatabaseApp : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var instance: RoomDatabaseApp? = null

//        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE user_table "
//                        + " ADD COLUMN last_update INTEGER");
//            }
//        }

        fun getInstance(context: Context): RoomDatabaseApp {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabaseApp::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
//                    .addMigrations(MIGRATION_1_2)
                    .build().also { instance = it }
            }
        }
    }
}