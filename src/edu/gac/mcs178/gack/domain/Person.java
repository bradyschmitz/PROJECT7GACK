package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import edu.gac.mcs178.gack.Utility;

public class Person {
	
	private String name;
	private Place place;
	private List<Thing> possessions;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public Place getPlace() { return place; }
	public List<Thing> getPossessions() { return possessions; }

	public Person(String name, Place place) {
		super();
		this.name = name;
		this.place = place;
		this.possessions = new ArrayList<Thing>();
		place.gain(this);
	}
	
	public void say(String text) {
		Utility.displayMessage("At " + place + ": " + this + " says -- " + text);
	}
	
	public List<Thing> otherThingsAtSamePlace() {
		List<Thing> otherThingsAtSamePlace = new ArrayList<Thing>();
		for (Thing thing : place.getContents()) {
			if (!possessions.contains(thing)) {
				otherThingsAtSamePlace.add(thing);
			}
		}
		return otherThingsAtSamePlace;
	}
	
	public List<Person> otherPeopleAtSamePlace() {
		List<Person> otherPeopleAtSamePlace = new ArrayList<Person>();
		for (Person occupant : place.getOccupants()) {
			if (!occupant.equals(this)) {
				otherPeopleAtSamePlace.add(occupant);
			}
		}
		return otherPeopleAtSamePlace;
	}
	
	public void lookAround() {
		say("I see " + Utility.verbalizeList(otherPeopleAtSamePlace(), "no people") +
				" and " + Utility.verbalizeList(otherThingsAtSamePlace(), "no objects") +
				" and can go " + Utility.verbalizeList(place.exits(), "nowhere"));
	}
	
	public void listPossessions() {
		say("I have " + Utility.verbalizeList(possessions, "nothing"));
	}
	
	public void read(Scroll scroll) {
		if ((scroll.isOwned()) && (scroll.getOwner().equals(this))) {
			scroll.beRead();
		} else {
			Utility.displayMessage(this + " does not have " + scroll);
		}
	}
	
	public void haveFit() {
		say("Yaaaah! I am upset");
	}
	
	public void moveTo(Place newPlace) {
		Utility.displayMessage(this + " moves from " + place + " to " + newPlace);
		place.lose(this);
		newPlace.gain(this);
		for(Thing possession : possessions) {
			place.lose(possession);
			newPlace.gain(possession);
		}
		place = newPlace;
		greet(otherPeopleAtSamePlace());
	}
	
	public void go(String direction) {
		Place newPlace = place.neighborTowards(direction);
		if (newPlace == null) {
			Utility.displayMessage("You cannot go " + direction + " from " + place);
		} else {
			moveTo(newPlace);
		}
	}
	
	//updated this function so that the user eats the item when they take it
	public void take(Thing thing) {
	    if (equals(thing.getOwner())) {
	        Utility.displayMessage(this + " already has " + thing);
	    } else {
	        if (thing.isOwned()) {
	            Person owner = thing.getOwner();
	            owner.lose(thing);
	            owner.haveFit();
	        }
	        thing.setOwner(this);
	        
	        if (thing instanceof Food) {
	            Food food = (Food) thing;
	            Utility.displayMessage(this + " has grabbed the " + food.getName() + " and has eaten it. Delicious!");
	            eat(food);
	            if (food.isPoison()) {
	            	Utility.displayMessage(this + " has gotten very sick after that meal. It had food poisoning! " + this + " does not know how much longer they will last...");
	            	die();
	            }
	        } else {
	            possessions.add(thing);
	            say("I take " + thing);
	        }
	    }
	}
	
	//new give button
	public void give(Thing thing, Person recipient) {
		//need to make sure the person has the thing to give
		if (!equals(thing.getOwner())) {
			Utility.displayMessage(this + " does not have " + thing);
		} else {
			thing.becomeUnowned();
			this.possessions.remove(thing);
			recipient.possessions.add(thing);
			thing.setOwner(recipient);
			this.say("Here, have my charity " + thing + " to " + recipient);
		}
	}
	
	public void lose(Thing thing) {
		if (!equals(thing.getOwner())) {
			Utility.displayMessage(this + " doesn't have " + thing);
		} else {
			thing.becomeUnowned();
			possessions.remove(thing);
			say("I lose " + thing);
		}
	}
	
	public void greet(List<Person> people) {
		if (!people.isEmpty()) {
			say("Hi " + Utility.verbalizeList(people, "no one")); // "no one" can't happen
		}
	}
	
	//new eat button
	public void eat (Food food) {
		if (possessions.contains(food)) {
			food.beEaten(this);
		}
		else {
			System.out.println(name + "does not have any " + food.getName() + " to eat.");
		}
	}
	
	//new die function for if the user gets food poisoning
	public void die() {
		//use pop up method
	    JOptionPane.showMessageDialog(null, name + " has died from food poisoning. Should have chosen a more healthy option, better luck next time. \nGAME OVER.", 
	                                  "Game Over", JOptionPane.ERROR_MESSAGE);
	    
	    System.exit(0); 
	}
	

	
	@Override
	public String toString() {
		return name;
	}
}
