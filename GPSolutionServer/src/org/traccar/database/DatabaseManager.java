package org.traccar.database;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.traccar.helper.Constants;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseManager {
	
	private static DataSource dataSource = null;
	
	private DatabaseManager() {
	}
	

	public static Connection getDBConnection(){
		Connection dbConnection = null;
		try{
			if(null == dataSource){
				dataSource = createDatasource();
			}
			dbConnection = dataSource.getConnection();
		}catch(Exception e){
			
		}
		return dbConnection;
	}
	
	private static DataSource createDatasource(){
		
		MysqlDataSource mysqldataSource= null;
		InitialContext ctx = null;
		
		try{
			
			ctx = new InitialContext();
			mysqldataSource = new MysqlDataSource();
			mysqldataSource.setUrl(Constants.MYSQL_DB_URL);
			mysqldataSource.setUser(Constants.MYSQL_DB_USERNAME);
			mysqldataSource.setPassword(Constants.MYSQL_DB_PASSWORD);
			mysqldataSource.setConnectTimeout(Constants.MYSQL_CONNECTION_TIMEOUT);
		}catch(Exception e){
			
		}
		return mysqldataSource;
	}
}
