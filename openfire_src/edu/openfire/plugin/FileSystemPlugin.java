package edu.openfire.plugin;

import java.io.File;

import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;

public class FileSystemPlugin implements Plugin {

	@Override
	public void destroyPlugin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializePlugin(PluginManager arg0, File arg1) {
		System.out.println("INFO: File System Plugin Initialized");
		
		XMPPServer server = XMPPServer.getInstance();
	}

}
