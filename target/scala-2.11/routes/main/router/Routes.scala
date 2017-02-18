
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Rustem/Documents/MyApps/superClientBotBackend/superClientBot/conf/routes
// @DATE:Sat Feb 18 18:22:39 ALMT 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  BotController_0: controllers.BotController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    BotController_0: controllers.BotController
  ) = this(errorHandler, BotController_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, BotController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.BotController.getMain()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val controllers_BotController_getMain0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_BotController_getMain0_invoker = createInvoker(
    BotController_0.getMain(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BotController",
      "getMain",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case controllers_BotController_getMain0_route(params) =>
      call { 
        controllers_BotController_getMain0_invoker.call(BotController_0.getMain())
      }
  }
}
