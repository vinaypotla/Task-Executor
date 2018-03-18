package project1trial1;

import java.util.NoSuchElementException;

public class TaskRunner implements Runnable {
	
	BlockingFIFO boundedblockingqueue;
	
	public TaskRunner(BlockingFIFO boundedblockingqueue) {
		this.boundedblockingqueue = boundedblockingqueue;
		
	}


	public void run() {
		Task result = null;
		
		while(true){
			try {
				result = boundedblockingqueue.remove();
				} catch (NoSuchElementException e1) {
					System.out.println("No such element Exception: " + e1.getMessage());
					e1.printStackTrace();
					}
			try {
				result.execute();
				Thread.yield();
				} catch (RuntimeException e) {
					System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
					
				}
			}
		}
	}