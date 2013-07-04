package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
    	return show("Index");
    }   	
  
    //public static Result show(String page) {
    public static Result show(String page) {
    	String user = session("userSessionCount");
    	
    	if(user == null || user.length()<1){
    		session("userSessionCount", "1");
    		response().setContentType("text/html; charset=UTF-8");
    		return ok("<head><title>playTestApp :: "+ page +"</title></head> <h1>Hello " + page + " World!</h1><br/><h3>Creating your session!!!</h3>", "UTF-8");
    	}
    	else{
    		String htmlMsg = "";
    		int userSessionCount = Integer.parseInt(user);
    		if(userSessionCount >10){
    			session("userSessionCount", "1");
    			htmlMsg ="<head><title>playTestApp :: "+ page +"</title></head> <h1>Hello " + page + " World!</h1><br/><h3>Your session has been renewed. You have refreshed this page more than 10 times :)</h3>"; 
    		}
    		else {
    			session("userSessionCount", String.valueOf(userSessionCount+1));
    			htmlMsg ="<head><title>playTestApp :: "+ page +"</title></head> <h1>Hello " + page + " World!</h1><br/><h3>You have refreshed this page " +String.valueOf(userSessionCount)+ " time(s)!!!</h3>"; 
    		}
    		response().setContentType("text/html;");
    		return ok(htmlMsg);
    	}
    }
}
