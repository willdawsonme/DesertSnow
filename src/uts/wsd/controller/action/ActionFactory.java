package uts.wsd.controller.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Action Factory
 * - Maps request strings to Actions
 */
public class ActionFactory {
    private static final Map<String, Action> actions;

    /* Static listing of all request maps */
    static {
        actions = new HashMap<String, Action>();
        actions.put("GET/index", new IndexAction());
        actions.put("POST/login", new LoginAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/article", new ArticleAction());
        actions.put("POST/article/new", new ArticleNewAction());
        actions.put("GET/article/delete", new ArticleDeleteAction());
        actions.put("GET/author", new AuthorAction());
        actions.put("GET/account", new AccountAction());
    }

    /* Returns the Action object for the specified request string */
    public static Action getAction(HttpServletRequest request) {
        String requestString = request.getMethod() + request.getServletPath();
        Action action = actions.get(requestString);
        if (action == null) action = new DefaultAction(); // Returns the default action if no map is specified.
        // Debug
        System.out.println("RequestMediator: " + requestString + " - " + action.getClass().getSimpleName());
        return action;
    }
}