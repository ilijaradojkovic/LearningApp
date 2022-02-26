package com.example.learnsource

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learnsource.Constants.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = TABLE_NAME)
data class ItemMainList(val title:String?,
@PrimaryKey(autoGenerate = true)
val ID:Int=0,
val podlista: LinkedList<ItemMainList>?
):Parcelable