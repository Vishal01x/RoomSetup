package com.exa.android.quotes

import androidx.room.TypeConverter
import java.util.Date

class Convertors {

    @TypeConverter  // to denote android that it is used whenever we pass date obj

    fun fromDateToLong(value : Date) : Long{
        return value.time
    }

    @TypeConverter // this is used to retrive data obj when we pass long time
    fun fromLongToDate(value: Long) : Date{
        return Date(value)
    }

}
/*
Behind Scene :
 when data obj is passed in database the room use type converters to customize it and convert in long
 when we retrive data from database then again android use converters and convert the data from long to date
 to apply convertes we annotate them with TypeConverter and in Database class  we apply following annotation
 @TypeConverted(Converter::class.jave)
 */