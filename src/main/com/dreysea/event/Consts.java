package com.dreysea.event;
// settings 
public class Consts {
  public static final double MAXTIME    = 2*60*60; //2 hours
  public static final long   MAXDIST    = 5;       //km
  public static final int    MINSAMPLES = 2;
  private Consts(){
      //this prevents even the native class from 
      //calling this ctor as well :
       throw new AssertionError();
  }
}
