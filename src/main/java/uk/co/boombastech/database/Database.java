package uk.co.boombastech.database;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Query;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import uk.co.boombastech.properties.PropertiesProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import static uk.co.boombastech.properties.Property.*;

@Singleton
public class Database {

	private final EbeanServer server;

	@Inject
	public Database(PropertiesProvider propertiesProvider) {
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDriver(propertiesProvider.getProperty(databaseDriver));
		dataSourceConfig.setUsername(propertiesProvider.getProperty(databaseUsername));
		dataSourceConfig.setPassword(propertiesProvider.getProperty(databasePassword));
		dataSourceConfig.setUrl(propertiesProvider.getProperty(databaseUrl));
		dataSourceConfig.setHeartbeatSql(propertiesProvider.getProperty(databaseHeartbeatSql));

		ServerConfig config = new ServerConfig();
		config.setName("pgtest");
		config.setDataSourceConfig(dataSourceConfig);
		config.setDdlGenerate(true);
		config.setDdlRun(true);
		config.setDefaultServer(false);
		config.setRegister(false);

		server = EbeanServerFactory.create(config);
		server.find(String.class).where("asdf").findList();
	}

	public <T> Query<T> createQuery(Class<T> clazz) {
		return server.createQuery(clazz);
	}
}