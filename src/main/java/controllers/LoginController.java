package controllers;

import database.DataService;
import entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dell on 20.07.2015.
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service = new DataService();
        List<Role> roles = service.loadRoles();
        req.setAttribute("roles", roles);

        req.getRequestDispatcher("/WEB-INF/JSP/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        int role = new Integer(req.getParameter("role"));
        DataService service = new DataService();
        if (service.userLogin(name, password, role)){
        req.setAttribute("username", name);
        req.getRequestDispatcher("/WEB-INF/JSP/home.jsp").forward(req, resp);
        } else {
            req.setAttribute("username", name);
            req.getRequestDispatcher("/WEB-INF/JSP/login.jsp").forward(req, resp);
        }
    }
}

//вытянуть всех юзеров по имени паролю и проверить в связанной таблице наличие роли нужного юзера
//сделать метод кот.возвр булево типа логин(логин, пароль, роль) возвр можно ли входить
