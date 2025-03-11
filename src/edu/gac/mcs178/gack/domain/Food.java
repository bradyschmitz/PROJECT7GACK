package edu.gac.mcs178.gack.domain;

//this is a new class that creates potatoes (or any other food) and allows the users to eat it
public class Food extends Thing{
	//new variable that determines if the user will die or not when they eat
	public boolean isPoison;
	
	public boolean isPoison() {
		return isPoison;
	}
	
	
	public Food(String name, boolean isPoison) {
		super(name);
		this.isPoison = isPoison;
	}
	
	
	public void beEaten(Person theEater) {
		if (theEater.getPossessions().contains(this)) {
			if (isPoison) {
				System.out.println(theEater.getName() + " has eaten the " + getName() + " and got sick!");
				theEater.haveFit();
				theEater.die();
			} else {
				System.out.println(theEater.getName() + " has eaten " + getName() + " and feels great!");
			}
			theEater.lose(this);
		}
		else {
			System.out.println(theEater.getName() + " needs to collect " + getName() + " before they can eat it!");
		}
	}

}
