package org.example

import java.io.File
import java.io.FileWriter

class Codegen(api: OpenApi) {
    private val _api: OpenApi = api

    init {

    }

    private fun getInfo(): String {
//        val title = if (_api.info.title != null) _api.info.title else "None"
        val title = _api.info.title.replace(' ', '_')
        val version = _api.info.version
        val description = (_api.info.description ?: "None")

        return "// Description: ${description}\n" +
                "libsl \"1.0.0\";\n" +
                "library $title\n" +
                "\tversion \"${version}\"\n" +
                "\tlanguage \"OpenAPI\";\n"
    }

    private fun getServers(): String {
        val servers = _api.servers
        var output = "// Servers:\n"
        for (server in servers) {
            output += "// ${server.url}\n" +
                    "// \tDescription: ${server.description ?: ""}"
        }
        return output
    }

    private fun getPaths(): String {
        var output = "// Paths:\n"
        val paths = _api.paths ?: mapOf()
        paths.forEach { path ->
//            println("${path.key} : ${path.value}")
            val name = path.key.replace('/', '_').replace("{", "").replace("}", "")
            output += getOpearations(name, path.value)

        }
        return output
    }

    private fun getOpearations(name: String, path: Path): String {
        var output = "\n// #-# Path $name operations:\n"
        if (path.get != null) {
            output += operationGet(name, path.get)
        }
        if (path.put != null) {
            output += operationPut(name, path)
        }
        return output
    }

//    private fun getScheme () : String {
//
//    }

    private fun operationGet(name: String, operation: Request): String {
        var output = "// #-# \tGet operation:\n"

        val types: MutableMap<String, String> = mutableMapOf()
        operation.responses?.forEach { response ->
            val code = response.key
            types += Pair(code, "t${name}_get_${code}")
            output += "type t${name}_get_${code} {\n" +
                    "// ${response.value.description ?: ""}\n" +
                    "\n" + // TODO: Add type information
                    "}\n"

        }

        output += "type t${name}_get {\n"
        types.forEach { item ->
            output += "\tresponse${item.key}: ${item.value};\n"
        }
        output += "}\n"

        output += "@Get\n"
        output += "fun ${name}_get ( " +
                "" + // TODO: Add parameters
                " ) : t${name}_get {}\n"

        return output
    }

    private fun operationPut(name: String, path: Path): String {
        // TODO: Finish
        var output = "// \tPut operation:\n"
        // Generate type
        output += "type ${name}#put () {\n" +
                "\n" +
                "}\n"
        // Generate function
        output += "fun ${name}#put () : {}"

        return output
    }


    private fun getContents(): String {
        var content: String = ""
        content += getInfo()
        content += "\n\n"
        content += getServers()
        content += "\n\n"
        content += getPaths()
        return content
    }

    fun generate(destination: String, print: Boolean = false) {
        println("File path: $destination")

        if (print) {
            println("#########")
            println(getContents())
            return
        }
        val output = File(destination)
        val writer = FileWriter(output)
        writer.write(getContents())

        writer.close()
    }


}