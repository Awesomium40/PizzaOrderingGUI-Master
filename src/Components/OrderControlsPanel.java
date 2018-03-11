package Components;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.*;
import javax.swing.*;
import EventListeners.Interfaces.IOrderChangingListener;
import EventRaisers.Interfaces.INotifyOrderChanging;
import EventListeners.ButtonActionListener;
import Events.OrderChangingEvent;

public class OrderControlsPanel extends JPanel implements INotifyOrderChanging
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5358896287739991209L;
	private JLabel lbl_quantPrompt;
	private JLabel lbl_pricePrompt;
	private JTextField txt_quantity;
	private JTextField txt_price;
	private JButton btn_add;
	private JButton btn_cancel;
	private ButtonActionListener listener;
	private ArrayList<IOrderChangingListener> subscribers;
	private static NumberFormat dollar = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
	
	
	public OrderControlsPanel()
	{
		super(new GridLayout(1, 3));
		listener = new ButtonActionListener(this::onButtonClick);
		subscribers = new ArrayList<IOrderChangingListener>();
		
		JPanel pnl;
		
		lbl_quantPrompt = new JLabel("Quantity:");
		lbl_pricePrompt = new JLabel("Current Item Price:");
		
		btn_add = new JButton("Add to Order");
		btn_cancel = new JButton("Cancel");
		
		btn_add.addActionListener(listener);
		btn_cancel.addActionListener(listener);
		
		txt_price = new JTextField("$0.00", 8);
		txt_price.setEditable(false);
		txt_price.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		txt_quantity = new JTextField("0", 4);
		txt_quantity.setEditable(false);
		txt_quantity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));		
		pnl.add(lbl_quantPrompt);
		pnl.add(txt_quantity);	
		add(pnl);
		
		pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnl.add(lbl_pricePrompt);
		pnl.add(txt_price);
		add(pnl);
		
		pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnl.add(btn_add);
		pnl.add(btn_cancel);
		add(pnl);
		
	}
	
	private void onButtonClick(ActionEvent e)
	{
		Object source = e.getSource();
		raiseOrderChangingEvent(new OrderChangingEvent(this, 
				source == btn_add ? OrderChangingEvent.ADDING : OrderChangingEvent.CANCEL, e.getActionCommand()));
	}


	public void updatePrice(double price)
	{
		this.txt_price.setText(dollar.format(price));
	}
	
	public void updateQuantity(int quantity)
	{
		this.txt_quantity.setText(String.valueOf(quantity));
	}

	
	private void raiseOrderChangingEvent(OrderChangingEvent e)
	{
		synchronized(this.subscribers)
		{
			subscribers.forEach(s -> s.orderChangingActionPerformed(e));
		}
	}

	@Override
	public void addNotifyOrderChangingListener(IOrderChangingListener l)
	{
		synchronized(this.subscribers)
		{
			if(!subscribers.contains(l))
				subscribers.add(l);
		}
		
	}

	@Override
	public void removeNotifyOrderChangingListener(IOrderChangingListener l)
	{
		synchronized(this.subscribers)
		{
			subscribers.remove(l);
		}
	}

}
