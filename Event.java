// settings 
class Consts {
  public static final double MAXTIME    = 2*60*60; //2 hours
  public static final long   MAXDIST    = 5;       //km
  public static final int    MINSAMPLES = 2;
  private Consts(){
      //this prevents even the native class from 
      //calling this ctor as well :
       throw new AssertionError();
  }
}

// lat, lon, and dist function
class Geo {
  public double lat;
  public double lon;
  //Haversine
  public double dist(Geo other){
    double R = 6372.8; //km
    double dlat = Math.toRadians(other.lat - this.lat);
    double dlon = Math.toRadians(other.lon - this.lon);
    double a = Math.sin(dlat/2)*Math.sin(dlat/2) + 
                Math.sin(dlon/2)*Math.sin(dlon/2)*
                Math.cos(this.lat)*Math.cos(other.lat);
    double c = 2  * Math.asin(Math.sqrt(a)); 
    return R * c;
  }

  public Geo(double lat, double lon){
   this.lat=lat; this.lon=lon;
  }

}

class EGeo {
 public Geo start;
 public Geo finish;
 // TODO: mean loc
 // vector?

 public double totdist()  {
  return start.dist(finish);
 }

 public void update(Geo newsample){
  this.finish=newsample;
 }

 // is this position too far
 public Boolean tooFar(Geo gloc) {
   return finish.dist(gloc) > Consts.MAXDIST;
 }

 // constructors
 EGeo(Geo start, Geo finish) {
  this.start=start;
  this.finish=finish;
 }
 EGeo(Geo firstandonly){
  this.start =firstandonly;
  this.finish=firstandonly;
 }

}
// start and stop time
// and updater
class ETime {
 long start;
 long finish;

 // 
 public Boolean tooLong(long timestamp) {
  return timestamp - this.finish > Consts.MAXTIME ;
 }

 //intializer
 public ETime(long timestamp){
  this.start=timestamp;
  this.finish=timestamp;
 }

 // update time
 public void update(long timestamp){
  this.finish=timestamp;
 }
}


// an event has a time and location for start and finish
class Event {

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
