<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312" import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>��ʿ�ﶬ�����Ƽ�</title>
<link rel="stylesheet" rev="stylesheet" href="css/global.css" type="text/css" media="all" />
</head>
<body>
		<div id="page">
			<div id="header">
				<jsp:include page="header.jsp" />      
       		</div>
			
			<div id="left_column">
				<jsp:include page="left_column.jsp" />  
			</div>
			<div id="center_column">
				<%-- <%if(session.getAttribute("cart")==null){ %>
					<div id="empty">
						<h3>���Ĺ��ﳵ���ǿյģ�</h3>
						<h3>�Ͻ��ж��ɣ�</h3>
					</div>
				<%}else{ %> --%>
				<img src="images/shopping_card.gif" />
				<div id="cart_tb">
						<table>
							<tr>
								<td width="10%"><span style="color:#696969;size:13;font-weight:bold;">���</span></td>
								<td width="30%"><span style="color:#696969;size:13;font-weight:bold;">��Ʒ����</span></td>
								<td width="20%"><span style="color:#696969;size:13;font-weight:bold;">���ۣ�Ԫ��</span></td>
								<td width="20%"><span style="color:#696969;size:13;font-weight:bold;">����</span></td>
								<td width="10%"><span style="color:#696969;size:13;font-weight:bold;">С�ƣ�Ԫ��</span></td>
								<td width="10%"><span style="color:#696969;size:13;font-weight:bold;">����</span></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>							
							<%
								request.setCharacterEncoding("utf-8");
								if(session.getAttribute("username")==null){
							%>
							<jsp:forward page="login.jsp">
								<jsp:param value="���ڵ�¼�󣬲鿴���ﳵ" name="mess"/>
							</jsp:forward>
							<%}
								double sum=0;
								Vector<Vector<String>> products=(Vector<Vector<String>>)request.getAttribute("cart_products");
								for(int i=0;i<products.size();i++){
									Vector<String> product=products.get(i);
									sum+=Double.parseDouble(product.get(2))*Double.parseDouble(product.get(4));
							%>
							<tr>
								<td><%=i+1%></td>
								<td><%=product.get(1) %></td>
								<td><%=product.get(2) %></td>
								<td><%=product.get(4) %></td>
								<td><%=Double.parseDouble(product.get(2))*Double.parseDouble(product.get(4)) %></td>
								<td><a href="cart_removesingle?productid=<%=product.get(5) %>"><button >ɾ��</button></a> </td>
							</tr>
							<%
								}
							%>
									
						</table>
					</div>
				<div id="cart_tt">	
					�ϼ��ܽ�<span id="totalPrice" style="font-size:18px;color:#f60;"><%=sum %></span>Ԫ</td>
				</div>
				<div id="cart_lk">
					<a href="index.jsp">��������</a> | <a href="#">ȥ����̨����</a> | <a href="cart_remove">��չ��ﳵ</a> 
				</div>
			
			</div>
			<div id="footer">
				<jsp:include page="bottom.jsp" />  
			</div>
		</div>
	
</body>
</html>