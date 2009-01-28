package edu.spark.plugin.file;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.spark.SparkManager;
import org.xmlpull.v1.XmlPullParser;

import edu.spark.plugin.FileSystemPlugin;

public class ServerSocketIQProvider implements IQProvider {

	private static final String DOCUMENT_ELEMENT_NAME = "socket";

	public IQ parseIQ(XmlPullParser parser) throws Exception {
		boolean done = false;
		while (!done) {
			int port;
			int event = parser.next();
			if (event == XmlPullParser.START_TAG) {
				if (parser.getName().equals("port")) {
					port = Integer.parseInt(parser.nextText());
					FileSystemPlugin
							.setServerInfo(new ServerSockectInfo(SparkManager
									.getConnection().getHost(), port));
					ServerSockectInfo info = FileSystemPlugin.getServerInfo();
					System.out.println("Server Socket info: server - "
							+ info.getAddress() + " port - " + info.getPort());
				}
			}

			if (parser.next() == XmlPullParser.END_TAG
					&& parser.getName().equals(DOCUMENT_ELEMENT_NAME))
				done = true;
		}
		return null;
	}

}
