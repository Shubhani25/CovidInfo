

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class StateDataServlet
 */
public class StateDataServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//this will show the data of that state whose admin is currently logged in
		HttpSession session = request.getSession();
		String state = (String) session.getAttribute("state");
		
		//Fetching data from database
		String sql = "SELECT * from covidinfo where state=?";
		try{
			Connection con = mypkg.Utility.connect();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, state);
			ResultSet rs = ps.executeQuery();
			out.println("<html><body>");
			out.println("<hr><h3>Data for state: "+state+"</h3><hr>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>SNO</th>");
			out.println("<th>Date</th>");
			out.println("<th>Total</th>");
			out.println("<th>Active</th>");
			out.println("<th>Deaths</th>");
			out.println("</tr>");
			while (rs.next()) {
				String sno = rs.getString("sno");
				String date = rs.getString("idate");
				String total = rs.getString("total");
				String active = rs.getString("active");
				String deaths = rs.getString("deaths");
				out.println("<tr>");
				out.println("<td>"+sno+"</td>");
				out.println("<td>"+date+"</td>");
				out.println("<td>"+total+"</td>");
				out.println("<td>"+active+"</td>");
				out.println("<td>"+deaths+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");			
			out.println("<a href=stadmindashboard.jsp>Dashboard</a>");
			out.println("</body></html>");
		}
		catch(Exception e) {
			e.printStackTrace();
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
