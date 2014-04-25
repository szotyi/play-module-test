package controllers.test

import play.api._
import play.api.mvc._

object Test extends Controller {
  
  def index = Action {
    Ok("test")
  }
  
}
