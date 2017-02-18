
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Rustem/Documents/MyApps/superClientBotBackend/superClientBot/conf/routes
// @DATE:Sat Feb 18 18:22:39 ALMT 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseBotController BotController = new controllers.ReverseBotController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseBotController BotController = new controllers.javascript.ReverseBotController(RoutesPrefix.byNamePrefix());
  }

}
