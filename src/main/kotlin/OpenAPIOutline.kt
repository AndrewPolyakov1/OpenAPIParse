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
    // TODO: Make Path class
    val paths: Map<String, Path>?,
    // TODO: Add separate class for items
    val components: Map<String, Any>?,

    )

data class Info(
    val version: String,
    val title: String,
    val description: String?,
    val termsOfService: String?,
    // TODO: Add fields
//    val contact,
//    val license

)

data class Server(
    @JsonProperty("url")
    val url: String,
    val description: String?,
    val variables: Map<String, ServerVariable>?
)

data class ServerVariable(
    val enum: List<String>?,
    val default: String,
    val description: String?
)

data class Security(
    @JsonProperty("BasicAuth")
    val BasicAuth: List<Any>
)

data class Path(
    val get: Request?,
    val post: Request?,
    val put: Request?,
    val delete: Request?,
    val options: Request?,
    val head: Request?,
    val patch: Request?,
    val trace: Request?,
    val servers: List<Server>?,
    // TODO: parameters objects list

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
//    val schema: Map<String, Any>
    val schema: Schema

)

//data class Schema (
//    val type: String,
//    val items: Array<Any>?,
//    val properties: Map<String, Any>?
//
//
//)

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
    val properties: Map<String, Any>?,
    // TODO: Add separate class for items
    val items: Map<String, Any>?,
    val `$ref`: String?,
    val summary: String?,
    val description: String?
)

data class Properties(
    val message: Message?
)

data class Message(
    val type: String
)