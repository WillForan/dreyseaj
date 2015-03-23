package com.dreysea.event;


// an event has a time and location for start and finish
public class Event {

  int nsamples;
  ETime time;
  EGeo place;
  
  // replace an event with a new one
  public Event( Event a) {
    this.place = a.place;
    this.time = a.time;
  }


  // create an event with lat lon and timestamp
  public Event( double lat, double lon, long timestamp) {
    time  = new ETime(timestamp);
    place = new EGeo (new Geo(lat,lon) );
  }
  
  private void resetEvent(Geo gloc,long timestamp){
    Event e = new Event(gloc,timestamp);
    this.time=e.time;
    this.place=e.place;
  }

  // create an event with a geo and timestamp
  public Event(Geo gloc, long timestamp) {
    time = new ETime(timestamp);
    place= new EGeo(gloc);
  }

  // new sample, what do we do?
  //  - add to this event
  //  - post this event, start a new one
  public void newSample(Geo gloc, long timestamp) {
    
    // are we too far (time, dist) from last sample?
    if(  place.tooFar(gloc) || time.tooLong(timestamp)){
       
       // event we were tracking is garbage
       if( nsamples > Consts.MINSAMPLES ) {
           this.postEvent(); 
           // maybe we want to set an iterator
           // to self b/c we are about to overwrite the event
           // ...do this in post event
       }

       // reset this event to the current sample
       resetEvent(gloc,timestamp);

    // update current event
    } else {
       addSample(gloc,timestamp);
    }

  }

  private void addSample(Geo gloc, long timestamp) {
    nsamples+=1;
    place.update(gloc);
    time.update(timestamp);
  }

  // read last event
  public void readEvent(String[] path){
  }

  // post event
  private void postEvent() {
    //post event
  }



}
