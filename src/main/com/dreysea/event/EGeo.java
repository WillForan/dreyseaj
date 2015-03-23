package com.dreysea.event;

public class EGeo {
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
 public boolean tooFar(Geo gloc) {
   return finish.dist(gloc) > Consts.MAXDIST;
 }

 // constructors
 public EGeo(Geo start, Geo finish) {
  this.start=start;
  this.finish=finish;
 }
 public EGeo(Geo firstandonly){
  this.start =firstandonly;
  this.finish=firstandonly;
 }

 public boolean equals (EGeo other) {
   return start == other.start && finish==other.finish;
 }
}
