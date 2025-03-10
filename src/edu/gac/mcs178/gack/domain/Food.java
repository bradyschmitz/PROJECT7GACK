package edu.gac.mcs178.gack.domain;

//this is a new class that creates potatoes (or any other food) and allows the users to eat it
public class Food extends Thing{
	public Food(String name) {
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
