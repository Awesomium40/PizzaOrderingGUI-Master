package Components;
import javax.swing.*;
import Components.OptionsMenu.BasicOptionsMenu;
import Enums.CheeseInstruction;
import Enums.CookingInstruction;
import Enums.PizzaSizes;
import Enums.SauceInstruction;
import EventListeners.PizzaChangingListener;
import EventListeners.OrderChangingListener;
import EventListeners.PizzaChangedListener;
import Events.OrderChangingEvent;
import Events.PizzaChangedEvent;
import Events.PizzaChangingEvent;
import java.awt.*;
import DataModel.*;

public class OrderingWindow extends JFrame
{
	private ToppingsMenu tm;
	private BasicOptionsMenu bm;
	private OrderControlsPanel controlPanel;
	private PizzaChangingListener pizzaChangingListener;
	private PizzaChangedListener pizzaChangedListener;
	private OrderChangingListener orderChangingListener;
	private Order order;
	private Pizza pizza;
	private final String WINDOW_TITLE = "Quickman's Pizza: Guranteed Delivery in 30 minutes or you get a boomerang to the head";

	/**
	 * 
	 */
	private static final long serialVersionUID = 4441490244966397306L;

	
	public OrderingWindow()
	{
		order = new Order();
		pizza = new Pizza();
		
		pizzaChangingListener = new PizzaChangingListener(this::onPizzaChanging);
		pizzaChangedListener = new PizzaChangedListener(this::onPizzaChanged);
		this.orderChangingListener = new OrderChangingListener(this::onOrderChanging);
		pizza.addPizzaChangedListener(pizzaChangedListener);
		
		
		tm = new ToppingsMenu();
		bm = new BasicOptionsMenu();
		controlPanel = new OrderControlsPanel();
		
		bm.addPizzaChangingListener(pizzaChangingListener);
		tm.addPizzaChangingListener(pizzaChangingListener);
		controlPanel.addNotifyOrderChangingListener(orderChangingListener);
		
		this.setLayout(new BorderLayout());
		this.add(tm, BorderLayout.WEST);
		this.add(bm, BorderLayout.NORTH);
		this.add(controlPanel, BorderLayout.SOUTH);
		this.setTitle(WINDOW_TITLE);
		
		this.pack();
		//this.setMinimumSize(this.getSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		onPizzaChanged(new PizzaChangedEvent(this, "create", true));

	}

	public static void main(String[] args)
	{
		new OrderingWindow();
	}
	
	private void onPizzaChanging(PizzaChangingEvent e)
	{
		byte change = e.getRequestedChange();
		switch (e.getChangingProperty())
		{
			case PizzaChangingEvent.BAKE_CHANGING:
				pizza.setBakeInstruction(change == PizzaChangingEvent.LIGHT_BAKE ? CookingInstruction.LIGHT_BAKE : 
										 change == PizzaChangingEvent.NORMAL_BAKE ? CookingInstruction.NORMAL : 
											 CookingInstruction.WELL_DONE);
				break;
			case PizzaChangingEvent.CHEESE_CHANGING:
				pizza.setCheeseInstruction(change == PizzaChangingEvent.NO_CHEESE ? CheeseInstruction.NO_CHEESE : 
										   change == PizzaChangingEvent.LIGHT_CHEESE ? CheeseInstruction.LIGHT_CHEESE : 
										   CheeseInstruction.NORMAL);
				break;
			case PizzaChangingEvent.SAUCE_CHANGING:
				pizza.setSauceInstruction(change == PizzaChangingEvent.NO_SAUCE ? SauceInstruction.NO_SAUCE : 
										  change == PizzaChangingEvent.LIGHT_SAUCE ? SauceInstruction.LIGHT_SAUCE : 
										  change == PizzaChangingEvent.NORMAL_SAUCE ? SauceInstruction.NORMAL : 
											  SauceInstruction.EXTRA_SAUCE);
				break;
			case PizzaChangingEvent.SIZE_CHANGING:
				pizza.setSize(change == PizzaChangingEvent.SMALL_PIZZA ? PizzaSizes.SMALL : 
							  change == PizzaChangingEvent.LARGE ? PizzaSizes.LARGE : PizzaSizes.XLARGE);
				break;
			case PizzaChangingEvent.TOPPINGS_CHANGING:
			case PizzaChangingEvent.TOPPINGS_ADDING:
			case PizzaChangingEvent.TOPPINGS_REMOVING:
				pizza.changeTopping(e.getChangingTopping(), e.getRequestedChange());
		}
	}
	
	private void onOrderChanging(OrderChangingEvent e)
	{
		if (e.getAction() == OrderChangingEvent.ADDING)
			addPizzaToOrder();
		else if (e.getAction() == OrderChangingEvent.CANCEL)
			resetPizza();			
	}
	
	private void onPizzaChanged(PizzaChangedEvent e)
	{
		if (e.hasPriceChanged())
			this.controlPanel.updatePrice(this.pizza.getPrice());
	}

	private void resetPizza()
	{
		pizza = new Pizza();
		pizza.addPizzaChangedListener(pizzaChangedListener);
		onPizzaChanged(new PizzaChangedEvent(this, "new", true));
		resetControls();
	}

	private void addPizzaToOrder()
	{
		order.addPizza(pizza);	
		updateOrderQuantity();
		showOrder();
		resetPizza();
	}
	
	private void updateOrderQuantity()
	{
		this.controlPanel.updateQuantity(order.getQuantity());
	}
	
	private void resetControls()
	{
		tm.resetControls();
		bm.resetControls();
	}
	
	private void showOrder()
	{
		JOptionPane.showMessageDialog(this, order.toString(), "Your Order", JOptionPane.INFORMATION_MESSAGE);
	}

}
