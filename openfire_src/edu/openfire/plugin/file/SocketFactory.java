package edu.openfire.plugin.file;

import java.io.IOException;
import java.net.ServerSocket;

import org.dom4j.Element;

public class SocketFactory {

	public static void createServerSocket(Element socketElement) {
//		create a server socket
		ServerSocket server = null;
		try {
			server = new ServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Server Socket port :" + server.getLocalPort());
		
//		fill the element
	}


}
