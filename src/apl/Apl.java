package apl;

import pertnetwerk.PERTNetwerk;

public class Apl {

	public static void main (String args[]) {
		PERTNetwerk p = new PERTNetwerk();
		
		//SITUATIE UIT OPDRACHT
		p.addStartingPoint("A");
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
		
		System.out.println("A:\t" + p.getTimes("A"));
		System.out.println("B:\t" + p.getTimes("B"));
		System.out.println("C:\t" + p.getTimes("C"));
		System.out.println("D:\t" + p.getTimes("D"));
		System.out.println("E:\t" + p.getTimes("E"));
		System.out.println("F:\t" + p.getTimes("F"));
		System.out.println("G:\t" + p.getTimes("G"));
	}
	
}
