# Case Study: Java Engineer

## Provided

Together with this document we will provide a Exercise.zip file containing an eclipse project. This project is the basis for the task.

## Expectation

Implement the task as described below. You can start whenever you like and send us the result once you are done.
Add documentation like you would in a real project. Bring the code to a production standard (not more, not less).
Do not spend more than 2 hours on this exercise.

## Return

After you have finished the task, send the code back to the provided e-mail. Please tell us how much time you have spent on the task. Also provide any external library that you have used.

## Scenario

There is a producer (Producer) of price updates for stocks.
This producer will generate constant price updates for a fix number of stocks.
The producer should not block, every price update should be consumed as quickly as possible.
Furthermore there is a load handler (LoadHandler) which will consume the price updates of the producer.
In the current implementation the load handler is just passing on the update to a consumer. This should be changed.
The consumer (Consumer) will receive the price updates from the load handler.
(The current implementation will just print out all price updates for convenience sake.)
The consumer is representing a legacy system that cannot consumer more than a certain number of price updates per second. Otherwise it would fall over.

## Task

The task of this exercise is to extend the LoadHandler to limit the updates per second to the consumer to a certain given number (MAX_PRICE_UPDATES).
In order to achieve this, it is allowed to drop price updates, since otherwise the application will run out of memory, if the application will keep all of them.
It is important that, if a price update will be send to the consumer, it has to be the most recent price.
