package com.dreysea.event;

// start and stop time
// and updater
class ETime {
 long start;
 long finish;

 // 
 public boolean tooLong(long timestamp) {
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

 public boolean equals (ETime other) {
   return start == other.start && finish==other.finish;
 }
}

