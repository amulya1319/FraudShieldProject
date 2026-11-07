

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Data.User;
import com.service.FraudService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String email = req.getParameter("email");
        String password = req.getParameter("password");

        User u = new User();

        u.setEmail(email);
        u.setPassword(password);

        FraudService f = new FraudService();

        int result = f.logic2_login(u);

        if (result == 1) {
        	res.sendRedirect("http://127.0.0.1:5501/CustomerDashboard.html");
        } else {
            res.setContentType("text/html");
            res.getWriter().println("<h2>Invalid Email or Password</h2>");
            res.getWriter().println("<a href='Login.html'>Try Again</a>");
        }
    }
}



