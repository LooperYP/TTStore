package com.ithiema.store.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static DataSource getDataSourse() {
		return cpds;
	}
	
	public static Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}

	public static Connection getConnectionTL() throws SQLException {
		Connection connection = tl.get();
		
		if(connection==null){
			connection = getDataSourse().getConnection();
			tl.set(connection);
		}
		return connection;
	}

	public static void startTranscation() throws SQLException {
		Connection connection = getConnectionTL();
		connection.setAutoCommit(false);
	}

	public static void commitAndRelease() throws SQLException {
		Connection connection = getConnectionTL();
		if(connection!=null){
			connection.commit();
			connection.close();
			connection = null;
		}
		tl.remove();
	}

	public static void rollAndRelease() throws SQLException {
		Connection connection = getConnectionTL();
		if(connection!=null){
			connection.rollback();
			connection.close();
			connection = null;
		}
		tl.remove();
	}
	
}
