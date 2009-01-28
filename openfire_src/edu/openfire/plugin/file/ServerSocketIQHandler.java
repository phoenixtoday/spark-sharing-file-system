package edu.openfire.plugin.file;

import org.dom4j.Element;
import org.jivesoftware.openfire.IQHandlerInfo;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.handler.IQHandler;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;

public class ServerSocketIQHandler extends IQHandler {

	private static final String MODULE_NAME = "server socket";

	private static final String NAME_SPACE = "http://jabber.org/protocol/file-sharing/socket";
	
	private IQHandlerInfo info;
	
	private int port;
	
	public ServerSocketIQHandler(int port) {
		super(MODULE_NAME);
		this.port = port;
		info = new IQHandlerInfo("socket", NAME_SPACE);
	}

	@Override
	public IQHandlerInfo getInfo() {
		return info;
	}

	@Override
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		IQ reply = IQ.createResultIQ(packet);
		Element socketElement = packet.getChildElement();
		
		if (!IQ.Type.get.equals(packet.getType()))
		{
			System.out.println("request type should not be: " + packet.getType().name());
			reply.setChildElement(socketElement.createCopy());
			reply.setError(PacketError.Condition.bad_request);
			return reply;
		}

		socketElement.addElement("port").addEntity("socket", "" + port);
		reply.setChildElement(socketElement.createCopy());

		System.out.println("created server socket: " + reply.toXML());
		return reply;
	}

}
