package uts.wsd.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.HashMap;

public class ActionFactory {
    private static final Map<String, Action> actions;

    static {
        actions = new HashMap<String, Action>();
        actions.put("GET/", new IndexAction());
        actions.put("POST/login", new LoginAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/article", new ArticleAction());
        actions.put("POST/newarticle", new NewArticleAction());
        actions.put("GET/author", new AuthorAction());
    }

    public static Action getAction(HttpServletRequest request) {
        System.out.println("================================\n" + request.getMethod() + request.getServletPath());
        Action action = actions.get(request.getMethod() + request.getServletPath());
        return (action != null ? action : new DefaultAction());
    }
}