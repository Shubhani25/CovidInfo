

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
		PrintWriter out=response.getWriter();
        //VerifyUser?uid=aaa%40gmail.com&password=ssi&utype=enduser
        String id=request.getParameter("uid");
        String pw=request.getParameter("password");
        String utype=request.getParameter("utype");
        
        if(utype.equals("Super-Admin")){
            if(id.equals("sadmin") && pw.equals("sadmin")){
                //we want this request to be forwarded to saddashboard.jsp
               //response.sendRedirect("sadmindashboard.jsp");
                RequestDispatcher rd=request.getRequestDispatcher("sadmindashboard.jsp");
                rd.forward(request, response);
            }else{
                out.println("<html>");
                out.println("<body>");
                out.println("<h3>Invalid Super Admin Account</h3>");
                out.println("<h4><a href=index.jsp>Try-Again</a></h4>");
                out.println("</body>");
                out.println("</html>");
            }
        }else if(utype.equals("State-Admin")){
            //we will check the credentials from db and match with the inputs given by user
            try{
            ps2.setString(1, id);
            ps2.setString(2, pw);
            ResultSet rs=ps2.executeQuery();
            
            boolean found=rs.next();
            if(found){//credentials are correct (id/pw)
                String status=rs.getString("status");
                String uid=rs.getString("userid");
            //check the status
            if(status.equals("disabled")){
            //if-disabled-then-we-will-show-profile-completion-form
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Profile-Completion-Form</h3>");
            out.println("<form action=UpdateStateAdminProfile>");
            //userid,password,uname,email,address,mobile
            out.println("<pre>");
            out.println("Userid     : <input type=text name=uid value="+uid+">");
            out.println("Password   : <input type=password name=password>");
            out.println("Username   : <input type=text name=uname>");
            out.println("Address    : <input type=text name=address>");
            out.println("Email      : <input type=text name=email>");
            out.println("Mobile     : <input type=text name=mobile>");
            out.println("<input type=submit value=Update>");
            out.println("</pre>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            }else{
            //if-enabled-then-we-will-display-dashboard
                //storing the id to session, state into session
                response.sendRedirect("stadmindashboard.jsp");
            }
            }else{
                //credentials are wrong
                out.println("<html><body>");
                out.println("<h3>Invalid State-Admin Account</h3>");
                out.println("<h4><a href=index.jsp>Try-Again</a></h4>");
                out.println("</body></html>");
            }
            }catch(Exception e){
                out.println(e);
            }
        }else if(utype.equals("End-User")){
           //we need to authenticate using DB 
           try{
               ps1.setString(1, id);
               ps1.setString(2, pw);
               ResultSet rs=ps1.executeQuery();
               boolean found=rs.next(); 
               if(found){
                   response.sendRedirect("userdashboard.jsp");
               }else{
                out.println("<html><body>");
                out.println("<h3>Invalid User Account</h3>");
                out.println("<h4><a href=index.jsp>Try-Again</a></h4>");
                out.println("</body></html>");
               }
           }catch(Exception e){
               out.println(e);
           }
        }
        



}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
