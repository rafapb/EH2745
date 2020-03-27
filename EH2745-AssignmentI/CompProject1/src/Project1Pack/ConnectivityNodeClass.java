package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ConnectivityNodeClass extends IDobject {
	
	private String CNodeContainerID;
	//	
    public Element extractNode (Node node){	
		Element element = super.extractNode(node);
		this.CNodeContainerID = element.getElementsByTagName("cim:ConnectivityNode.ConnectivityNodeContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
	}
    //
    public String getCNodeContainerID(){
	return CNodeContainerID;
	}
}
