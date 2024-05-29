package com.exa.android.quotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user") // -> if there is no table name specified then default it uses class name as table name
class User (
    @PrimaryKey(autoGenerate = true) // -> to auto increament the id we use autoGen
    val id : Long,
    @ColumnInfo("first_name") val firstName : String, // -> ColumnInfo is to use some other name than data class property name
    val lastName : String, // -> if no column then default it uses your data entity name for column name
    //for update-3, applying type converters for complex object
    val date: Date,
    // for update-4: for migration concept we have added a new column "isActive" which will have value 0 or 1
    val isActive :Int
)

/*
 this defines the no. of column will be in the table, basically creating the table
 what will be the unique id to define them.
 Here we can also specify relation between data classes/tables
 */