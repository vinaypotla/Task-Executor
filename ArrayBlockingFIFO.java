package project1trial1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingFIFO {
	
	int arrayLength;
	BlockingQueue<Task> queue;
	
	public ArrayBlockingFIFO(int arrayLength){
		this.arrayLength = arrayLength;
		queue = new ArrayBlockingQueue<Task>(arrayLength);
		}
	
	public void add(Task task){
		try {
			queue.put(task);
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception: " + e.getMessage());
				e.printStackTrace();
				}
		}
	
	public Task remove(){
		Task task = null;
		
		try {
			task = queue.take();
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception: " + e.getMessage());
				e.printStackTrace();
				}
		return task;
		}
	
}
