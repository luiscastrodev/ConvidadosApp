package com.example.convidadosapp.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.convidadosapp.repository.constants.DataBaseConstants
import com.example.convidadosapp.service.model.GuestModel
import java.lang.Exception

class GuestRespository private constructor(context: Context) {

    //singleton
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context = context)

    companion object {
        private lateinit var repository: GuestRespository
        fun getInstance(context: Context): GuestRespository {

            if (!::repository.isInitialized) {//verifica se ja foi instanciado
                repository = GuestRespository(context)
            }
            return repository
        }
    }

    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val value = ContentValues()
            value.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            value.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, value)
            true
        } catch (e: Exception) {
            false
        }
    }


    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val value = ContentValues()
            value.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            value.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, value, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun remove(id: Int): Boolean {
        return try {

            val db = mGuestDataBaseHelper.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

             true
        } catch (e: Exception) {
            false
        }
    }

    fun get(id: Int) : GuestModel?{
        var guest : GuestModel? = null

        return try {

            val db = mGuestDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.NAME,DataBaseConstants.GUEST.COLUMNS.PRESENCE)
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val selectionArgs = arrayOf(id.toString())

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
                projection,
            selection,
            selectionArgs,
            null,
            null,
            null)

           if (cursor != null && cursor.count > 0){
               cursor.moveToFirst()

              val name =  cursor.getString(cursor. getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
              val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

               guest = GuestModel(id,name,presence)
           }

            cursor?.close()
            guest

        }catch (e:Exception){
            guest
        }

    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {

            val db = mGuestDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.ID,DataBaseConstants.GUEST.COLUMNS.NAME,DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null)

            if (cursor != null && cursor.count > 0){

                while (cursor.moveToNext()){
                    val id =  cursor.getInt(cursor. getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  cursor.getString(cursor. getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id= id ,name = name, presence = presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list

        }catch (e:Exception){
            list
        }
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {

            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0){

                while (cursor.moveToNext()){
                    val id =  cursor.getInt(cursor. getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  cursor.getString(cursor. getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id= id ,name = name, presence = presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list

        }catch (e:Exception){
            list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {

            val db = mGuestDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.ID,DataBaseConstants.GUEST.COLUMNS.NAME,DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null)

            if (cursor != null && cursor.count > 0){

                while (cursor.moveToNext()){
                    val id =  cursor.getInt(cursor. getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  cursor.getString(cursor. getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    if (presence)continue
                    val guest = GuestModel(id= id ,name = name, presence = presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list

        }catch (e:Exception){
            list
        }
    }
}