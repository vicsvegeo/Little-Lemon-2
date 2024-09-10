package com.example.littlelemon

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

// Define the entity
@Entity(tableName = "menu_item")
data class MenuItemEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)
@Entity
data class MenuItemRoom(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUrl: String
)

@Dao
interface MenuItemDao{
    @Query("SELECT * FROM MenuItemRoom")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemRoom)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemRoom::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun menuItemDao(): MenuItemDao
}