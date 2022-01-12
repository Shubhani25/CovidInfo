

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mypkg.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class VerifyUser extends HttpServlet {
	private Connection con;
	private PreparedStatement ps1;
	private PreparedStatement ps2;
	public void init() {
		try {
			con=Utility.connect();
			ps1 = con.prepareStatement("Select * from users where email=? and password=?");
			ps2 = con.prepareStatement("Select * from stateadmins where userid=? and password=?");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void destroy() {
		try {
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("uid");
		String pwd = request.getParameter("password");
		String utype = request.getParameter("utype");
		
		if(utype.equals("Super-Admin")) {
			if(id.equals("sadmin@hmail.com") & pwd.equals("1234")) {
				//response.sendRedirect("sadmindashboard.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("sadmindashboard.jsp");
				rd.forward(request, response);
			}
			else {
				out.print("<html><body>");
				out.println("<h3>Invalid Super Admin Id or Password</h3>");
				out.println("<h4><a href=index.jsp>Try Again</a></h4>");
				out.print("</body></html>");
			}
		}
		
		else if(utype.equals("State-Admin")) {
			
			try{
				ps2.setString(1, id);
				ps2.setString(2, pwd);
				
				ResultSet rs = ps2.executeQuery();
				
				boolean found = rs.next();
				if (found) {
					String status = rs.getString("status");
					String uid = rs.getString("userid");
					
					if(status.equals("disabled")) {
						out.println("<html><body>");
						out.println("<h3>Profile Completion Form</h3>");
						out.println("</body></html>");
					}
				}
				
				//oif(id.equals("state@hmail.com") & pwd.equals("state")) {
					//response.sendRedirect("stadmindashboard.jsp");
					
				//}
				else {
					out.print("<html><body>");
					out.println("<h3>Invalid State Admin Id or Password</h3>");
					out.println("<h4><a href=index.jsp>Try Again</a></h4>");
					out.print("</body></html>");
				}
			}
			
			catch(Exception e) {
				out.println(e);
			}
		}	
		
		else {
			try {
				ps1.setString(1, id);
				ps1.setString(2, pwd);
				
				ResultSet rs = ps1.executeQuery();
				
				boolean found = rs.next();
				
				if(found) {
					response.sendRedirect("userdashboard.jsp");
				}
				else {
					out.print("<html><body>");
					out.println("<h3>Invalid User Admin Id or Password</h3>");
					out.println("<h4><a href=index.jsp>Try Again</a></h4>");
					out.print("</body></html>");
				}
			}
			catch(Exception e) {
				out.println(e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
