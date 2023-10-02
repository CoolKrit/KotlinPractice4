package com.example.kotlinpractice4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "age")
    val age: Int,

    )