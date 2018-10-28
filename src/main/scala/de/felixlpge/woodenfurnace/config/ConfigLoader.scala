package de.felixlpge.woodenfurnace.config

import java.io.{BufferedWriter, File, FileWriter}

import com.google.gson.JsonParser

import scala.io.Source



class ConfigLoader(filePath: String, template: String) {
  if (!new File(filePath).exists()){
    val file = new File(filePath)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(template)
    bw.close()
  }
  var json = Source.fromFile(filePath).getLines().mkString
  var parser = new JsonParser
  var element = parser.parse(json)

  def getConfigOption(option: String): String ={
    element.getAsJsonObject.get(option).getAsString
  }

}
