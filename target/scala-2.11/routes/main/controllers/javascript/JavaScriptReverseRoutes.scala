
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Rustem/Documents/MyApps/superClientBotBackend/superClientBot/conf/routes
// @DATE:Sat Feb 18 18:22:39 ALMT 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:1
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:1
  class ReverseBotController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def getMain: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BotController.getMain",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}
