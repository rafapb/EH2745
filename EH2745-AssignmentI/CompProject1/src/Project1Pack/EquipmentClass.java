package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
//
public class EquipmentClass extends IDobject {
	
	private String equipmentContainer;
	//
	public Element extractNode (Node node){
		Element element = super.extractNode(node);
		this.equipmentContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
	}
    //
	public String getEqContID(){
		return equipmentContainer;
	}
}
