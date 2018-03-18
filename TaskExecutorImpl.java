package project1trial1;

public class TaskExecutorImpl 
{	
	static int bufferLength = 100;
	static int numThreads;
	
	//final ArrayBlockingFIFO boundedblockingqueue = new ArrayBlockingFIFO(bufferLength);
	final BlockingFIFO boundedblockingqueue = new BlockingFIFO(bufferLength);	
	
	public TaskExecutorImpl(int nThreads) {
		this.numThreads = nThreads;
						
		for (int i = 0; i < numThreads; i++) {
			Thread t = new Thread(new TaskRunner(boundedblockingqueue));
			t.start();
			}
		}	
	
	public void addTask(Task task) {	
		try {
			boundedblockingqueue.add(task);
			} catch (RuntimeException e) {
				System.out.println("Runtime Exception: " + e.getMessage());
				e.printStackTrace();
				}
		}	
	}
