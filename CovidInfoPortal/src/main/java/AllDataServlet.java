

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
public class AllDataServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//this will show the data of that state whose admin is currently logged in
		//fetching data from database
		String sql = "SELECT * from covidinfo ";
		try{
			Connection con = mypkg.Utility.connect();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			out.println("<html><body>");
			out.println("<hr><h3>Data for All States</h3><hr>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>SNO</th>");
			out.println("<th>State</th>");
			out.println("<th>Date</th>");
			out.println("<th>Total</th>");
			out.println("<th>Active</th>");
			out.println("<th>Deaths</th>");
			out.println("</tr>");
			int sumTotalCases=0;
			int sumActiveCases=0;
			int sumDeaths=0;
			while (rs.next()) {
				String sno = rs.getString("sno");
				String state = rs.getString("state");
				String date = rs.getString("idate");
				int total = rs.getInt("total");
				sumTotalCases = sumTotalCases+total;
				int active = rs.getInt("active");
				sumActiveCases += active;
				int deaths = rs.getInt("deaths");
				sumDeaths += deaths;
				out.println("<tr>");
				out.println("<td>"+sno+"</td>");
				out.println("<td>"+state+"</td>");
				out.println("<td align=right>"+date+"</td>");
				out.println("<td align=right>"+total+"</td>");
				out.println("<td align=right>"+active+"</td>");
				out.println("<td align=right>"+deaths+"</td>");
				out.println("</tr>");
			}
			out.println("<tr>");
			out.println("<td></td>");
			out.println("<td></td>");
			out.println("<td></td>");
			out.println("<td align=right>"+sumTotalCases+"</td>");
			out.println("<td align=right>"+sumActiveCases+"</td>");
			out.println("<td align=right>"+sumDeaths+"</td>");
			out.println("</tr>");
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
