

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mypkg.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class RegistrationServlet extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	
	public void init() {
		try {
			con = Utility.connect();
			String sql = "INSERT INTO USERS VALUES(?,?,?,?,?)";
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
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		
		//process the data
		try {
			
			ps.setString(1, userid);
			ps.setString(2, password);
			ps.setString(3,  name);
			ps.setString(4,  address);
			ps.setString(5, mobile);
			
			ps.executeUpdate();
			
			
			
			out.println("<html>");
			out.println("<body>");
			out.println("<h3>REGISTERED</h3>");
			out.println("<h4><a href=index.jsp>Login Now</a></h4>");
			
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
