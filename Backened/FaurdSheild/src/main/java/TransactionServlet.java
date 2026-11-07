

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Data.Transaction;
import com.service.FraudService;

public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 String email = req.getParameter("email");
	        String accountNumber = req.getParameter("accountNumber");
	        String transactionType = req.getParameter("transactionType");
	        String amount = req.getParameter("amount");
	        String receiverAccount = req.getParameter("receiverAccount");
	        String description = req.getParameter("description");

	        Transaction t = new Transaction();

	        t.setEmail(email);
	        t.setAccountNumber(accountNumber);
	        t.setTransactionType(transactionType);
	        t.setAmount(Double.parseDouble(amount));
	        t.setReceiverAccount(receiverAccount);
	        t.setDescription(description);
	        t.setTransactionStatus("Pending");

	        FraudService f = new FraudService();

	        
	        int result = f.logic4_save(t);

	        res.setContentType("text/html");
	        res.setCharacterEncoding("UTF-8");

	        if (result > 0) {

	            int historyResult = f.logic5_saveHistory(t);

	            res.getWriter().println("<html><body>");

	            res.getWriter().println("<h1>Transaction Saved Successfully!</h1>");

	            if (historyResult > 0) {
	                res.getWriter().println("<p>Transaction History Saved Successfully!</p>");
	            } else {
	                res.getWriter().println("<p>Transaction saved, but History saving failed.</p>");
	            }

	            res.getWriter().println(
	            	    "<br><br><a href='http://127.0.0.1:5501/CustomerDashboard.html'>Back to Dashboard</a>"
	            	);
	            res.getWriter().println("</body></html>");

	        } else {

	            res.getWriter().println("<html><body>");

	            res.getWriter().println("<h2>Transaction Could Not Be Saved</h2>");

	            res.getWriter().println("<a href='http://192.168.0.123:5500/Transaction.html'>Back</a>");

	            res.getWriter().println("</body></html>");
	        }
	}
}
	        
