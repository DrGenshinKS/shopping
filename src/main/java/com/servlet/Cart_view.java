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
 * Servlet implementation class Cart_view
 */
@WebServlet("/cart_view")
public class Cart_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart_view() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//锟斤拷锟斤拷锟斤拷锟斤拷
//		String quantity=request.getParameter("quantity");
		//System.out.println(quantity);
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		//System.out.println(username);
		//锟斤拷锟斤拷锟斤拷锟斤拷
//		DataProcess data=new DataProcess();
//		int id=Integer.parseInt(request.getParameter("id"));
		//System.out.println(id);
		
//		String sql="select sale from product where id="+id;//鏌ヨ搴撳瓨
//		Vector<Vector<String>> tmp1=data.getData(sql);
//		int tmp2=Integer.parseInt(tmp1.get(0).get(0));
//		if(Integer.parseInt(quantity)>tmp2) {//搴撳瓨涓嶈冻
//			request.setAttribute("mess", "搴撳瓨涓嶈冻");
//			request.getRequestDispatcher("Item?id="+id).forward(request, response);
//		}else {//搴撳瓨瓒�
//			sql="update product set sale="+(tmp2-Integer.parseInt(quantity))+" where id="+id;
//			data.update(sql);
//		}
		
		
		
//		String sql="update cart set count='"+quantity+"' where userName='"+username+"';";
//		
//		
//		try {
//			int tmp=data.update(sql);
//			if(tmp==0) {
//				sql="insert into cart(productid,count,userName) values("+id+",'"+quantity+"','"+username+"')";
//				data.update(sql);
//			}
//			//澧炲姞鍞嚭
//			sql="select sale from product where id="+id;
//			Vector<Vector<String>> tmp1=data.getData(sql);
//			int tmp2=Integer.parseInt(tmp1.get(0).get(0));
//			sql="update product set sale="+(tmp2+Integer.parseInt(quantity))+" where id="+id;
//			data.update(sql);
//		}catch(Exception e) {
//			System.err.print(e);
//			return;
//		}
		String sql="SELECT a.id,b.name,b.price,b.oneprice,a.count,a.productid "
				+ "FROM cart as a "
				+ "INNER JOIN product as b "
				+ "ON a.productid = b.id and a.userName='"+username+"';";
		try {
			DataProcess data=new DataProcess();
			Vector<Vector<String>> cart_products=data.getData(sql);
			request.setAttribute("cart_products", cart_products);
			request.getRequestDispatcher("cart_view.jsp").forward(request, response);
			return;
		}catch(Exception e) {
			System.err.print(e);
			return;
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
