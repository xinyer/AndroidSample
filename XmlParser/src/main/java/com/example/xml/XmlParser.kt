package com.example.xml

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class XmlParser {

    fun go() {
        val xmlDeserializer = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val simple = Simple("x","y")
        val test = Test(simple)
        println(xmlDeserializer.writeValueAsString(test))

        val stringToParse = "<Test><Simple><x>1</x><y>2</y></Simple></Test>"
        val finalObject = xmlDeserializer.readValue(stringToParse, Test::class.java)
        println(finalObject.simple.x)
    }
}