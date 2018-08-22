package utils;

import java.sql.*;

/**
 * Created by chengzw on 2018/8/11.
 */
public class MysqlConnecUtil {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.200.77:3306/cms";
    static final String USERNAME = "cms";
    static final String PWD = "HvvjTaFynnD2Ae4WufHH";
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USERNAME,PWD);
            statement = connection.createStatement();
            String sql = "select * from openclass_coupon_user_record where username = 'dxy_test4';";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String username = resultSet.getString("username");
                System.out.println(username);
                System.out.println("\n");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
