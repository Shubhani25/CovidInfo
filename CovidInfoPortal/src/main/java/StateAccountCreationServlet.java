

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mypkg.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class StateAccountCreationServlet extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	
	public void init() {
		try {
			con = Utility.connect();
			String sql = "INSERT INTO STATEADMINS VALUES(?,?,?,null, null, null, null, 'disabled')";
			ps = con.prepareStatement(sql);
			
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
		//read the data
		String userid = request.getParameter("uid");
		String password = request.getParameter("password");
		String state = request.getParameter("state");
		
		//process the data
		try {
			
			ps.setString(1, userid);
			ps.setString(2, password);
			ps.setString(3,  state);
						
			ps.executeUpdate();
			
			
			
			out.println("<html>");
			out.println("<body>");
			out.println("<h3>ACCOUNT CREATED SUCCESSFULLY!</h3>");
			out.println("<h4><a href=sadmindashboard.jsp>Super Admin Dashboard</a></h4>");
			
			out.println("</body>");
			out.println("</html>");
			
			
		}
		catch(Exception e) {
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
