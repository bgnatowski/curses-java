package pl.ormlite.example.Utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DbManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);
	private static final String JDBC_DRIVER_SQLLITE = "jdbc:h2:./bazadanych";
	private static ConnectionSource connectionSource;
	private static String statementSQL;

	public static void initDatabase(){
		createStatementDropTable();
		executeStatement();

		createStatementCreate();
		executeStatement();

		createConnectionSource();
//		dropTable();
//		createTable();
		closeConnectionSource();
	}

	private static void createStatementDropTable() {
		WinPQData model = ConverterWinPQData.convertToDbObject(new WinPQModelObj());
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("DROP TABLE IF EXISTS " + model.getClass().getSimpleName() + ";");
		statementSQL = stringBuilder.toString();
	}

	private static void createStatementCreate() {
		WinPQData model = ConverterWinPQData.convertToDbObject(new WinPQModelObj());
//		CREATE TABLE MODEL(ID LONG PRIMARY KEY AUTO_INCREMENT,  TIME TIME, DATE DATE, FLAG VARCHAR(1), U12_AVG DOUBLE PRECISION, U12_MAX DOUBLE PRECISION, U12_MIN DOUBLE PRECISION);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("DROP TABLE IF EXISTS " + model.getClass().getSimpleName() + ";\n");
		stringBuilder.append("CREATE TABLE ");
		stringBuilder.append(model.getClass().getSimpleName());
		stringBuilder.append("(");
		stringBuilder.append("ID LONG PRIMARY KEY AUTO_INCREMENT, ");
		stringBuilder.append("DATE DATE, ");
		stringBuilder.append("TIME TIME, ");
		if (model instanceof WinPQData){
			WinPQData winPQModelObj = (WinPQData) model;
			Map<UnitaryNames, Double> records = winPQModelObj.getRecords();
			stringBuilder.append("FLAG VARCHAR(1),");
			records.forEach((name, value) -> {
				stringBuilder.append(name.toString().toUpperCase() + " DOUBLE PRECISION, ");
			});
			stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
			stringBuilder.append(");");
		} else {
			stringBuilder.append("FLAG VARCHAR(5),");
		}
		statementSQL = stringBuilder.toString();
		System.out.println(statementSQL);
	}

	private static void executeStatement() {
		try (Connection connection = H2JDBCUtils.getConnection();
			 // Step 2:Create a statement using connection object
			 Statement statement = connection.createStatement();) {
			// Step 3: Execute the query or update query
			statement.execute(statementSQL);
		} catch (SQLException e) {
			// print SQL exception information
			H2JDBCUtils.printSQLException(e);
		}
	}

	public static void executeStatement(String statementSQL){
		try (Connection connection = H2JDBCUtils.getConnection();
			 // Step 2:Create a statement using connection object
			 Statement statement = connection.createStatement();) {
			// Step 3: Execute the query or update query
			statement.execute(statementSQL);
		} catch (SQLException e) {
			// print SQL exception information
			H2JDBCUtils.printSQLException(e);
		}
	}

	private static void createConnectionSource(){
		try {
			connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLLITE, "admin", "");
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}

	public static ConnectionSource getConnectionSource(){
		if(connectionSource == null) {
			createConnectionSource();
		}
		return connectionSource;
	}

	public static void closeConnectionSource(){
		if (connectionSource!=null){
			try {
				connectionSource.close();
			} catch (Exception e) {
				LOGGER.warn(e.getMessage());
			}
		}
	}

	public static void createTable(){
		try {
//			TableUtils.createTableIfNotExists(connectionSource, Author.class);
//			TableUtils.createTableIfNotExists(connectionSource, Book.class);
			TableUtils.createTableIfNotExists(connectionSource, WinPQData.class);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}
	public static void dropTable(){
		try {
//			TableUtils.dropTable(connectionSource, Author.class, true);
//			TableUtils.dropTable(connectionSource, Book.class, true);
			TableUtils.dropTable(connectionSource, WinPQData.class, true);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}
}
