package org.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.nio.file.Path


fun main() {
    val path = "C:\\Coding\\ProjectOpenAPI\\LibSLParse_Gradle\\src\\main\\resources\\test_spec.yaml"
    val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
    val obj: OpenApi = mapper.readValue(Path.of(path).toFile())
    println(obj.toPrettyString(4))
}