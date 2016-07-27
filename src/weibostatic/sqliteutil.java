package weibostatic;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by suntr on 7/23/2016.
 */
public class sqliteutil {
	Connection conn;
	Statement stat ;
	String table = "status";
	
	public sqliteutil() {
		// TODO Auto-generated constructor stub
        try {
			Class.forName("org.sqlite.JDBC");
	        Connection conn = DriverManager.getConnection("jdbc:sqlite:concern.db");
	        Statement stat = conn.createStatement();
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int  insertOld(String weiboid, String weibomid) throws SQLException{
		
		String sql = "INSERT INTO " + table + "VALUES (null, " + weiboid + "," + weibomid + ");";
		return stat.executeUpdate("sql");
	}
	
	public ResultSet query(String[] m) throws SQLException{
		String sql = "SELECT * FROM " + table + "WHERE " + m[0] + "=" + m[1] + ";";
		return stat.executeQuery(sql);
	}
	
	public ResultSet queryOldByWeiboid(String weiboid) throws SQLException{
		return query(new String[]{"statuid",weiboid});
	}
	public ResultSet queryOldByWeibomid(String weibomid) throws SQLException{
		return query(new String[]{"statumid",weibomid});
	}
	
	
	
    public static void main(String[] args){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:concern.db");
            Statement stat = conn.createStatement();
            stat.executeUpdate("create table if not exists status(id integer primary key autoincrement, statuid integer, statumid integr );");
            stat.executeUpdate("insert into status values(null,'10','13');");
            stat.executeUpdate("insert into status values(null,'66','77');");
            ResultSet rs = stat.executeQuery("SELECT * FROM status;");

            while (rs.next()){
                System.out.println(rs.getInt("statuid"));
                System.out.println(rs.getInt("statumid"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
