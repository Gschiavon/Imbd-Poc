package Movies

import UtilsHttp.Utils

class Movies {

  def findMovieByTitle(title: String): String = {
    Utils.getMovie(title,List.empty)
  }
}
