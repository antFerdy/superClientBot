
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Rustem/Documents/MyApps/superClientBotBackend/superClientBot/conf/routes
// @DATE:Sat Feb 18 18:22:39 ALMT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
