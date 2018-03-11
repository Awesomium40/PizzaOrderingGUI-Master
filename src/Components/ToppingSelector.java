package Components;
import javax.swing.*;

import DataModel.PizzaTopping;
import Enums.ToppingsChoice;
import EventListeners.ButtonActionListener;
import EventListeners.Interfaces.IPizzaChangingListener;
import EventRaisers.NotifyPizzaChangingBase;
import EventRaisers.Interfaces.INotifyPizzaChanging;
import Events.PizzaChangingEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;

public class ToppingSelector extends NotifyPizzaChangingBase
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4586174820577847191L;
	//Component objects
	public JRadioButton btn_none;
	private JRadioButton btn_full;
	private JRadioButton btn_firstHalf;
	private JRadioButton btn_secondHalf;
	private JCheckBox chk_double;
	private JLabel lbl_toppingName;
	private ButtonGroup bg;
	private ButtonActionListener radioHandler;
	private ButtonActionListener chkHandler;
	
	//Fields for the state 
	private boolean isPremiumTopping;
	
	//Constructors
	public ToppingSelector(String name, boolean isPremium, boolean addLabels)	
	{
		super(new GridBagLayout());
		//Construct components
		lbl_toppingName = new JLabel(name, 10);
		lbl_toppingName.setPreferredSize(new Dimension(100, 20));

		
		if (isPremium)
			lbl_toppingName.setForeground(Color.RED);
		
		radioHandler = new ButtonActionListener(this::onRadioButtonClick);
		chkHandler = new ButtonActionListener(this::onCheckBoxClick);
		
		subscribers = new ArrayList<IPizzaChangingListener>();

		
		btn_none = new JRadioButton("", true);
		btn_full = new JRadioButton();
		btn_firstHalf = new JRadioButton();
		btn_secondHalf = new JRadioButton();
		chk_double = new JCheckBox();
		bg = new ButtonGroup();
		
		btn_none.setPreferredSize(new Dimension(20, 35));
		btn_full.setPreferredSize(new Dimension(20, 35));
		btn_firstHalf.setPreferredSize(new Dimension(20, 35));
		btn_secondHalf.setPreferredSize(new Dimension(20, 35));

		this.isPremiumTopping = isPremium;
		
		//Add components to the panel - Panel has GridBagLayout, so need to set the GridBagConstraints appropriately
		add(lbl_toppingName, new GridBagConstraints(0, 0, 1, 1, .25, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		add(btn_none, new GridBagConstraints(1, 0, 1, 1, .15, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 25, 0, 0), 30, 0));
		add(btn_full, new GridBagConstraints(2, 0, 1, 1, .15, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 30, 0));
		add(btn_firstHalf, new GridBagConstraints(3, 0, 1, 1, .15, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 30, 0));
		add(btn_secondHalf, new GridBagConstraints(4, 0, 1, 1, .15, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 30, 0));
		add(chk_double, new GridBagConstraints(5, 0, 1, 1, .15, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 30, 0));
		
		
		//add event listeners
		btn_none.addActionListener(radioHandler);
		btn_full.addActionListener(radioHandler);
		btn_firstHalf.addActionListener(radioHandler);
		btn_secondHalf.addActionListener(radioHandler);
		chk_double.addActionListener(chkHandler);
		
		//Group together radiobuttons
		bg.add(btn_none);
		bg.add(btn_full);
		bg.add(btn_firstHalf);
		bg.add(btn_secondHalf);
		
	}
	
	private PizzaTopping getTopping()
	{
		return new PizzaTopping(lbl_toppingName.getText(), btn_none.isSelected() ? ToppingsChoice.NONE : 
			                                               btn_full.isSelected() ? ToppingsChoice.FULL : 
			                                               btn_firstHalf.isSelected() ? ToppingsChoice.FIRST_HALF : 
			                                               ToppingsChoice.SECOND_HALF, chk_double.isSelected(), this.isPremiumTopping);
	}

	
	private void onRadioButtonClick(ActionEvent e)
	{
		raisePizzaChangingEvent(new PizzaChangingEvent(this, 
				this.getTopping(), btn_none.isSelected() ? PizzaChangingEvent.TOPPINGS_REMOVING : 
					PizzaChangingEvent.TOPPINGS_CHANGING, e.getActionCommand()));
	}
	
	private void onCheckBoxClick(ActionEvent e)
	{
		raisePizzaChangingEvent(new PizzaChangingEvent(this, 
				this.getTopping(), btn_none.isSelected() ? PizzaChangingEvent.TOPPINGS_REMOVING : 
					PizzaChangingEvent.TOPPINGS_CHANGING, e.getActionCommand()));
	}

	public void resetControls()
	{
		// TODO Auto-generated method stub
		if (!btn_none.isSelected())
			btn_none.doClick();
		if (chk_double.isSelected())
			chk_double.setSelected(false);
	}
	

}
