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
import edu.spark.plugin.file.ServerSockectInfo;
import edu.spark.plugin.file.ServerSocketIQ;
import edu.spark.plugin.file.ServerSocketIQProvider;
import edu.spark.plugin.ui.FileSystemPanel;

public class FileSystemPlugin implements Plugin
{
	private static ServerSockectInfo serverInfo;
	
	public static ServerSockectInfo getServerInfo() {
		return serverInfo;
	}

	public static void setServerInfo(ServerSockectInfo serverInfo) {
		FileSystemPlugin.serverInfo = serverInfo;
	}

	public boolean canShutDown()
	{
		return false;
	}

	public void initialize()
	{
		addFileSystemTab();
		
		XMPPConnection conn = SparkManager.getConnection();
		ProviderManager providerManager = ProviderManager.getInstance();
		
		providerManager.addIQProvider("file", "http://jabber.org/protocol/file-sharing", new FileSenderIQProvider());
		System.out.println("INFO - Registrer file sender iq provider");
		
		providerManager.addIQProvider("socket", "http://jabber.org/protocol/file-sharing/socket", new ServerSocketIQProvider());
		System.out.println("INFO - Registrer server socket iq provider");
		
		
		ServerSocketIQ request = new ServerSocketIQ();
		request.setType(IQ.Type.GET);

		sendReqGetResp(request);
		
//		JID Formate : login_name@server_name/spark
		System.out.println("DEBUG - try to get server ip :" + conn.getServiceName());

	}
	
	public void shutdown()
	{
	}

	public void uninstall()
	{
	}
	
	/**
	 * 
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
		System.out.println("send request :\n" + req.toXML());

		IQ response = (IQ) collector.nextResult(SmackConfiguration
				.getPacketReplyTimeout());
		collector.cancel();

		return response;
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
