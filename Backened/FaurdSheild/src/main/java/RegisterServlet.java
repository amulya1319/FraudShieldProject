

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Data.User;
import com.service.FraudService;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// Bringing frontend HTML data into Servlet

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String accountNumber = req.getParameter("accountNumber");
        String address = req.getParameter("address");
        String terms = req.getParameter("terms");

        // Creating User object

        User u = new User();

        // Calling setter methods

        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setPassword(password);
        u.setAge(Integer.parseInt(age));
        u.setGender(gender);
        u.setAccountNumber(accountNumber);
        u.setAddress(address);

        // Passing User object from Controller to Service

        FraudService f = new FraudService();

        int result = f.logic1_save(u);

        PrintWriter out = res.getWriter();

        out.println("<h2>Registration process completed. Result: " + result + "</h2>");
    }
}
		
		
