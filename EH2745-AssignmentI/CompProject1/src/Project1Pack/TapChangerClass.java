package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TapChangerClass extends IDobject {
	
    private double step;
	//
	public Element extractNode (Node node){
		Element element = super.extractNode(node);
		return element;
	}
	//
	public Element extractNodeSSH (Node node){
		Element element = (Element) node;
		this.step = Double.parseDouble(element.getElementsByTagName("cim:TapChanger.step").item(0).getTextContent());
		return element;
	}
	//
	public double getStep(){
		return step;
	}
}
