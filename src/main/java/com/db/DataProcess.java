package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

public class DataProcess {
	private String url="jdbc:sqlserver://LAPTOP-J1CLM07B:1433;databaseName=shopping;encrypt=false;"; 
	private String user="sa"; //锟斤拷陆锟斤拷锟捷匡拷锟斤拷锟斤拷锟斤拷锟斤拷没锟斤拷锟�
	private String password="zcmu2021";//锟斤拷陆锟斤拷锟捷匡拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	private Connection conn=null;
	private Statement stm=null;
	public Vector<Vector<String>> getData(String sql){//select
		Vector<Vector<String>> rows=new Vector<Vector<String>>();
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			conn=DriverManager.getConnection(url, user, password);
			stm=conn.createStatement();
			ResultSet rst=stm.executeQuery(sql); 
			ResultSetMetaData rstd=rst.getMetaData();
			Vector<String> rowData=null;
			while(rst.next()){
				rowData=new Vector<String>();
				for (int i = 1; i <rstd.getColumnCount()+1 ; i++) {
					rowData.add(rst.getString(i));
				}
//				rowData.add(rst.getString(1));
//				rowData.add(rst.getString(2));
				rows.add(rowData);
			}
			stm.close();
			conn.close();
		}catch(Exception e){
			System.err.print(e);
		}
		return rows;
	}
	public int update(String sql){//insert,update,delete
		int num=0;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection(url, user, password);
			stm=conn.createStatement();
			num=stm.executeUpdate(sql);
			stm.close();
			conn.close();
		}catch(Exception e){
			System.err.print(e);
		}
		return num;
	}
}
