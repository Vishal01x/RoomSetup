package com.exa.android.quotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.Objects


/*
update=1:

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase :RoomDatabase(){

    abstract fun contactDao():ContactDAO
}


// UPDATE - 2,3
@Database(entities = [User :: class], version = 1) // -> entities are the class that we are using to store data, // version is used to update data for user after launching app
@TypeConverters(Convertors::class)

abstract class UserDatabase : RoomDatabase(){

    abstract fun userDao() : UserDAO

   companion object{
       // Volatile annotation ensures visibility of changes across threads
       @Volatile
       private var INSTANCE : UserDatabase ? = null

       fun getDatabase(context : Context): UserDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context, UserDatabase::class.java, "userDatabase"
                    ).build()
                }
            }
           return INSTANCE!!
       }
   }
}

/*
Another way of writing same code
 companion object {

        @Volatile private var instance: AppDatabase? = null

        // Function to get the database instance
        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { instance = it }
            }
        }
    }
 */
 */

@Database(entities = [User :: class], version = 2) // -> updating version as the schema of table changes ie new column is added
@TypeConverters(Convertors::class)

abstract class UserDatabase : RoomDatabase(){

    abstract fun userDao() : UserDAO



    companion object{

        // this migrattion for isActive only
        val migration_1_2 = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE user ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }
        }

            // Volatile annotation ensures visibility of changes across threads
        @Volatile
        private var INSTANCE : UserDatabase ? = null

        fun getDatabase(context : Context): UserDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context, UserDatabase::class.java, "userDatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
