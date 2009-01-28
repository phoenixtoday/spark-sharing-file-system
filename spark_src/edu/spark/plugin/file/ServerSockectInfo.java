package edu.spark.plugin.file;

public class ServerSockectInfo {

	private int port;
	
	private String address;
	
	public ServerSockectInfo(String address, int port){
		this.address = address;
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}

	public String getAddress() {
		return address;
	}
	
}
