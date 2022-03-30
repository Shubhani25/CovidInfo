

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mypkg.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class UpdateStateAdminProfile extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	
	public void init() {
		try {
			con = Utility.connect();
			String sql = "Update stateadmins set password=?, username=?, email=?, address=?, mobile=?, status='enabled' where userid=?"; 
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
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");	
		//process the data
		try {
			ps.setString(1, password);
			ps.setString(2, username);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, mobile);
			ps.setString(6, userid);
			
			ps.executeUpdate();
			
			response.sendRedirect("stadmindashboard.jsp");
			
			
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
