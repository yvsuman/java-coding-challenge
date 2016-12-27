package exercise;

import java.util.Arrays;

public class LoadHandler {
	
	private static final int MAX_PRICE_UPDATES = 100;
	private final Consumer consumer;
	
	public LoadHandler (Consumer consumer) {
		this.consumer = consumer;
	}
	
	public void receive(PriceUpdate priceUpdate) {
		
		consumer.send (Arrays.asList(priceUpdate));
	}
	
}
