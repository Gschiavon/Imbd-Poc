package UtilsHttpSpec

import UtilsHttp.Utils
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by gschiavon on 22/04/16.
  */
class UtilsSpec extends WordSpec with Matchers {
  val json = Utils.parseJson( """http://www.omdbapi.com/?t=The+lion+king""")

  "test for a get should be a JSON with the movie" in {
    val result = json
      result should be(Map("Title" -> "The Lion King", "Country" -> "USA", "Writer" -> "Irene Mecchi (screenplay), Jonathan Roberts (screenplay), Linda Woolverton (screenplay), Brenda Chapman (story supervisor), Burny Mattinson (story), Barry Johnson (story), Lorna Cook (story), Thom Enriquez (story), Andy Gaskill (story), Gary Trousdale (story), Jim Capobianco (story), Kevin Harkey (story), Jorgen Klubien (story), Chris Sanders (story), Tom Sito (story), Larry Leker (story), Joe Ranft (story), Rick Maki (story), Ed Gombert (story), Francis Glebas (story), Mark Kausler (story), J.T. Allen (additional story material), George Scribner (additional story material), Miguel Tejada-Flores (additional story material), Jenny Tripp (additional story material), Bob Tzudiker (additional story material), Christopher Vogler (additional story material), Kirk Wise (additional story material), Noni White (additional story material)", "Metascore" -> "83", "imdbVotes" -> "621,223", "Year" -> "1994", "Poster" -> "http://ia.media-imdb.com/images/M/MV5BMjEyMzgwNTUzMl5BMl5BanBnXkFtZTcwNTMxMzM3Ng@@._V1_SX300.jpg", "Director" -> "Roger Allers, Rob Minkoff", "Language" -> "English, Swahili, Xhosa, Zulu", "Plot" -> "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.", "Response" -> "True", "Runtime" -> "89 min", "Awards" -> "Won 2 Oscars. Another 29 wins & 27 nominations.", "imdbRating" -> "8.5", "Released" -> "24 Jun 1994", "imdbID" -> "tt0110357", "Genre" -> "Animation, Adventure, Drama", "Actors" -> "Matthew Broderick, Jonathan Taylor Thomas, James Earl Jones, Jim Cummings", "Type" -> "movie", "Rated" -> "G"))
  }

  "test for a getString should be the value of the title of they json" in {
    val result = Utils.getString("Title", json)
    result should be("The Lion King")
  }

  "test for a getString should be the value of the country of they json" in {
    val result = Utils.getString("Country", json)
    result should be("USA")
  }

  "test for compose an URL should return the url with the title of the movie" in {
    val result = Utils.composeUrl("The lion king")
    result should be("""http://www.omdbapi.com/?t=The+lion+king""")
  }

  "testfor get a movie should return \"Title\", \"Released\", \"Genre\", \"Country\"" in {
    val result = Utils.getMovie("The lion king", List("Title", "Released", "Genre", "Country"))
    result should be("  The Lion King   24 Jun 1994   Animation, Adventure, Drama   USA ")
  }

  "tes tfor get a movie should return empty chain" in {
    val result = Utils.getMovie("The lion king", List())
    result should be("")
  }
}
