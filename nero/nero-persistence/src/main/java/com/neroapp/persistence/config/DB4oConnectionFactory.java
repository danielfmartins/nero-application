package com.neroapp.persistence.config;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;
import com.neroapp.annotations.Value;

@ApplicationScoped
class DB4oConnectionFactory implements Serializable {

	private static final long serialVersionUID = 523673311441549625L;

	@Inject
	@Value("db4o.connection.host")
	private String host;
	
	@Inject
	@Value("db4o.connection.port")
	private Integer port;
	
	@Inject
	@Value("db4o.connection.database")
	private String database;
	
	@Inject
	@Value("db4o.connection.user")
	private String user;
	
	@Inject
	@Value("db4o.connection.passwd")
	private String password;

	private ObjectServer objectServer;
	private ObjectContainer objectContainer;

	@PostConstruct
	public void initialize() {
		if (this.objectServer == null) {
			ServerConfiguration serverConfig = Db4oClientServer
					.newServerConfiguration();
			serverConfig.common().messageLevel(1);
			this.objectServer = Db4oClientServer.openServer(serverConfig,
					this.database, this.port);
			this.objectServer.grantAccess(this.user, this.password);
		}
	}

	@Produces
	public ObjectContainer getObjectContainer() {
		if (this.objectContainer == null) {
			try {
				this.initialize();
				this.objectContainer = Db4oClientServer.openClient(
						Db4oClientServer.newClientConfiguration(), this.host,
						this.port, this.user, this.password);
			} catch (Exception e) {
				// TODO LOG
				e.printStackTrace();
			}
		}
		return objectContainer;
	}

}
