package edu.gac.mcs178.gack.domain;


//this is a new class that creates potatoes and allows the users to eat them
public class Potatoes extends Thing{
	public Potatoes(String name) {
		super(name);
	}
	
	
	public void beEaten(Person theEater) {
		if (theEater.getPossessions().contains(this)) {
			theEater.lose(this);
			System.out.println(theEater.getName() + " has eaten " + getName() + "!");
		}
		else {
			System.out.println(theEater.getName() + " needs to collect " + getName() + " before they can eat it!");
		}
	}
}
