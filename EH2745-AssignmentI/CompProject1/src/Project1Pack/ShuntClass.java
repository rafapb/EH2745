package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ShuntClass extends EquipmentClass {

	private double Bvalue, Gvalue, NomVolt;
	//
	public Element extractNode (Node node){
		Element element = super.extractNode(node);
		this.Bvalue = Double.parseDouble(element.getElementsByTagName("cim:LinearShuntCompensator.bPerSection").item(0).getTextContent());
		this.Gvalue = Double.parseDouble(element.getElementsByTagName("cim:LinearShuntCompensator.gPerSection").item(0).getTextContent());
		this.NomVolt = Double.parseDouble(element.getElementsByTagName("cim:ShuntCompensator.nomU").item(0).getTextContent());
		return element;
	}
	//
	public double getBvalue() {
	return Bvalue;
	}
	public double getGvalue() {
	return Gvalue;
	}
	public double getNomVolt() {
	return NomVolt;
	}
}
