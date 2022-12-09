package com.emented.weblab3.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.SQLException;

@Data
@ManagedBean
@ApplicationScoped
public class HeliosDatabaseConnectionProvider implements ConnectionProvider {

    private static final String USERNAME = "s336189";
    private static final String PASSWORD = "MRCr5hGKzN3TVdUx";
    private static final String URL = "jdbc:postgresql://pg:5432/studs";


    private static final HikariConfig HIKARI_CONFIG = new HikariConfig();
    private static final HikariDataSource HIKARI_DATA_SOURCE;

    static {
        HIKARI_CONFIG.setUsername(USERNAME);
        HIKARI_CONFIG.setPassword(PASSWORD);
        HIKARI_CONFIG.setJdbcUrl(URL);
        HIKARI_CONFIG.addDataSourceProperty("cachePrepStmts", "true");
        HIKARI_CONFIG.addDataSourceProperty("prepStmtCacheSize", "250");
        HIKARI_CONFIG.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HIKARI_DATA_SOURCE = new HikariDataSource(HIKARI_CONFIG);
    }

    @PreDestroy
    public void closeDataSource() {
        HIKARI_DATA_SOURCE.close();
    }

    public Connection getConnection() throws SQLException {
        return HIKARI_DATA_SOURCE.getConnection();
    }

}
