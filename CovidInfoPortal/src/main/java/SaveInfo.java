

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mypkg.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class SaveInfo extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	
	public void init() {
		try {
			con = Utility.connect();
			//String sql = "INSERT INTO covidinfo(idate,state,total,active,deaths,userid) VALUES(?,?,?,?,?,? )";
			String sql = "INSERT INTO covidinfo(idate,state,total,active,deaths,userid) VALUES(now(),?,?,?,?,? )";

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
		//state=&userid=&total=&active=&deaths=
		String userid = request.getParameter("userid");
		String state = request.getParameter("state");
		int total = Integer.parseInt(request.getParameter("total"));
		int active = Integer.parseInt(request.getParameter("active"));
		int deaths = Integer.parseInt(request.getParameter("deaths"));
		//java.util.Date dt = new java.util.Date();
		//long val = dt.getTime();
		//java.sql.Date idate = new java.sql.Date(val);
		//process the data
		try {
			//idate,state,total,active,deaths,userid
			//ps.setDate(1, idate);			
			ps.setString(1, state);
			ps.setInt(2,total);
			ps.setInt(3,active);
			ps.setInt(4,deaths);
			ps.setString(5,userid);
			
			ps.executeUpdate();
			
			
			
			out.println("<html>");
			out.println("<body>");
			out.println("<h3>Information Submited Succesfully</h3>");
			out.println("<h4><a href=stadmindashboard.jsp>Dashboard</a></h4>");
			
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
