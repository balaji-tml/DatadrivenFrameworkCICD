package com.bit.utilities;

public class TestConfig {


    public static String server = "smtp.gmail.com";
    public static String from = "bettertester21@gmail.com";
    public static String password = "";
    public static String[] to = {"pulibalaji@gmail.com"};
    public static String subject = "Extent Project Report";

    public static String messageBody = "TestMessage";
    public static String attachmentPath = "/home/balaji/Documents/Frameworks/DatadrivenFrameworkCICD/Reports/Pic1.jpg";
    public static String attachmentName = "error.jpg";


    //SQL DATABASE DETAILS
    public static String driver = "net.sourceforge.jtds.jdbc.Driver";
    public static String dbConnectionUrl = "jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval";
    public static String dbUserName = "sa";
    public static String dbPassword = "$ql$!!1";


    //MYSQL DATABASE DETAILS
    public static String mysqldriver = "com.mysql.jdbc.Driver";
    public static String mysqluserName = "root";
    public static String mysqlpassword = "selenium";
    public static String mysqlurl = "jdbc:mysql://localhost:3306/acs";


}