package com.example.learnsource

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class TypeConverter {
val gson=Gson()

    @TypeConverter
    fun ListToString(lista: List<ItemMainList>):String{
        val stringBuilder=StringBuilder()
        for (item in lista){
            stringBuilder.append(item.title.toString() + ",")
        }
        return gson.toJson(lista)
    }
    @TypeConverter
    fun StringToList(lista: String):LinkedList<ItemMainList>{

        val token=object: TypeToken<ItemMainList>() {

        }.type
        //da mu damo do znanja da postoji lista unutar tog objekta
        return gson.fromJson(lista, object : TypeToken<LinkedList<ItemMainList?>?>() {}.type)

    }

}