package com.cloudifiers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseSecret {

	private String username;
	private String password;
	private String host;
	private String engine;
	private String port;
	private String dbInstanceIdentifier;
	private String databaseName;
	private String databaseDriver;
	
	@Override
	public String toString() {
		return "DatabaseSecret [username=" + username + ", password=" + password + ", host=" + host + ", engine="
				+ engine + ", port=" + port + ", dbInstanceIdentifier=" + dbInstanceIdentifier + ", databaseName="
				+ databaseName + ", databaseDriver=" + databaseDriver + "]";
	}
}
