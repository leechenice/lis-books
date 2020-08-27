package lee.servlet;

import lee.model.User;
import lee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = userService.login(username,password);
            if(user == null) {
                resp.sendRedirect("/login.html");
            }else {
                final HttpSession session = req.getSession();
                session.setAttribute("user",user);
                resp.sendRedirect("/");
            }
        } catch (SQLException e) {
            throw new RuntimeException("用户名或密码错误",e);
        }
    }
}
