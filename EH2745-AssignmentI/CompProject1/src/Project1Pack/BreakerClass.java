package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BreakerClass extends EquipmentClass {
//	
private boolean state;
	//
	public Element extractNodeSSH (Node node){
		Element element = (Element) node;
		this.state = Boolean.valueOf(element.getElementsByTagName("cim:Switch.open").item(0).getTextContent());
		return element;
	}
	//	
	public boolean getBRstate() {
	return state;
	}
}
