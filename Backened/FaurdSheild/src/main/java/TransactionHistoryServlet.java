
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Data.Transaction;
import com.service.FraudService;

public class TransactionHistoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();

        // Get Account Number
        String accountNumber = req.getParameter("accountNumber");

        FraudService f = new FraudService();

        // Get transaction history using Account Number
        List<Transaction> history = f.logic6_viewHistory(accountNumber);

        out.println("<html>");
        out.println("<head><title>Transaction History</title></head>");
        out.println("<body>");

        out.println("<h2>Transaction History</h2>");

        // Display Account Number
        out.println("<h3>Account Number: " + accountNumber + "</h3>");

        // Display Total Transactions
        out.println("<h3>Total Transactions: " + history.size() + "</h3>");

        if (history.isEmpty()) {

            out.println("<p>No transaction history found for this account number.</p>");

        } else {

            out.println("<table border='1' cellpadding='10'>");

            out.println("<tr>");
            out.println("<th>History ID</th>");
            out.println("<th>Account Number</th>");
            out.println("<th>Transaction Type</th>");
            out.println("<th>Amount</th>");
            out.println("<th>Receiver Account</th>");
            out.println("<th>Description</th>");
            out.println("<th>Status</th>");
            out.println("<th>Date</th>");
            out.println("</tr>");

            for (Transaction t : history) {

                out.println("<tr>");

                out.println("<td>" + t.getTransactionId() + "</td>");
                out.println("<td>" + t.getAccountNumber() + "</td>");
                out.println("<td>" + t.getTransactionType() + "</td>");
                out.println("<td>" + t.getAmount() + "</td>");
                out.println("<td>" + t.getReceiverAccount() + "</td>");
                out.println("<td>" + t.getDescription() + "</td>");
                out.println("<td>" + t.getTransactionStatus() + "</td>");
                out.println("<td>" + t.getTransactionDate() + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("<br><br>");
        out.println("<a href='http://127.0.0.1:5501/CustomerDashboard.html'>Back to Dashboard</a>");

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
