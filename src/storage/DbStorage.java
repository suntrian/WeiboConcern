package storage;

import java.sql.*;

/**
 * Created by suntr on 7/23/2016.
 */
public class DbStorage {
	Connection conn;
	Statement stat;
	String table = "status";
	String dbName = "default.db";
	public DbStorage(String db) {
		// TODO Auto-generated constructor stub
		this.dbName = db;
		this.conn = conncetDB(dbName);			
		stat = getStatement(this.conn);
			
	}
	public Connection conncetDB(String dbPath){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ dbPath);
			return conn;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public Statement getStatement(Connection connection){
		try {
			if(connection.isClosed()){
				this.conn = conncetDB(dbName);
			}
			Statement stat = connection.createStatement();
			return stat;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int insert(String table , int args, String[] argv) throws SQLException{
		if(this.stat.isClosed()){
			this.stat = getStatement(this.conn);
		}
		String sql = "INSERT INTO " + table + " VALUES( null, ";
		int i = 0;
		do {
			argv[i].replaceAll("'", "''");
			if(argv[i] != "null"){
				argv[i] = "'" + argv[i] + "'";
			}
			if(i==0){
				sql += argv[i];
			}else {
				sql += (" , " + argv[i]);
			}	
		}while( ++i < args);
		sql += " );";
		return stat.executeUpdate(sql);
	}

	public int insertStatus(String weiboid, String userid , String creatime ) throws SQLException {
		return insert(table, 3, new String[]{weiboid,userid ,creatime,"null","null"});
	}
	public int insertStatus(String weiboid,String userid , String creatime ,String text) throws SQLException{
		return insert(table, 4, new String[]{weiboid,userid ,creatime,text,"null"});
	}
	public int insertStatus(String weiboid,String userid , String creatime ,String text,String pics) throws SQLException{
		if(creatime.trim().isEmpty() | creatime == null) creatime = "null";
		if(text.trim().isEmpty() | text == null) text = "null";
		if(pics.trim().isEmpty() | pics == null) pics = "null";
		return insert(table, 5, new String[]{weiboid,userid ,creatime,text,pics});
	}
	
	public int queryCount(String table, String[] m) throws SQLException{
		String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + m[0] + "=" + m[1] + ";";
		ResultSet res = stat.executeQuery(sql);
		while (res.next()) {
			return res.getInt(1);
		}
		return 0;
	}
	public long queryLatestStatusByDate() throws SQLException{
		if(this.stat.isClosed()){
			this.stat = getStatement(this.conn);
		}
		String sql = "SELECT creatime FROM " + table + " ORDER BY creatime DESC;";
		ResultSet rs = stat.executeQuery(sql);
		if(rs.next()){
			return rs.getLong("creatime");
		}
		return 0;
	}

	public int queryStatusCountById(String id) throws SQLException{
		return queryCount(table, new String[]{"statusid",id});
	}
	public ResultSet query(String table, String[] m) throws SQLException {
		if(this.stat.isClosed()){
			this.stat = getStatement(this.conn);
		}
		String sql = "SELECT * FROM " + table + " WHERE " + m[0] + "=" + m[1] + ";";
		return stat.executeQuery(sql);
	}

	public ResultSet queryStatusByWeiboid(String weiboid) throws SQLException {
		return query(this.table, new String[] { "statusid", weiboid });
	}

	public ResultSet queryStatusByWeiboUserId(String userid) throws SQLException {
		return query(this.table, new String[] { "userid", userid });
	}

	public void initial_db(){

		try {
			if(this.stat.isClosed()){
				this.stat = getStatement(this.conn);
			}
			
			String sqlCreateTableStatus = "CREATE TABLE IF NOT EXISTS " + "status" + "("
								+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
								+ "statusid VARCHAR(20) NOT NULL UNIQUE,"
								+ "userid VARCHAR(20) NOT NULL,"
								+ "creatime VARCHAR(20),"
								+ "status TEXT,"
								+ "pictures TEXT"
								+ ");";
			
			
			stat.executeUpdate(sqlCreateTableStatus);
			//stat.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ Common.getUserDirectory() + Common.getSystemSeparator() +  "concern.db");
			Common.getUserDirectory();
			Statement stat = conn.createStatement();
			stat.executeUpdate(
					"create table if not exists status(id integer primary key autoincrement, statusid integer not null UNIQUE , creatime integer,"
					+ "status text, pic varchar(100) );");
			//stat.executeUpdate("insert into status values(null,'18','13',null,null);");
			//stat.executeUpdate("insert into status values(null,'69','77',null,null);");
			ResultSet rs = stat.executeQuery("SELECT * FROM status WHERE statuid = 9;");
			System.out.println();
			System.out.println(rs.getRow());
			
			if(rs== null){
				System.out.println("null null");
			}
			while (rs.next()) {
				System.out.println(rs.getRow());
				System.out.println(rs.getInt("statusid"));
				System.out.println(rs.getInt("creatime"));
			}

			ResultSet res = stat.executeQuery("SELECT COUNT(*) FROM status WHERE statuid = 10;");
			while(res.next()){
				
				System.out.println("COUNT:" + res.getInt(1));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
