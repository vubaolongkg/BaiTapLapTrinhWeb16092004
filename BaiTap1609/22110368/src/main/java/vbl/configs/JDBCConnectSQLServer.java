package vbl.configs;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectSQLServer {
    private final String serverName = "localhost";
    private final String dbName = "ltwebst2";
    private final String portNumber = "1433";
    private final String instance = ""; // MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "baolong"; // Điền username ở đây
    private final String password = "Baolong@12345"; // Điền password ở đây

    public Connection getConnection() throws Exception {
        // Cấu hình URL kết nối với thuộc tính encrypt và trustServerCertificate
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber;
        if (instance == null || instance.trim().isEmpty()) {
            url += ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
        } else {
            url += "\\" + instance + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    // Test chương trình. Kích phải chuột chọn run as -> Java Application
    public static void main(String[] args) {
        try {
            Connection conn = new JDBCConnectSQLServer().getConnection();
            if (conn != null) {
                System.out.println("Kết nối thành công: " + conn);
            } else {
                System.out.println("Kết nối thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
