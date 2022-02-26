package com.example.learnsource

import javax.inject.Inject

class LocalDataSource
@Inject
constructor(
 private val  dao:Dao
)
{

    suspend fun MainItemInsert(itemMainList: ItemMainList)=dao.MainItemInsert(itemMainList)

    fun getAllMainItems()=dao.getAllMainItems()

    suspend  fun updateMainItem(itemMainList:ItemMainList)=dao.updateItem(itemMainList)
}