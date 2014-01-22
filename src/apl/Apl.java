package apl;

import pertnetwerk.PERTNetwerk;

public class Apl {

	public static void main (String args[]) {
		PERTNetwerk p = new PERTNetwerk();
		
		//Situatie uit opdracht
		p.addActivity("A", "B", 3);
		p.addActivity("A", "D", 1);
		p.addActivity("A", "G", 3);
		p.addActivity("B", "F", 1);
		p.addActivity("B", "C", 1);
		p.addActivity("D", "E", 1);
		p.addActivity("G", "F", 2);
		p.addActivity("E", "F", 1);
		p.addActivity("F", "C", 1);
		
		p.vroegsteTijden();
		p.laatsteTijden();
		
		System.out.println(p.toString());
	}
	
}
