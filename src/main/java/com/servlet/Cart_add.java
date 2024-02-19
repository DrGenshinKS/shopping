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
 * Servlet implementation class Cart_add
 */
@WebServlet("/cart_add")
public class Cart_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart_add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//接收数据
		String quantity=request.getParameter("quantity");
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		int id=Integer.parseInt(request.getParameter("id"));
		//验证/处理数据
		if(username==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		DataProcess data=new DataProcess();
		String sql="select count from cart where userName='"+username+"' and productid="+id;
		Vector<Vector<String>> d1=data.getData(sql);
		if(d1.size()==0){
			sql="insert into cart(productid,count,userName) values("+id+",'"+quantity+"','"+username+"')";
			data.update(sql);
		}else {
			int tmpnum=Integer.parseInt(d1.get(0).get(0))+Integer.parseInt(quantity);
			sql="update cart set count="+tmpnum+" where productid="+id;
			data.update(sql);
		}
		//增加售出
		sql="select sale from product where id="+id;
		Vector<Vector<String>> tmp1=data.getData(sql);
		int tmp2=Integer.parseInt(tmp1.get(0).get(0));
		sql="update product set sale="+(tmp2+Integer.parseInt(quantity))+" where id="+id;
		data.update(sql);
		
		response.sendRedirect("item?id="+id);
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
