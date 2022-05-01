package com.example.convidadosapp.repository.constants

class GuestConstants private constructor(){

    companion object{
        const val GUESTID = "guestId"
    }

    object FILTER{
        val EMPTY = 0
        val PRESENTE = 1
        val ABSENT = 2
    }
}