package com.example.ulavto.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE favorite == 1")
    fun getFavoriteItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE cart == 1")
    fun getCartItems(): Flow<List<Item>>

}