package com.example.learnsource

import javax.inject.Inject

class Repository
@Inject
constructor(
 private val  localDataSource: LocalDataSource
)
{
    suspend fun  MainItemInsert(itemMainList: ItemMainList)=localDataSource.MainItemInsert(itemMainList)


    fun getAllMainItems()=localDataSource.getAllMainItems()

    suspend fun updateMainItem(itemMainList:ItemMainList)=localDataSource.updateMainItem(itemMainList)
}