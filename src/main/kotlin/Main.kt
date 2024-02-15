package org.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.nio.file.Path

fun parseIntoObject(path: Path): OpenApi{
    val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
    val obj: OpenApi = mapper.readValue(path.toFile())
    return obj
}

fun main() {
    val path = "C:\\Coding\\ProjectOpenAPI\\LibSLParse_Gradle\\src\\main\\resources\\test_spec.yaml"
    try {
        val obj = parseIntoObject(Path.of(path))
        val dest = "C:\\Coding\\ProjectOpenAPI\\LibSLParse_Gradle\\src\\main\\resources\\test.lsl"
        val codegen = Codegen(obj)
        codegen.generate(dest)
    } catch (e: Exception){
        System.err.println("Exception occurred: ${e.toString()}")
    }

//    println(obj.toPrettyString(4))


}