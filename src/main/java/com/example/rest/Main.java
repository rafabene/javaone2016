package com.example.rest;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;

public class Main {

	public static void main(String[] args) throws Exception {

		Swarm swarm = new Swarm();

		swarm.fraction(new DatasourcesFraction().jdbcDriver("h2", (d) -> {
			d.driverClassName("org.h2.Driver");
			d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
			d.driverModuleName("com.h2database.h2");
		}).dataSource("ExampleDS", (ds) -> {
			ds.driverName("h2");
			ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
			ds.userName("foo");
			ds.password("bar");
			ds.backgroundValidation(true);
			ds.validateOnMatch(true);
			ds.checkValidConnectionSql("SELECT 1");
		}));

		// Start the container and deploy the default war
		swarm.start().deploy();

	}

}
