<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.db.DataProcess,java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品分类</title>
<link rel="stylesheet" rev="stylesheet" href="css/global.css" type="text/css" media="all" />
</head>
<body>
	<%
		int id=Integer.parseInt(request.getParameter("id")); 
		DataProcess data=new DataProcess(); 
		String sql="select * from product where sort="+id;
		Vector<Vector<String>> products = data.getData(sql);
		for(int i=0;i<products.size();i++){
			Vector<String> product=products.get(i);
	%>
		<div id='sort_product'>
			<ul>
				<li><p class='gpic'><a href='item?id=<%=product.get(0) %>'><img width='205' height='154' src="<%=product.get(5) %>"></a></p></li> 
				<li><p class='gbt'><a href='product.jsp?productid=1'>品名：<%=product.get(1) %> </a></p></li>
				<li><p class='gprice'>促销价：<span style="color:#FF6600;font-weight:bold;"></span>￥<%=product.get(3) %>元</p></li>
				<li><p class='gsale'>已售出：<span style='font-weight:bold;'><%=product.get(6) %></span>&nbsp;笔</p></li>
			</ul>
		</div>	
	<%
		}
	%>
</body>
</html>