package org.example

import java.io.File
import java.io.FileWriter

class Codegen(api: OpenApi) {
    private val _api: OpenApi = api

    init {
        println("Init block")
//        println(_api.toPrettyString())
    }

    private fun getName(): String {
//        val title = if (_api.info.title != null) _api.info.title else "None"
        val title = _api.info.title
        return "api <$title>;"
    }

    private fun getContents(): String{
        var content: String = ""

        content += getName()

        return content
    }

    fun generate(destination: String) {
        println("File path: $destination")
        val output = File(destination)

        val writer = FileWriter(output)


        writer.write(getContents())

        writer.close()
    }


}