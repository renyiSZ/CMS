package com.kc.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;
import com.kc.entity.Users;
import com.kc.service.UsersService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoginServlet(){
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("userid");//获取用户名
		String passwd = request.getParameter("password"); //获取用户密码
		String action = request.getParameter("action");
		//String path =request.getServletPath();
		//String refer = request.getHeader("REFERER"); 
		//System.out.println(path+"//"+refer);
		
		PrintWriter out=response.getWriter();
		//List<String> info=new ArrayList<String>();
		//String info="";
		
		/*if(id==null||"".equals(id))
		{
			info.add("id cannot be null");
			//info="ID cannot be null! ";
			System.out.println("ID cannot be null! ");
			path = "homepage.jsp";
		}
		if(passwd==null||"".equals(passwd))
		{
			info.add("Password cannot be null! ");
			//info=info+"Password cannot be null! ";
			System.out.println("password cannot be null");
			path = "homepage.jsp";
		}*/
		//if(info.size()==0){
			try{
				if (action.equals("login")) {
					Users user= DAOFactory.getUserDAOInstance().queryByID(id);//根据用户名查询用户
					if (passwd.equals(user.getPasswd())) {//输入的密码与数据库中的一致
						request.getSession().setAttribute("uid", id);
						request.getSession().setAttribute("uname", user.getUname());
						request.getSession().setAttribute("job", user.getJob());
						request.getSession().setAttribute("usex", user.getUsex());
						request.getSession().setAttribute("uhead", user.getUhead());
						request.getSession().setAttribute("representative",user.getRepresentative());
						request.getSession().setAttribute("assistant", user.getAssistant());
						
						System.out.println("log in representative:"+ user.getRepresentative());
						
						request.getRequestDispatcher("homepage.jsp").forward(request, response);
						
					} else {
						out.println("<script type='text/javascript' language='javascript'>");
						out.println("alert('Failed to log in, wrong ID or password!');");
						out.println("window.history.back(-1);");
						out.println("</script>");
						out.flush();
						out.close();
						//request.setAttribute("status", "用户名或密码错误！");
						//info.add("Failed to log in, wrong ID or password!");
						//info="Failed to log in, wrong ID or password!";
						//path = "homepage.jsp";
					}
				}
				else if (action.equals("logout")) {//用户退出，注销session中的用户
					request.getSession().removeAttribute("uid");
					request.getSession().removeAttribute("uname");
					request.getSession().removeAttribute("job");
					//path = "homepage.jsp";
					request.getRequestDispatcher("homepage.jsp").forward(request, response);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			
		//}
		//request.setAttribute("info",info);
		//request.getRequestDispatcher(path).forward(request, response);
	
	}
}
