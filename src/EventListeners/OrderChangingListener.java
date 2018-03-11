package EventListeners;
import java.util.function.Consumer;
import EventListeners.Interfaces.IOrderChangingListener;
import Events.OrderChangingEvent;

public class OrderChangingListener implements IOrderChangingListener
{

	private Consumer<OrderChangingEvent> method;
	
	public OrderChangingListener(Consumer<OrderChangingEvent> method)
	{
		if (method == null)
			throw new NullPointerException("c");
		
		this.method = method;
	}
	
	@Override
	public void orderChangingActionPerformed(OrderChangingEvent e)
	{
		method.accept(e);
	}

}
