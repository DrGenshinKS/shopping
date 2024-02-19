package com.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DataProcess;

/**
 * Servlet implementation class Cart_remove
 */
@WebServlet("/cart_remove")
public class Cart_remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart_remove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		DataProcess data=new DataProcess();
		String sql="select productid,count from cart where userName='"+username+"'";
		Vector<Vector<String>> d1=data.getData(sql);
		for(int i=0;i<d1.size();i++) {
			sql="select sale from product where id="+d1.get(i).get(0);
			Vector<Vector<String>> d2=data.getData(sql);
			int sale=Integer.parseInt(d2.get(0).get(0))+Integer.parseInt(d1.get(i).get(1));
			sql="update product set sale= "+sale+" where id="+d1.get(i).get(0);
			data.update(sql);
		}
		sql="delete from cart where userName='"+username+"'";
		data.update(sql);
		response.sendRedirect("cart_view");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
