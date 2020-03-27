package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SubstationClass extends IDobject{
	
	private String regionID;
	//
	public Element extractNode (Node node){
		Element element = super.extractNode(node);
		this.regionID = element.getElementsByTagName("cim:Substation.Region").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
	}
    //
	public String getRegionID(){
		return regionID;
	}
}


