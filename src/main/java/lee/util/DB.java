package lee.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DB {
    private static volatile DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            synchronized(DB.class) {
                if (dataSource == null) {
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("92727");
        mysqlDataSource.setDatabaseName("listen_books");
        mysqlDataSource.setCharacterEncoding("utf8");
        mysqlDataSource.setUseSSL(false);
        return mysqlDataSource;
    }

}
