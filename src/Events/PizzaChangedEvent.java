package Events;
import java.awt.event.ActionEvent;

public class PizzaChangedEvent extends ActionEvent
{
	private boolean hasPriceChanged;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1253417584812781886L;

	public PizzaChangedEvent(Object source, String cmd, boolean priceChanged)
	{
		super(source, ActionEvent.ACTION_PERFORMED, cmd);
		
		hasPriceChanged = priceChanged;
	}
	
	public boolean hasPriceChanged()
	{
		return hasPriceChanged;
	}

}
