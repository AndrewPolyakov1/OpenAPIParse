package org.example

import com.fasterxml.jackson.annotation.JsonProperty

fun Any.toPrettyString(indentSize: Int = 2) = " ".repeat(indentSize).let { indent ->
    toString()
        .replace(", ", ",\n$indent")
        .replace("(", "(\n$indent")
        .dropLast(1) + "\n)"
}

data class OpenApi(
    val openapi: String,
    val info: Info,
    val servers: List<Server>,
    val security: List<Security>?,
    val paths: Map<String, RequestHandler>?,
    val components: Map<String, Any>?,

)

data class Info(
    val version: String,
    val title: String,
    val description: String?
)

data class Server(
    @JsonProperty("url")
    val url: String
)

data class Security(
    @JsonProperty("BasicAuth")
    val BasicAuth: List<Any>
)

data class RequestHandler(
    val get: Request?,
    val post: Request?
)

data class Request(
    val description: String?,
    @JsonProperty("parameters")
    val parameters: List<Parameter>?,
    val responses: Map<String, Response>?,
    val requestBody: RequestBody?
)

data class Response(
    val description: String?,
    val content: Map<String, ResponseContent>?
)

data class ResponseContent(
    // TODO: Add separate class for items
    val schema: Map<String, Any>
)

data class RequestBody(
    val required: Boolean,
    val content: Map<String, ResponseContent>?
)

data class Parameter(
    val name: String,
    val required: Boolean?,
    val description: String?,
    val `in`: String?,
    val schema: Schema?
)

data class Schema(
    val type: String?,
    val properties: Properties?,
    // TODO: Add separate class for items
    val items: Map<String, Any>?,
    val `$ref`: String?
)

data class Properties(
    val message: Message?
)

data class Message(
    val type: String
)