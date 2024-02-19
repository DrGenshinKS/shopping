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
	<%String username=request.getParameter("userName");
		String password0=request.getParameter("pwd");
		String password1=request.getParameter("pwd1");
		String xb=request.getParameter("sex");
		String xq[]=request.getParameterValues("interest");
		if(username==""||password0==""||password1==""||username==null){
	%>
	<jsp:forward page="reg.jsp">
		<jsp:param value="请输入完整的信息" name="mess0"/>
	</jsp:forward>
	<%} 
		if(!password0.equals(password1)){
	%>
	<jsp:forward page="reg.jsp">
		<jsp:param value="两次密码不一致" name="mess1"/>
	</jsp:forward>
	<%}%>
	用户名：<%=username %><br>
	密码：<%=password0 %><br>
	性别：<%=xb %><br>
	兴趣：<%String strHobby="";
			for(int i=0;i<xq.length;i++){%>
			<%=xq[i] %>
			<%strHobby+=xq[i]+",";
				if(i<xq.length-1){%>
				<%=","%>	
			<%}
			}
			if(!strHobby.equals("")){
				strHobby=strHobby.substring(0,strHobby.length()-1);	
		}
			try{
	String url="jdbc:sqlserver://LAPTOP-J1CLM07B:1433;databaseName=shopping;encrypt=false;"; 
	String user="sa"; //��½���ݿ���������û���
	String password="zcmu2021";//��½���ݿ������������
	
	
	Connection conn=null;
	PreparedStatement stm=null;
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载驱动程序
	conn=DriverManager.getConnection(url, user, password);//获得连接
	String sql="insert into userinfo1(userName,password,sex,interest)values(?,?,?,?)";
	//也可以insert into user1(userName,password,sex,interest)values('"+username+"','"+password0+"',?,?)
	stm=conn.prepareStatement(sql);//创建一个statement对象，sql容器
	stm.setString(1,username);
	stm.setString(2,password0);
	stm.setString(3,xb);
	stm.setString(4,strHobby);
	stm.executeUpdate();
	stm.close();
	conn.close();
			}
			catch(Exception e){
				System.out.println(e);
				%>
				<jsp:forward page="reg.jsp">
					<jsp:param value="用户已存在" name="mess0"/>
				</jsp:forward>
				<%
			}
		%>
	
	<a href="index.jsp">去登录</a>
</body>
</html>