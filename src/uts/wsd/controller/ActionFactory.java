package uts.wsd.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.HashMap;

public class ActionFactory {
    private static final Map<String, Action> actions;

    static {
        actions = new HashMap<String, Action>();
        actions.put("GET/index", new IndexAction());
        actions.put("POST/login", new LoginAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/article", new ArticleAction());
        actions.put("POST/newarticle", new NewArticleAction());
        actions.put("GET/author", new AuthorAction());
    }

    public static Action getAction(HttpServletRequest request) {
        String requestString = request.getMethod() + request.getServletPath();
        Action action = actions.get(requestString);
        if (action == null) action = new DefaultAction();
        // Debug
        System.out.println("RequestMediator: " + requestString + " - " + action.getClass().getSimpleName());
        return action;
    }
}