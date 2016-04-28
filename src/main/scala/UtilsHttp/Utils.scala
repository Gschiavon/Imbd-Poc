package UtilsHttp

import scala.util.parsing.json._
import org.apache.http._
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient

object Utils {

  /**
    *
    * @param title of the movie
    * @return the url to look for the movie
    */
  def composeUrl(title: String): String = {
    val titleParsed = title.replace(" ", "+")
    s"http://www.omdbapi.com/?t=$titleParsed"
  }
  /**
    * Returns the info from the website
    * @param url of the web
    * @return string with the info
    */
  def get(url: String) = scala.io.Source.fromURL(url).mkString

  /**
    * Parse from String to JSON
    * @param value the json in string format
    * @return the json
    */
  def parseJson(value: String): Map[String, String] = {
    JSON.parseFull(get(value)).get.asInstanceOf[Map[String, String]]
  }

  /**
    * match the json with a given key to obtain the value
    * @param key
    * @param json
    * @tparam K
    * @tparam V
    * @return the value of the key
    */
  def getString[K,V](key: K, json: Map[K, V]): String = {
    json.get(key) match {
      case Some(value) => value.toString
      case None => "no match"
    }
  }

  def getMovie(title: String, keys: List[String]): String = {
    var result = ""
    val url = composeUrl(title)
    val json = parseJson(url)
    for (values <- keys)
    result +=s"  ${getString(values,json)} "
  result
  }


}
