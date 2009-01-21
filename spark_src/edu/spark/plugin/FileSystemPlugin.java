package edu.spark.plugin;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.tabbedPane.SparkTabbedPane;
import org.jivesoftware.spark.plugin.Plugin;

import edu.spark.plugin.file.FileSenderIQProvider;
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
		
		XMPPConnection conn = SparkManager.getConnection();
		ProviderManager providerManager = ProviderManager.getInstance();
		providerManager.addIQProvider("yrui", "edu:im:file", new FileSenderIQProvider());
		System.out.println("INFO - Registrer file sender iq provider");
		
//		 JID Formate : login_name@server_name/spark
		System.out.println("DEBUG - try to get server ip :" + conn.getServiceName());

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
	
	/**
	 * 发送IQ 请求，并获得最终返回的响应IQ
	 * 
	 * @param req
	 * @return
	 */
	public static IQ sendReqGetResp(IQ req)
	{
		XMPPConnection conn = SparkManager.getConnection();
		System.out.println(req.toXML());

		PacketCollector collector = conn
				.createPacketCollector(new PacketIDFilter(req.getPacketID()));
		conn.sendPacket(req);
		System.out.println("发送IQ :\n" + req.toXML());

		IQ response = (IQ) collector.nextResult(SmackConfiguration
				.getPacketReplyTimeout());
		collector.cancel();

		return response;
	}

}
