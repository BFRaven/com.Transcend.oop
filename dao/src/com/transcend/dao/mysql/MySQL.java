package com.transcend.dao.mysql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQL {

//    protected means that it is accessible by this class and any other
//    class that inherits it. If set to private they wont be accessible outside of this class.
   protected static String dbHost = "localhost";
   protected static String dbName = "hr";
   protected static String dbUser = "root";
   protected static String dbPass = "root";
   protected static String useSSL = "false";
   protected static String procBod = "true";

   protected static Connection connection = null;

//    this is where we will put our logger; when we get it working correctly.
    final static Logger logger = Logger.getLogger(MySQL.class);

    protected static final int GET_BY_ID = 10;
    protected static final int GET_COLLECTION = 20;
    protected static final int INSERT = 10;
    protected static final int UPDATE = 20;
    protected static final int DELETE = 30;



    // this method will be to connect to the DB
    protected static void Connect () {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("My SQL Driver not found!" + ex);

        }

        logger.info("My SQL Driver Registered");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (
                SQLException ex) {
            logger.error("Connection failed!" + ex);

        }

        if(connection != null) {
            logger.info("Successfully connected to MySQL database");
//
        } else {
            logger.error("Connection failed!");

        }

    }


}
