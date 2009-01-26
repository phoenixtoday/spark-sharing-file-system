package edu.spark.plugin.file;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class ServerSocketIQProvider implements IQProvider{

	private static final String DOCUMENT_ELEMENT_NAME = "socket";
	
	public IQ parseIQ(XmlPullParser parser) throws Exception{
		return null;
	}

}
