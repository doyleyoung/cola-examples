package com.github.bmsantos.cola.kotlin.handler

interface RequestHandler<in V : Validable> {
    fun process(value: V?, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer;
}