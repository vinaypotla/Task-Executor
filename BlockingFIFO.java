package project1trial1;

import java.util.LinkedList;
import java.util.Queue;


public class BlockingFIFO{
	 static Queue<Task> queue = new LinkedList<Task>();  
	 static int capacity;  
	 static int nextin = 0; 
	 static int nextout = 0;
	 static int count = 0;
	 static Object notfull = new Object();
	 static Object notempty = new Object(); // Monitors used for synchronization
	 
	 public BlockingFIFO(int capacity) {
		 if (capacity <= 0)  throw new IllegalArgumentException("The capacity of the queue must be > 0.");
	     this.capacity = capacity;  
	     }  
	
	 public void add(Task task) {
		 synchronized(notfull) {
			 while(count == capacity){
				 try {
					 notfull.wait();
					 } catch (InterruptedException e) {
						 System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
						 e.printStackTrace();
						 }   // Buffer is full, wait for take
				 }
			 }	
		 
		 synchronized(notempty) {
			 if (count < capacity){
				 queue.add(task);
				 nextin = (nextin + 1) % capacity;
				 count++;
				 notempty.notifyAll();
				 }
			 }
		 }
	 
	 public Task remove() {
		 Task result=null;
		 
		 synchronized(notempty){
			 while(count == 0){
				 try {
					 notempty.wait();
					 } catch (InterruptedException e) {
						 System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
						 e.printStackTrace();
						 }	// Buffer is empty
				 }
			 }
		 
		 synchronized(notfull) {
			 if (count>0){
				 result = queue.remove();
				 nextout = (nextout + 1) % capacity;
				 count--;
				 notfull.notifyAll();
				 }
			 
			 return result;
			 }
		 }	
	 }