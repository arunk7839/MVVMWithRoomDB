package com.c1ctech.mvvmwithroomdb.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "eventTable")
data class Event(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    val id: Int?,

    @ColumnInfo(name = "event_title")
    val title: String?,

    @ColumnInfo(name = "event_description")
    val description: String?

)