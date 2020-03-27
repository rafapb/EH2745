package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RegControlClass extends IDobject {
	
    private double targetValue;

	public Element extractNodeSSH (Node node){
		Element element = (Element) node;
		this.targetValue = Double.parseDouble(element.getElementsByTagName("cim:RegulatingControl.targetValue").item(0).getTextContent());
		return element;
	}
	
	public double getTargetValue(){
		return targetValue;
	}

}
