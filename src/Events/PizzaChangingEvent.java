package Events;
import java.awt.event.ActionEvent;
import DataModel.PizzaTopping;

/**
 * Event that fires when a menu panel requests a change to the pizza object
 * @author Justin
 *
 */
public class PizzaChangingEvent extends ActionEvent
{
	//REGION public constants
	public static final byte SIZE_CHANGING = 0;
	public static final byte CHEESE_CHANGING = 1;
	public static final byte SAUCE_CHANGING = 2;
	public static final byte BAKE_CHANGING = 3;
	public static final byte TOPPINGS_ADDING = 4;
	public static final byte TOPPINGS_REMOVING = 5;
	public static final byte TOPPINGS_CHANGING = 6;
	
	public static final byte SMALL_PIZZA = 7;
	public static final byte LARGE = 8;
	public static final byte XLARGE = 9;
	
	public static final byte NO_CHEESE = 10;
	public static final byte LIGHT_CHEESE = 11;
	public static final byte NORMAL_CHEESE = 12;
	
	public static final byte NO_SAUCE = 13;
	public static final byte LIGHT_SAUCE = 14;
	public static final byte NORMAL_SAUCE = 15;
	public static final byte EXTRA_SAUCE = 16;
	
	public static final byte LIGHT_BAKE = 17; 
	public static final byte NORMAL_BAKE = 18;
	public static final byte WELL_DONE_BAKE = 19;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2981653705370447883L;
	private byte changingProperty;
	private byte changeRequested;
	private PizzaTopping changingTopping;
	
	/**
	 * Creates a new instance of the PizzaChangingEvent - use when Base options are changing
	 * @param source the object that raised the event
	 * @param propertyChanging the property of the pizza that is changing - should be one of the predefined constants
	 * @param change the change requested - should be one of the predefined constants
	 * @param cmd the ActionCommand associated with this instance
	 */
	public PizzaChangingEvent(Object source, byte propertyChanging, byte change, String cmd)
	{
		super(source, ActionEvent.ACTION_PERFORMED, cmd);
		if (propertyChanging > TOPPINGS_CHANGING || propertyChanging < SIZE_CHANGING)
		{
			throw new IllegalArgumentException("PropertyChanging must be one of the following; SIZE_CHANGING, CHEESE_CHANGING, SAUCE_CHANGING, BAKE_CHANGING");
		}
		else if (change < SMALL_PIZZA || change > WELL_DONE_BAKE)
		{
			throw new IllegalArgumentException("PropertyChanging must be one of the following; SMALL_PIZZA, MEDIUM_PIZZA, LARGE_PIZZA, NO_CHEESE, " + 
		"LIGHT_CHEESE, NORMAL_CHEESE, NO_SAUCE, LIGHT_SAUCE, NORMAL_SAUCE, EXTRA_SAUCE, LIGHT_BAKE, NORMAL_BAKE, WELL_DONE_BAKE");
		}
		else if (propertyChanging == SIZE_CHANGING && (change < SMALL_PIZZA || change > XLARGE))
		{
			throw new IllegalArgumentException("change in the size property requires a coresponding change parameter");
		}
		else if (propertyChanging == CHEESE_CHANGING && (change < NO_CHEESE || change > NORMAL_CHEESE))
		{
			throw new IllegalArgumentException("change in the cheese property requires a coresponding change parameter");
		}
		else if (propertyChanging == SAUCE_CHANGING && (change < NO_SAUCE || change > EXTRA_SAUCE))
		{
			throw new IllegalArgumentException("change in the size property requires a coresponding change parameter");
		}
		else if (propertyChanging == BAKE_CHANGING && (change < LIGHT_BAKE || change > WELL_DONE_BAKE))
		{
			throw new IllegalArgumentException("change in the bake property requires a coresponding change parameter");
		}
		else
		{
			this.changingProperty = propertyChanging;
			this.changeRequested = change;
		}
		
	}
	
	/**
	 * Creates a new instance of the object - Use when topping is changing
	 * @param source the source of the event
	 * @param topping the PizzaTopping to be changed
	 * @param change the PizzaChangingEvent change constant to be made
	 * @param cmd the actioncommand
	 */
	public PizzaChangingEvent(Object source, PizzaTopping topping, byte change, String cmd)
	{
	
		super(source, ActionEvent.ACTION_PERFORMED, cmd);
		
		if (change < TOPPINGS_ADDING || change > TOPPINGS_CHANGING)
			throw new IllegalArgumentException("change in Topping requirs a corresponding change parameter");
		
		changingProperty = change;
		changeRequested = change;
		
		this.changingTopping = topping;
		
	}
	
	/**
	 * gets the property of the pizza that is being changed
	 * @return byte
	 */
	public byte getChangingProperty()
	{
		return this.changingProperty;
	}

	/**
	 * gets the change that is requested for the property being modified
	 * @return byte
	 */
	public byte getRequestedChange()
	{
		return this.changeRequested;
	}
	
	public PizzaTopping getChangingTopping()
	{
		return this.changingTopping;
	}
}
