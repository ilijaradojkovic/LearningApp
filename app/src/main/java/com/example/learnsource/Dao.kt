package com.example.learnsource

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  MainItemInsert(itemMainList: ItemMainList)

    @Query("SELECT * FROM ItemTable")
    fun getAllMainItems():LiveData<List<ItemMainList>>

    @Update
   suspend fun updateItem(itemMainList: ItemMainList)
}