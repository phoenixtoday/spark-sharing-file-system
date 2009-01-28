package edu.spark.plugin.file;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileSender {

	private static FileSender instance;
	
	private int port;

	private String address;

	public FileSender(String address, int port) {
		this.address = address;
		this.port = port;
		instance = this;
	}

	public int getPort() {
		return port;
	}

	public String getAddress() {
		return address;
	}

	public void sendFile(File file) {
		try {
			System.out.print("Sending data...\n");
			Socket socket;

			socket = new Socket(address, port);

			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fis);
			DataOutputStream out = new DataOutputStream(socket
					.getOutputStream());
			out.writeUTF(file.getName());
			int i;
			while ((i = in.read()) != -1) {
				out.write(i);
			}

			out.flush();
			out.close();
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FileSender getInstance() {
		return instance;
	}

}
