package com.coding.smkcoding_project_2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coding.smkcoding_project_2.dao.TutorialDao
import com.coding.smkcoding_project_2.model.TutorialModel

@Database(entities = arrayOf(TutorialModel::class), version = 1, exportSchema = false)
public abstract class TutorialDatabase : RoomDatabase() {

    abstract fun tutorialDao(): TutorialDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TutorialDatabase? = null

        fun getDatabase(context: Context): TutorialDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TutorialDatabase::class.java,
                    "tutorial_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
