<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%request.setCharacterEncoding("utf-8"); %>
	<%if(request.getParameter("useName").equals("")||
		request.getParameter("pwd").equals("")){%>
		<jsp:forward page="login.jsp">
			<jsp:param value="用户名或密码不能为空" name="mess0"/>
		</jsp:forward>
	<%}
	try{
		String url="jdbc:sqlserver://LAPTOP-J1CLM07B:1433;databaseName=shopping;encrypt=false;"; 
		String user="sa"; //��½���ݿ���������û���
		String password="zcmu2021";//��½���ݿ������������
		Connection conn=null;
		PreparedStatement stm=null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn=DriverManager.getConnection(url, user, password);
		String sql="select password from userinfo1 where userName=?";
		stm=conn.prepareStatement(sql);
		stm.setString(1,request.getParameter("useName"));
		ResultSet rst=stm.executeQuery();
		if(rst.next()){
			String pwd=rst.getString("password");
			if(pwd.equals(request.getParameter("pwd"))){
				session.setAttribute("username", request.getParameter("useName"));
				session.setAttribute("password", request.getParameter("pwd"));
			}else{%>
			<jsp:forward page="index.jsp">
				<jsp:param value="密码不正确" name="mess0"/>
			</jsp:forward>
			<%}
		}
	}catch(Exception e){
		System.out.println(e);
	}
	response.sendRedirect("index.jsp");
	%>
</body>
</html>