package com.example.booksapp.utils

fun addHttps(input: String): String {
    return if (input.startsWith("http://")) {
        input.replaceFirst("http://", "https://")
    } else {
        input // Return the original string if it doesn't start with "http://"
    }
}