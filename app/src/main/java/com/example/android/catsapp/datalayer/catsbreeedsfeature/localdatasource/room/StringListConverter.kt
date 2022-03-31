package com.example.android.catsapp.datalayer.catsbreeedsfeature.localdatasource.room

import androidx.room.TypeConverter

class ImagesUrlConverter {
    @TypeConverter
    fun fromImagesUrls(list: List<String>) =
        list.joinToString(separator = "@")

    @TypeConverter
    fun toImagesUrls(str: String) =
        str.split("@")
}