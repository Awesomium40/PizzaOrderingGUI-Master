package EventRaisers;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import EventListeners.Interfaces.IPizzaChangingListener;
import EventRaisers.Interfaces.INotifyPizzaChanging;
import Events.PizzaChangingEvent;

public class NotifyPizzaChangingBase extends JPanel implements INotifyPizzaChanging
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -818729391359143245L;
	
	protected ArrayList<IPizzaChangingListener> subscribers;
	
	public NotifyPizzaChangingBase(LayoutManager l)
	{
		super(l);
		subscribers = new ArrayList<IPizzaChangingListener>();
	}

	public NotifyPizzaChangingBase()
	{
		super();
		subscribers = new ArrayList<IPizzaChangingListener>();
	}

	@Override
	public void addPizzaChangingListener(IPizzaChangingListener l)
	{
		synchronized(this.subscribers)
		{
			if (!subscribers.contains(l))
				subscribers.add(l);
		}
	}

	@Override
	public void removePizzaChangingListener(IPizzaChangingListener l)
	{
		synchronized(this.subscribers)
		{
			this.subscribers.remove(l);
		}
	}
	
	protected void raisePizzaChangingEvent(PizzaChangingEvent e)
	{
		synchronized(this.subscribers)
		{
			subscribers.forEach(s -> s.pizzaChangingActionPerformed(e));
		}
			
	}

}
