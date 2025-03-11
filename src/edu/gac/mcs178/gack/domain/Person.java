package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

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
			possessions.add(thing);
			say("I take " + thing);
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
	//first checks to see if the items they have are food
	public List<String> getFoodItems(){
		List<String> foodItems = new ArrayList<String>();
		for (Thing item: possessions) {
			if (item instanceof Food) {
				foodItems.add(item.getName());
			}
		}
		return foodItems;
		
	}
	
	public void eat (String foodName) {
		//create variable to hold the things that can be eaten
		Thing edibleFood = null;

        // Find the edible items in possessions
        for (Thing item : possessions) {
            if (item.getName().equalsIgnoreCase(foodName) && item instanceof Food) {
                edibleFood = item;
                break;
            }
        }

        // If the food item was found, eat it
        if (edibleFood != null) {
            ((Food) edibleFood).beEaten(this);
            possessions.remove(edibleFood); // Remove the item after eating
            say("I just ate " + foodName + ".");
        } else {
            say("I have no food to eat");
        }
    }
	

	
	@Override
	public String toString() {
		return name;
	}
}
