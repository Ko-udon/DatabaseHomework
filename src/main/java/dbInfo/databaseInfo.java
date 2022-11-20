package dbInfo;

public class databaseInfo {
    private String dbName="jdbc:mysql://localhost/movieInfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String userName="root";
    private String password="ehddn";


    public String getDbName() {
        return dbName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }




}
