package com.seclass.sepcamp.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class CustomTableConfiguration {
    @Value("classpath:database/createUserTable.sql")
    private Resource createUserTableSql;
    @Value("classpath:database/createTeamTable.sql")
    private Resource createTeamTableSql;
    @Value("classpath:database/createProjectTable.sql")
    private Resource createProjectTableSql;
    @Value("classpath:database/createFunctionTable.sql")
    private Resource createFunctionTableSql;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) throws SQLException {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

        ResultSet userTable = metaData.getTables(null, null, "SEPCAMP_USER", null);
        if(!userTable.next()) {
            populator.addScript(createUserTableSql);
        }
        ResultSet teamTable = metaData.getTables(null, null, "SEPCAMP_TEAM", null);
        if(!teamTable.next()) {
            populator.addScript(createTeamTableSql);
        }
        ResultSet projectTable = metaData.getTables(null, null, "SEPCAMP_PROJECT", null);
        if(!teamTable.next()) {
            populator.addScript(createProjectTableSql);
        }
        ResultSet functionTable = metaData.getTables(null, null, "SEPCAMP_FUNCTION", null);
        if(!teamTable.next()) {
            populator.addScript(createFunctionTableSql);
        }

        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
