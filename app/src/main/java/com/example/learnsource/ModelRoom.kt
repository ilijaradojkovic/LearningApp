package com.example.learnsource

import android.content.Context
import androidx.room.Room
import com.example.learnsource.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ModelRoom {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context:Context): MyDatabase {
        return Room.databaseBuilder(context,MyDatabase::class.java,DATABASE_NAME).build()
    }
    @Provides
    @Singleton
    fun getDao(database:MyDatabase):Dao
    {
        return  database.getDao()
    }



}