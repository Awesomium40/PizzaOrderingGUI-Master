package EventListeners;
import java.util.function.Consumer;

import EventListeners.Interfaces.IPizzaChangingListener;
import Events.OrderChangingEvent;
import Events.PizzaChangingEvent;

public class PizzaChangingListener implements IPizzaChangingListener
{

	private Consumer<PizzaChangingEvent> method;
	
	public PizzaChangingListener(Consumer<PizzaChangingEvent> method)
	{
		if (method == null)
			throw new NullPointerException("method");
		this.method = method;
	}
	
	@Override
	public void pizzaChangingActionPerformed(PizzaChangingEvent e)
	{
		method.accept(e);
	}

}
