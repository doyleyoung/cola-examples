package com.github.bmsantos.cola.kotlin.handler

interface Validable {
    fun isValid(): Boolean;
}

fun Validable?.isValid():Boolean = if (this == null) true else this.isValid()