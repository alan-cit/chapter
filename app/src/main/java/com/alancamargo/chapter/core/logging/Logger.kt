package com.alancamargo.chapter.core.logging

interface Logger {

    fun error(message: String, throwable: Throwable)
}
