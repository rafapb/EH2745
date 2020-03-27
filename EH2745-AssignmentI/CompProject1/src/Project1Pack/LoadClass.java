package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class LoadClass extends EquipmentClass {
	
	private double ActivePower, ReactivePower;
	//
	public Element extractNodeSSH (Node node){
		Element element = (Element) node;
		this.ActivePower = Double.parseDouble(element.getElementsByTagName("cim:EnergyConsumer.p").item(0).getTextContent());
		this.ReactivePower = Double.parseDouble(element.getElementsByTagName("cim:EnergyConsumer.q").item(0).getTextContent());
		return element;
	}
	//
	public double getLoadActiveP() {
	return ActivePower;
	}
	public double getLoadReactiveP() {
	return ReactivePower;
	}
}
