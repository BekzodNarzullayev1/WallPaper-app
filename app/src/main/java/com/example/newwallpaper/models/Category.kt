package com.example.newwallpaper.models

import java.io.Serializable

data class Category(var name:String,var list: List<Hit>):Serializable
