package edu.openfire.plugin;

import java.io.File;

import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;

import edu.openfire.plugin.file.ServerSocketIQHandler;
import edu.openfire.plugin.file.StoreFileServerSocket;

public class FileSystemPlugin implements Plugin {

	public void destroyPlugin() {
	}

	public void initializePlugin(PluginManager arg0, File arg1) {
		System.out.println("INFO: File System Plugin Initialized");

		XMPPServer server = XMPPServer.getInstance();

		StoreFileServerSocket fileStoreServer = new StoreFileServerSocket();
		Thread t = new Thread(fileStoreServer);
		t.start();

		sleep();
		int port = fileStoreServer.getServer().getLocalPort();

		System.out.println("INFO: Start server socket at: " + port);

		server.getIQRouter().addHandler(new ServerSocketIQHandler(port));
		System.out.println("INFO: Added server socket iq handler");
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
