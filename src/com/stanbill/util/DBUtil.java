package com.stanbill.util;
/*
获取数据库连接的工具类

在这个项目里有多个DAO里都需要获取数据库的连接，并且在本项目中都是一样的数据库连接。 所以就可以把获取数据库连接的代码重构到一个类里。
这样做的好处是有两个
1. 不需要DAO里分别进行编写，直接调用就可以了
2. 如果账号密码发生了变化，值需要修改这一个地方，而不用每个DAO里就分别修改，降低了维护成本，也降低了因为忘记修改而出错的概率
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    //首先定义了很多和数据库连接相关的信息，如果账号密码发生改变，修改起来很容易
    static String ip = "127.0.0.1";
    static int port = 3307;
    static String database = "stanbill";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "admin";

    //驱动初始化放在了静态初始化块里，因为这行代码需要先执行，而且只需要执行一次就足够了
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //提供了一个静态的public的getConnection方法获取连接
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }
}
