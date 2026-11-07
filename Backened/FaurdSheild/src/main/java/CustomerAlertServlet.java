

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Data.Alert;
import com.service.FraudService;


public class CustomerAlertServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accountNumber = req.getParameter("accountNumber");

        FraudService f = new FraudService();

        List<Alert> alerts = f.logic8_viewAlerts(accountNumber);

        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        res.getWriter().println("<html><body>");

        res.getWriter().println("<h1>Customer Alerts</h1>");

        if (alerts.isEmpty()) {

            res.getWriter().println("<h3>No alerts found for this account number.</h3>");

        } else {

            res.getWriter().println("<table border='1' cellpadding='10'>");

            res.getWriter().println(
                "<tr><th>Alert ID</th><th>Transaction ID</th>" +
                "<th>Alert Type</th><th>Risk Level</th>" +
                "<th>Description</th><th>Status</th><th>Date</th></tr>"
            );

            for (Alert a : alerts) {

                res.getWriter().println("<tr>");

                res.getWriter().println("<td>" + a.getAlertId() + "</td>");
                res.getWriter().println("<td>" + a.getTransactionId() + "</td>");
                res.getWriter().println("<td>" + a.getAlertType() + "</td>");
                res.getWriter().println("<td>" + a.getRiskLevel() + "</td>");
                res.getWriter().println("<td>" + a.getDescription() + "</td>");
                res.getWriter().println("<td>" + a.getAlertStatus() + "</td>");
                res.getWriter().println("<td>" + a.getAlertDate() + "</td>");

                res.getWriter().println("</tr>");
            }

            res.getWriter().println("</table>");
        }

        res.getWriter().println("<br><br>");

        res.getWriter().println(
            "<a href='http://127.0.0.1:5501/CustomerDashboard.html'>Back to Dashboard</a>"
        );

        res.getWriter().println("</body></html>");
    }
}