package com.example.ulavto.adapter

import java.io.Serializable

data class ItemParts(val id: Int? = null, val tag: String, val nameN: String, val description: String, val  cost: Int, val avatarUrl: Int, val favorite: Int, val cart: Int) :
    Serializable
