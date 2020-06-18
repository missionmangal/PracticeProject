package com.myapplication.rxjava

data class Optional<T>(val value: T?)
fun <T> T?.asOptional() = Optional(this)