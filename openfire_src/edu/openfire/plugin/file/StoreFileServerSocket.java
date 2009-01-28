package edu.openfire.plugin.file;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StoreFileServerSocket implements Runnable {

	private ServerSocket server;

	public void run() {
		while (true) {
			server = initServer();

			Socket connection = connection(server);

			try {

				DataInputStream reader = new DataInputStream(connection
						.getInputStream());

				String dir = "/Users/twer/Download/down"; // TODO
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
				server.close();

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
	
	public ServerSocket getServer(){
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
