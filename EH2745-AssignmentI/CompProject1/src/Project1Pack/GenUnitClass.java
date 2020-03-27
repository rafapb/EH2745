package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GenUnitClass extends EquipmentClass {
	
	private double MaxP, MinP;
	//
	public Element extractNode (Node node){	
		Element element = super.extractNode(node);
		this.MaxP = Double.parseDouble(element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0).getTextContent());
		this.MinP = Double.parseDouble(element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0).getTextContent());
		return element;
		}
	//
	public double getMaxP() {
	return MaxP;
	}
	public double getMinP() {
	return MinP;
	}
}
