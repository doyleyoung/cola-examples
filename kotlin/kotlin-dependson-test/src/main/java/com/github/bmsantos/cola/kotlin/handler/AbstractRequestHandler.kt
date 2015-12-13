package com.github.bmsantos.cola.kotlin.handler

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.bmsantos.cola.kotlin.handler.handlers.EmptyPayload
import com.github.bmsantos.cola.kotlin.handler.model.Model
import spark.Request
import spark.Response
import spark.Route
import java.io.IOException
import java.io.StringWriter

abstract class AbstractRequestHandler<V : Validable>(val valueClass: Class<V>, val model: Model) :
        RequestHandler<V>, Route {

    companion object {
        val KEY_UUID = ":uuid"
        val HTTP_OK = 200
        val HTTP_CREATED = 201
        val HTTP_BAD_REQUEST = 400
        val HTTP_NOT_FOUND = 404
    }

    override fun process(value: V?, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer {
        if (!value.isValid()) {
            return Answer(HTTP_BAD_REQUEST)
        }
        return processImpl(value, urlParams, shouldReturnHtml)
    }

    abstract fun processImpl(value: V?, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer

    override fun handle(request: Request, response: Response): String? {
        try {
            val shouldReturnHtml = shouldReturnHtml(request)
            val urlParams = request.params()
            val objectMapper = ObjectMapper().registerModule(KotlinModule())

            var value: V? = null
            if (valueClass != EmptyPayload::class.java) {
                value = objectMapper.readValue(request.body(), valueClass)
            }

            val answer = process(value, urlParams, shouldReturnHtml)
            response.status(answer.code)

            if (shouldReturnHtml) response.type("text/html")
            else response.type("application/json")

            return answer.body
        } catch (e: JsonMappingException) {
            response.status(400)
            response.body(e.message)
            return e.message
        }
    }
}

fun AbstractRequestHandler.Companion.shouldReturnHtml(request: Request): Boolean {
    val accept: String? = request.headers("Accept")
    return accept?.contains("text/html") ?: false; // Can this be simpler?
}

fun AbstractRequestHandler.Companion.dataToJson(data: Any?): String {
    try {
        val mapper = ObjectMapper();
        mapper.enable(INDENT_OUTPUT);

        val sw = StringWriter();
        mapper.writeValue(sw, data);

        return sw.toString();
    } catch (e: IOException) {
        throw RuntimeException("IOException from a StringWriter?");
    }
}