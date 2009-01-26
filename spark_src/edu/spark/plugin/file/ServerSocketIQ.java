package edu.spark.plugin.file;

import org.jivesoftware.smack.packet.IQ;

public class ServerSocketIQ extends IQ {

	@Override
	public String getChildElementXML() {
		StringBuffer buf = new StringBuffer();
		buf.append("<socket xmlns=\"http://jabber.org/protocol/file-sharing/socket\">");
		buf.append("</socket>");
		return buf.toString();
	}

}
