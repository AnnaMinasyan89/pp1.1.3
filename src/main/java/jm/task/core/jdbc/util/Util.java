package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    // реализуйте настройку соеденения с БД
    private static final String PASSWORD = "pass";
    private static final String USERNAME = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

    static {
        loadDriver();
    }

    private Util() {
    }

    public static Connection open(){
        try {
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


   public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                configuration.setProperty("hibernate.connection.driver_class", DRIVER);
                configuration.setProperty("hibernate.connection.url", URL);
                configuration.setProperty("hibernate.connection.username", USERNAME);
                configuration.setProperty("hibernate.connection.password", PASSWORD);
                configuration.setProperty("hibernate.dialect", DIALECT);
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.setProperty("hibernate.hbm2ddl", "drop");
                configuration.setProperty("hibernate.current_session_context_class", "thread");

                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

}

