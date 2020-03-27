package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TerminalClass extends IDobject {
	
	private String ConductingEqID;
	private String ConnectNodeID;
	//	
    public Element extractNode (Node node){	
		Element element = super.extractNode(node);
		this.ConductingEqID = element.getElementsByTagName("cim:Terminal.ConductingEquipment").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		this.ConnectNodeID = element.getElementsByTagName("cim:Terminal.ConnectivityNode").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
	}
    //
    public String getConductingEqID(){
	return ConductingEqID;
	}

    public String getConnectNodeID() {
	return ConnectNodeID;
	}
}
