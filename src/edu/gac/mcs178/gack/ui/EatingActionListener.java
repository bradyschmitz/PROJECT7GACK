package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class EatingActionListener implements ActionListener {
	
	
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox eatJComboBox;
	private boolean enabled;

	public EatingActionListener(GraphicalUserInterface gui, Person player, JComboBox eatJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.eatJComboBox = eatJComboBox;
		this.enabled = true;
		eatJComboBox.addItem("Eat...");
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			String item = (String) eatJComboBox.getSelectedItem();
			if (!item.equals("Eat...")) {
				gui.displayMessage("\n>>> Eat " + item);
				player.eat(item);
				gui.playTurn();
			}
		}
	}

	public void updateJComboBox() {
		eatJComboBox.removeAllItems();
		for (String food : player.getFoodItems()) {
			eatJComboBox.addItem(food);
		}
		
	}
}
