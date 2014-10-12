package com.neroapp.persistence.config;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;

@ApplicationScoped
class DB4oConnectionFactory implements Serializable {

	private static final long serialVersionUID = 523673311441549625L;

	private static final String DATABASE_FILE_PATH = "NeroDB.db4o";
	private static final String PASSWORD = "admpasswd";
	private static final String USER = "admin";
	private static final int PORT = 6001;

	private ObjectServer objectServer;
	private ObjectContainer objectContainer;

	@PostConstruct
	public void initialize() {
		if (this.objectServer == null) {
			ServerConfiguration serverConfig = Db4oClientServer
					.newServerConfiguration();
			serverConfig.common().messageLevel(1);
			this.objectServer = Db4oClientServer.openServer(serverConfig,
					DATABASE_FILE_PATH, PORT);
			this.objectServer.grantAccess(USER, PASSWORD);
		}
	}

	@Produces
	public ObjectContainer getObjectContainer() {
		if (this.objectContainer == null) {
			try {
				this.initialize();
				this.objectContainer = Db4oClientServer.openClient(
						Db4oClientServer.newClientConfiguration(), "localhost",
						PORT, USER, PASSWORD);
			} catch (Exception e) {
				// TODO LOG
				e.printStackTrace();
			}
		}
		return objectContainer;
	}

}
