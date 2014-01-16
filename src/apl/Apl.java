package apl;

import java.util.Arrays;

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
		
		System.out.println("A:\t" + Arrays.toString(p.getTimes("A")));
		System.out.println("B:\t" + Arrays.toString(p.getTimes("B")));
		System.out.println("C:\t" + Arrays.toString(p.getTimes("C")));
		System.out.println("D:\t" + Arrays.toString(p.getTimes("D")));
		System.out.println("E:\t" + Arrays.toString(p.getTimes("E")));
		System.out.println("F:\t" + Arrays.toString(p.getTimes("F")));
		System.out.println("G:\t" + Arrays.toString(p.getTimes("G")));
	}
	
}
