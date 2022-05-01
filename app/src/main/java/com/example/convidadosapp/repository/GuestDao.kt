package com.example.convidadosapp.repository;

import androidx.room.*

import com.example.convidadosapp.service.model.GuestModel;

@Dao
public interface GuestDao {
    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM GuestModel WHERE id = :id")
    fun load(id: Int): GuestModel

    @Query("SELECT * FROM GuestModel")
    fun getInvited(): List<GuestModel>

    @Query("SELECT * FROM GuestModel WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM GuestModel WHERE presence = 0")
    fun getAbsent(): List<GuestModel>
}
