package edu.spark.plugin;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.tabbedPane.SparkTabbedPane;
import org.jivesoftware.spark.plugin.Plugin;

public class FileSystemPlugin implements Plugin
{

	public boolean canShutDown()
	{
		return false;
	}

	public void initialize()
	{
		addFileSystemTab();
	}

	public void shutdown()
	{
	}

	public void uninstall()
	{
	}
	
	private void addFileSystemTab()
	{
		SparkTabbedPane pane = SparkManager.getWorkspace().getWorkspacePane();
		pane.addTab("File System", new ImageIcon(getClass().getResource(
				"images/sharing_file_system.png")), fileSystemPane());
	}
	
	private JScrollPane fileSystemPane()
	{
		JScrollPane pane = new JScrollPane();
		return pane;
	}

}
