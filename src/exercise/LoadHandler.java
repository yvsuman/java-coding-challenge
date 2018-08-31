package exercise;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LoadHandler extends Thread {

	private static final int MAX_PRICE_UPDATES = 100;
	private final Consumer consumer;
	private static final int TIMER = 1000;
	private static boolean UPDATES_COMPLETED = false;

	private static BlockingQueue<PriceUpdate> stockUpdateQueue;

	public LoadHandler(Consumer consumer) {
		this.consumer = consumer;
		stockUpdateQueue = new LinkedBlockingQueue<>();
		start();
	}

	@Override
	public void run() {
		try {
			updatesToConsumer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method to receive the producer updates
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void receive(PriceUpdate priceUpdate) throws InterruptedException {
		stockUpdateQueue.offer(priceUpdate, 1, SECONDS);
	}

	/**
	 * List of price updates queue
	 */
	public static BlockingQueue<PriceUpdate> getUpdateQueue() {
		return stockUpdateQueue;
	}

	/**
	 * This method is for consumer updates
	 * 
	 * **/
	public void updatesToConsumer() throws InterruptedException {
		List<PriceUpdate> priceUpdate = new ArrayList<PriceUpdate>(100);
		while (!UPDATES_COMPLETED || !getUpdateQueue().isEmpty()) {
			priceUpdate.addAll(LoadHandler.getUpdateQueue());
			LoadHandler.getUpdateQueue().clear();
			List<PriceUpdate> sortedList = getLatestUpdates(priceUpdate);
			if (sortedList.size() > MAX_PRICE_UPDATES)
				sortedList.subList(0, MAX_PRICE_UPDATES - 1);
			if (!sortedList.isEmpty()) {
				consumer.send(sortedList);
			}
			priceUpdate.clear();
			sortedList.clear();
			Thread.sleep(TIMER);
		}
	}

	/**
	 * This method for stock updates
	 * Return List of price updates with sort and removes duplicate values
	 */
	private List<PriceUpdate> getLatestUpdates(List<PriceUpdate> priceUpdates) {
		List<PriceUpdate> sortedList = new ArrayList<PriceUpdate>();
		List<String> stockes = new ArrayList<String>();
		for (PriceUpdate price : priceUpdates) {
			stockes.add(price.getCompanyName());
		}
		Set<String> uniqueSet = new HashSet<String>(stockes);
		PriceUpdate priceObject = null;
		int i = 0;
		int j;
		for (String name : uniqueSet) {
			priceObject = null;
			j = i++;
			Iterator<PriceUpdate> itr = priceUpdates.iterator();
			while (itr.hasNext()) {
				PriceUpdate pu = (PriceUpdate) itr.next();
				if (pu.getCompanyName().equals(name)) {
					if (priceObject == null) {
						sortedList.add(j, pu);
						priceObject = pu;
					} else {
						if (pu.equals(priceObject)) {
							sortedList.remove(j);
							sortedList.add(j, pu);
							priceObject = pu;

						}
					}

				}
			}
		}
		return sortedList;
	}

}
