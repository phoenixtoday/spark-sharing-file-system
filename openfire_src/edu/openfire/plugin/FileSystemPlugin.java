package edu.openfire.plugin;

import java.io.File;

import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;

public class FileSystemPlugin implements Plugin {

	public void destroyPlugin() {
	}

	public void initializePlugin(PluginManager arg0, File arg1) {
		System.out.println("INFO: File System Plugin Initialized");
		
		XMPPServer server = XMPPServer.getInstance();
		
		server.getIQRouter().addHandler(new ServerSocketIQHandler());
		System.out.println("INFO: Added server socket iq handler");
	}

}
