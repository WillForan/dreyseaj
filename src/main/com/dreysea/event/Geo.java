package com.dreysea.event;
// lat, lon, and dist function
public class Geo {
  public double lat;
  public double lon;
  //Haversine
  public double dist(Geo other){
    double R = 6372.8; //km
    double dlat = Math.toRadians(other.lat - this.lat);
    double dlon = Math.toRadians(other.lon - this.lon);
    double a =  Math.sin(dlat/2)*Math.sin(dlat/2) +
                Math.sin(dlon/2)*Math.sin(dlon/2)*
                Math.cos(this.lat)*Math.cos(other.lat);
    double c = 2  * Math.asin(Math.sqrt(a)); 
    return R * c;
  }

  public Geo(double lat, double lon){
   this.lat=lat; this.lon=lon;
  }

  public boolean equals (Geo other) {
   return lat == other.lat && lon==other.lon;
  }

}

