package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ACLineClass extends EquipmentClass {
	
    private String BaseVoltageID;
    private double r, x, bch, gch;
	//
	public Element extractNode (Node node){
		Element element = super.extractNode(node);
		this.BaseVoltageID = element.getElementsByTagName("cim:ConductingEquipment.BaseVoltage").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		this.r = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.r").item(0).getTextContent());
		this.x = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.x").item(0).getTextContent());
		this.bch = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.bch").item(0).getTextContent());
		this.gch = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.gch").item(0).getTextContent());
		return element;
	}
	//	
	public String getBaseVoltID() {
	return BaseVoltageID;
	}
	public double getR() {
	return r;
	}
	public double getX() {
	return x;
	}
	public double getBch() {
	return bch;
	}
	public double getGch() {
	return gch;
	}
}
