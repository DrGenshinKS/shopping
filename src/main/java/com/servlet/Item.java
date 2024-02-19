package com.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Product;
import com.db.DataProcess;

/**
 * Servlet implementation class Item
 */
@WebServlet("/item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Item() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		//System.out.println(id);
		DataProcess data=new DataProcess(); 
		String sql="select * from product where id="+id;
		Vector<Vector<String>> d1 = data.getData(sql);
		Product p1 =new Product();
		p1.setId(d1.get(0).get(0));
		p1.setName(d1.get(0).get(1));
		p1.setSort(d1.get(0).get(2));
		p1.setPrice(d1.get(0).get(3));
		p1.setOneprice(d1.get(0).get(4));
		p1.setImg(d1.get(0).get(5));
		p1.setSale(d1.get(0).get(6));
		p1.setFace(d1.get(0).get(7));
		p1.setBody(d1.get(0).get(8));
		p1.setLength(d1.get(0).get(9));
		p1.setQuantity(d1.get(0).get(10));
		p1.setSource(d1.get(0).get(11));
		request.setAttribute("product", p1);
		request.getRequestDispatcher("item.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
