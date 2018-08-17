package com.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
	public static final String url = "jdbc:mysql://127.0.0.1:3306/dbb?useUnicode=true&characterEncoding=utf-8";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";  
  
    public Connection conn = null;
    //public CallableStatement stat = null;
    public PreparedStatement pst = null;  
  
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);  
            conn = DriverManager.getConnection(url, user, password); 
            pst = conn.prepareStatement(sql);//准备执行语句
            //stat = conn.prepareCall(sql);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();
            //this.stat.close();
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
}
