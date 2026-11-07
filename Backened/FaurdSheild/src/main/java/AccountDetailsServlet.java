
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Data.Account;
import com.service.FraudService;

public class AccountDetailsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");

        Account a = new Account();
        a.setEmail(email);

        FraudService f = new FraudService();

        Account result = f.logic3_account(a);

        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        if (result != null) {

            String accountNumber = result.getAccountNumber();

            String maskedAccount = "XXXXXX";

            if (accountNumber != null && accountNumber.length() >= 4) {
                maskedAccount += accountNumber.substring(
                    accountNumber.length() - 4
                );
            } else {
                maskedAccount = "XXXXXX";
            }

            res.getWriter().println("<html><body>");

            res.getWriter().println("<h1>Account Details</h1>");

            res.getWriter().println("<p>First Name: "
                    + result.getFirstName() + "</p>");

            res.getWriter().println("<p>Last Name: "
                    + result.getLastName() + "</p>");

            res.getWriter().println("<p>Email: "
                    + result.getEmail() + "</p>");

            res.getWriter().println("<p>Age: "
                    + result.getAge() + "</p>");

            res.getWriter().println("<p>Gender: "
                    + result.getGender() + "</p>");

            res.getWriter().println("<p>Account Number: "
                    + maskedAccount + "</p>");

            res.getWriter().println("<p>Address: "
                    + result.getAddress() + "</p>");

            res.getWriter().println(
            	    "<br><br><a href='http://127.0.0.1:5501/CustomerDashboard.html'>Back to Dashboard</a>"
            	);

            	res.getWriter().println("</body></html>");

        } else {

            res.getWriter().println("<h2>Account details not found</h2>");

        }
    }
}
