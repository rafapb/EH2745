package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SynchMachineClass extends EquipmentClass {
	
    private double ratedS, ActivePower, ReactivePower;
    private String GenUnitID, RegControlID;
	//
	public Element extractNode (Node node){	
		Element element = super.extractNode(node);
		this.ratedS = Double.parseDouble(element.getElementsByTagName("cim:RotatingMachine.ratedS").item(0).getTextContent());
		this.GenUnitID = element.getElementsByTagName("cim:RotatingMachine.GeneratingUnit").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		this.RegControlID = element.getElementsByTagName("cim:RegulatingCondEq.RegulatingControl").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
		}
	//
	public Element extractNodeSSH (Node node){
		Element element = (Element) node;
		this.ActivePower = Double.parseDouble(element.getElementsByTagName("cim:RotatingMachine.p").item(0).getTextContent());
		this.ReactivePower = Double.parseDouble(element.getElementsByTagName("cim:RotatingMachine.q").item(0).getTextContent());
		return element;
	}
	//
	public double getRatedS() {
	return ratedS;
	}
	public double getActiveP() {
	return ActivePower;
	}
	public double getReactiveP() {
	return ReactivePower;
	}
	public String getGenUnitID() {
	return GenUnitID;
	}
	public String getRegContID() {
	return RegControlID;
	}
}
