package se.ixanon.filmhandler.server;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import se.ixanon.filmhandler.shared.Channel;

public class XmlReader {
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;
	
	String videoDirectory;
	HashMap<String, Channel> channels = new HashMap<>();
	
	public XmlReader(){
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public boolean parseFile(String path){
		File file = new File(path);
		if(file.exists() == false){
			JOptionPane.showMessageDialog(null, "Filen kunde inte hittas: " + path);
			return false;
		}
		try {
			document = builder.parse(file);
			document.getDocumentElement().normalize();
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//Builds the xml file into a list of channels + filmPath
	public boolean build(){
		NodeList root = document.getElementsByTagName("config");
		
		//Reads in the root channel node
		Node channelsNode = getNodeByName(root, "channels");
		Node vid = getNodeByName(root, "videoDirectory");
		
		if((new File(vid.getTextContent().replaceAll("\"", ""))).exists()){
			videoDirectory = vid.getTextContent();
		}else{
			System.err.println("<videoDirectory> doesn't specify a valid path!");
			return false;
		}
		
		//Create channels
		for(int i = 0; i < channelsNode.getChildNodes().getLength(); i++){
			if(channelsNode.getChildNodes().item(i).getNodeName() == "channel"){
				Channel chan = new Channel();
				Node chanNode = channelsNode.getChildNodes().item(i);
				chan.name = getAttributeValue(chanNode, "name");
				
				Node temp;
				
				//Looks for a child node called 'film' - this node needs to include the currently picked film
				if((temp = getNodeByName(chanNode.getChildNodes(), "video")) != null){
					chan.video = temp.getTextContent().replaceAll("\"", "");
				}else{
					System.err.println("Kanalen " + chan.name + " innehåller inte en under tagg som heter 'video'!");
				}
				
				//Looks for a child node called 'streaming' - this node needs to tell wether the channel is streaming or not
				if((temp = getNodeByName(chanNode.getChildNodes(), "streaming")) != null){
					chan.streaming = Boolean.parseBoolean(temp.getTextContent().replaceAll("\"", ""));
					
					//Makes note of the fact that all information hasn't been given by the user
					if(temp.getTextContent().length() == 0){
						System.err.println("Varning kanalen " + chan.name + "s 'streaming' tagg innehåller varken \"true\" eller \"false\", sätts till \"false\" som standard.");
					}
				}else{
					System.err.println("Kanalen " + chan.name + " innehåller inte en under tagg som heter 'streaming'!");
				}
				channels.put(chan.name, chan);
			}
		}
		return true;
	}
	
	//An attempt at returning the value of a certain attribute within a tag
	public String getAttributeValue(Node n, String name){
		if(n.hasAttributes()){
			for(int i = 0; i < n.getAttributes().getLength(); i++){
				if(n.getAttributes().item(i).getNodeName() == name){
					return n.getAttributes().item(i).getNodeValue();
				}
			}
		}else{
			System.err.println("Node doesn't contain an attribute called " + name);
			return "'??'";
		}
		
		System.err.println("Node doesn't contain an attribute called " + name);
		return "'??'";
	}
	
	//A recursive get where a certain node is found
	public Node getNodeByName(NodeList parent, String name){
		for(int i = 0; i < parent.getLength(); i++){
			if(parent.item(i).getNodeName() == name){
				return parent.item(i);
			}
			
			if(parent.item(i).hasChildNodes()){
				
				//Looks for the node amongst his children, one is only returned if
				//it isn't null
				Node n = getNodeByName(parent.item(i).getChildNodes(), name);
				if(n != null){
					return n;
				}
			}
		}
		
		return null;
	}
	
	public Channel getChannel(String name){
		if(channels.containsKey(name)){
			return channels.get(name);
		}
		System.out.println("Kanalen " + name + " finns inte!");
		return null;
	}
	
	//Prints everything that's important
	public void printChannelInformation(){
		System.out.println("Video directory " + videoDirectory);
		for(Map.Entry<String, Channel> k : channels.entrySet()){
			if(k.getValue().streaming){
				System.out.println("Channel " + k.getValue().name + " is currently streaming \"" + k.getValue().video + "\"");
			}else{
				System.out.println("Channel " + k.getValue().name + " will stream \"" + k.getValue().video + "\" once it's turned on");
			}
		}
	}
}
