package edu.spark.plugin;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.tabbedPane.SparkTabbedPane;
import org.jivesoftware.spark.plugin.Plugin;

import edu.spark.plugin.ui.FileSystemPanel;

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
		System.out.println("INFO : Initialize File System Plugin UI");
		pane.addTab("File System", new ImageIcon(getClass().getResource(
				"images/sharing_file_system.png")), fileSystemPane());
	}
	
	private JScrollPane fileSystemPane()
	{
		JScrollPane pane = new JScrollPane();
		JPanel fileSystemPanel = new FileSystemPanel();
		pane.setViewportView(fileSystemPanel);
		return pane;
	}

}
