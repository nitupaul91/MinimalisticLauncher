package me.pauln.minimalisticlauncher.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.pauln.minimalisticlauncher.data.model.App

@Database(entities = [App::class], version = 1, exportSchema = false)

abstract class AppsSelectionDatabase : RoomDatabase() {

    abstract fun appsDao(): AppsSelectionDao

    companion object {
        private var instance: AppsSelectionDatabase? = null

        fun getInstance(context: Context): AppsSelectionDatabase? {
            if (instance == null) {
                synchronized(AppsSelectionDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppsSelectionDatabase::class.java, "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}