package com.example.learnsource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [ItemMainList::class],version = 1)
@TypeConverters(com.example.learnsource.TypeConverter::class)
abstract class  MyDatabase: RoomDatabase() {
    abstract fun getDao():Dao


}