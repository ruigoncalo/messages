package com.ruigoncalo.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ruigoncalo.data.cache.model.UserCached

@Dao
abstract class UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(users: List<UserCached>)

    @Query("SELECT * FROM users")
    abstract fun getUsers(): List<UserCached>

    @Query("SELECT * FROM users WHERE id = :userId")
    abstract fun getUser(userId: Long): UserCached
}