package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class EatActionListener implements ActionListener{
    private static final Thing INSTRUCTIONS = new Thing("Eat ...");

    private GraphicalUserInterface gui;
    private Person player;
	private JComboBox dropJComboBox;
	private boolean enabled;
	private List<Thing> things;
}