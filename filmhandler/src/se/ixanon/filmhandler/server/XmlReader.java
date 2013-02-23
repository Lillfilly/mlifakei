package se.ixanon.filmhandler.server;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import se.ixanon.filmhandler.shared.Channel;

public class XmlReader {
	//For reading the file
	DocumentBuilderFactory documentFactory;
	DocumentBuilder builder;
	
	//For writing to the file
	TransformerFactory transformerFactory;
	Transformer transformer;
	
	Document document;
	
	String videoDirectory;
	ArrayList<Channel> channels = new ArrayList<>();
	
	public XmlReader(){
		documentFactory = DocumentBuilderFactory.newInstance();
		transformerFactory = TransformerFactory.newInstance();
		try {
			builder = documentFactory.newDocumentBuilder();
			transformer = transformerFactory.newTransformer();
		} catch (ParserConfigurationException | TransformerConfigurationException e) {
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
			videoDirectory = vid.getTextContent().replaceAll("\"", "");
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
				channels.add(chan);
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
		for(int i = 0; i < channels.size(); i++){
			if(channels.get(i).name == name){
				return channels.get(i);
			}
		}
		System.out.println("Kanalen " + name + " finns inte!");
		return null;
	}

	public ArrayList<Channel> getChannels(){
		return channels;
	}
	
	public String getVideoDirectory(){
		return videoDirectory;
	}
	
	public void editChannel(Channel chan){
		Channel toEdit = null;
		
		for(int i = 0; i < channels.size(); i++){
			if(chan.name.equals(channels.get(i).name)){
				toEdit = channels.get(i);
				break;
			}
		}
		
		if(toEdit != null){
			toEdit.streaming = chan.streaming;
			toEdit.video = chan.video;
			
			//Change the node that is associated with the channel
			NodeList root = document.getElementsByTagName("config");
			Node channels = getNodeByName(root, "channels");
			Node thisChannel = null;
			
			//Find the right channel
			Node temp = null;
			for(int i = 0; i < channels.getChildNodes().getLength(); i++){
				temp = channels.getChildNodes().item(i);
				if(temp.hasAttributes()){
					//Look through each channels attributes for a 'name' attribute
					for(int j = 0; j < temp.getAttributes().getLength(); j++){
						if(temp.getAttributes().item(j).getNodeName() == "name"){
							if(temp.getAttributes().item(j).getNodeValue().equals(toEdit.name)){
								thisChannel = temp;
								break;
							}
						}
					}
				}
			}
			if(thisChannel != null){
				Node video = getNodeByName(thisChannel.getChildNodes(), "video");
				Node streaming = getNodeByName(thisChannel.getChildNodes(), "streaming");
				
				if(video != null){
					video.setTextContent("\"" + toEdit.video + "\"");
				}
				if(streaming != null){
					streaming.setTextContent("\"" + String.valueOf(toEdit.streaming) + "\"");
				}
				
				//Save the changes to the xml file
				DOMSource source = new DOMSource(document);
				StreamResult result = new StreamResult(new File("./config.xml"));
				try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("Kunde inte hitta en kanal med namn " + toEdit.name);
			}
		}
	}
	
	//Prints everything that's important
	public void printChannelInformation(){
		System.out.println("Video directory " + videoDirectory);
		for(int i = 0; i < channels.size(); i++){
			if(channels.get(i).streaming){
				System.out.println("Channel " + channels.get(i).name + " is currently streaming \"" + channels.get(i).video + "\"");
			}else{
				System.out.println("Channel " + channels.get(i).name + " will stream \"" + channels.get(i).video + "\" once it's turned on");
			}
		}
	}
}
