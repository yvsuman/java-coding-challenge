
# Overview

  Producer - Consumer Load Handler Case Study

### Documentation

  - [Description](#description)


## Description

   #### Producer : Who updates price of stocks, generate constant price updates for a fix number of stocks. 
              Where every price update has to be consumed by Consumer with out any block of updates   
 
   #### Load Handler: Consumes the price updates produced by the producer, which intern passes updates to consumer.
                 LoadHandler is implemened by a thread which will start a scheduler to limit the updates as bulk once per second.
   
   #### Consumer: As per the legacy system consumer has more updates of price per second.
             with the current implementation, blocking queue which always takes the latest updates and added into queue.
             And Queue of updates which will be processed  into smaller chunk of updates with out any duplicates.