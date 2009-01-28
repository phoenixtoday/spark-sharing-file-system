package edu.openfire.plugin.file;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.PluginManager;

public class StoreFileServerSocket implements Runnable {

	private ServerSocket server;

	public void run() {

		server = initServer();

		while (true) {
			try {
				
				Socket connection = connection(server);
				DataInputStream reader = new DataInputStream(connection
						.getInputStream());

				PluginManager pluginManager = XMPPServer.getInstance()
						.getPluginManager();
				String dir = pluginManager.getPluginDirectory(
						pluginManager.getPlugin("admin")).toString()
						+ "/webapp/files/";

				createDirIfNotExist(dir);
				FileOutputStream fileStream = new FileOutputStream(dir
						+ reader.readUTF());
				BufferedOutputStream out = new BufferedOutputStream(fileStream);

				int i;
				while ((i = reader.read()) != -1) {
					out.write(i);
					System.out.println("Receiving data...");
				}
				out.flush();

				reader.close();
				out.close();
				connection.close();

			} catch (FileNotFoundException fe) {
				System.err.println("file not found: " + fe.getMessage());
				fe.printStackTrace();
			} catch (IOException ioe) {
				System.err.println("io error occured: " + ioe.getMessage());
				ioe.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private void createDirIfNotExist(String dir) {
		File fileDir = new File(dir);
		if (fileDir.exists())
			return;
		else
			fileDir.mkdir();
	}

	public ServerSocket getServer() {
		return server;
	}

	private ServerSocket initServer() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(0);
		} catch (IOException e) {
			System.err.println("Can not initialize server socket");
			e.printStackTrace();
		}
		return server;
	}

	private Socket connection(ServerSocket server) {
		Socket connection = null;
		try {
			connection = server.accept();
		} catch (IOException e) {
			System.err.println("Can not create connection: " + e.getMessage());
		}
		return connection;

	}
}
