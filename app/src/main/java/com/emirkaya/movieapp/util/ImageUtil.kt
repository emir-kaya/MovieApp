package com.emirkaya.movieapp.util

object ImageUtil {
    fun buildImageUrl(path: String?): String {
        return "${Constants.BASE_IMG_URL}$path"
    }
}