package edu.gac.mcs178.gack;

import edu.gac.mcs178.gack.domain.AutoPerson;
import edu.gac.mcs178.gack.domain.Food;
import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Place;
import edu.gac.mcs178.gack.domain.Scroll;
import edu.gac.mcs178.gack.domain.Thing;
import edu.gac.mcs178.gack.domain.Witch;
import edu.gac.mcs178.gack.domain.Wizard;

public class GackWorld extends World {
	
	public GackWorld() {
		super();
		Place foodService = new Place("Food Service");
		Place po = new Place("Post Office");
		Place alumniHall = new Place("Alumni Hall");
		Place chamberOfWizards = new Place("Chamber of Wizards");
		Place library = new Place("Library");
		Place goodShipOlin = new Place("Good Ship Olin");
		Place lounge = new Place("Lounge");
		Place computerLab = new Place("Computer Lab");
		Place offices = new Place("Offices");
		Place dormitory = new Place("Dormitory");
		Place pond = new Place("Pond");
		//new place called lund
		Place lund = new Place("Lund");
		
		foodService.addNewNeighbor("down", po);
		po.addNewNeighbor("south", alumniHall);
		alumniHall.addNewNeighbor("north", foodService);
		alumniHall.addNewNeighbor("east", chamberOfWizards);
		alumniHall.addNewNeighbor("west", library);
		chamberOfWizards.addNewNeighbor("west", alumniHall);
		chamberOfWizards.addNewNeighbor("south", dormitory);
		dormitory.addNewNeighbor("north", chamberOfWizards);
		dormitory.addNewNeighbor("west", goodShipOlin);
		library.addNewNeighbor("east", library);
		library.addNewNeighbor("south", goodShipOlin);
		goodShipOlin.addNewNeighbor("north", library);
		goodShipOlin.addNewNeighbor("east", dormitory);
		goodShipOlin.addNewNeighbor("up", lounge);
		lounge.addNewNeighbor("west", computerLab);
		lounge.addNewNeighbor("south", offices);
		computerLab.addNewNeighbor("east", lounge);
		offices.addNewNeighbor("north", lounge);
		//make lund new neighbor for the dormitory
		dormitory.addNewNeighbor("northwest", lund);
		
		new AutoPerson("Max", dormitory, 2);
		new AutoPerson("Karl", computerLab, 4);
		new Witch("Barbara", offices, 3, pond);
		new Wizard("Elvee", offices, 1, chamberOfWizards);
		//create new witch named Jacob
		new Witch("Jacob", lund, 2, pond);
		
		
		
		dormitory.gain(new Scroll("Late Lab Report"));
		
		
		lounge.gain(new Thing("Karl's glasses"));
		//add new scroll to lund
		lund.gain(new Scroll ("Louis don't barf magical enchantment"));
		
		library.gain(new Scroll("Scroll of Enlightenment"));
		String[] someTitles = {"War and Peace", "Iliad", "Collected Works of Rilke"};
		for (String title : someTitles) {
			library.gain(new Scroll(title));
		}
		computerLab.gain(new Scroll("Unix Programmers Manual"));
		computerLab.gain(new Scroll("NeXT User's Reference"));
		
		
		setPlayer(new Person("player", dormitory));
		
		//add new food (potatoes)
		Food obPotatoes = new Food("Potatoes O'Brien", true);
		Food hashBrowns = new Food("Hash Browns", false);
		Food taterTots = new Food("Tater Tots", true);
		Food dicedPotatoes = new Food("Seasoned Diced Potatoes", false);
		
		//add new food (fruit), and so on....
		Food apple = new Food("Apple", false);
		
		//place the food in the foodService
		dormitory.gain(obPotatoes);
		foodService.gain(hashBrowns);
		lounge.gain(taterTots);
		foodService.gain(dicedPotatoes);
		goodShipOlin.gain(apple);
		
	}
}
