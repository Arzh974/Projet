package main;

public class ProdFile extends Thread {
	
	File file;
	int sleep;
	String name;
	int last;
	boolean pause;
	
	public ProdFile(File file, int sleep,String name) {
		this.file=file;
		this.sleep=sleep;
		this.name=name;
	}
	
	public void run() {
		
		while(!isInterrupted()) {
			if(this.pause) {
				try {
					synchronized(this) {
						wait();
						
					}
				}catch(InterruptedException e) {
					
				}
			}
			last= file.add();	
			try {
				sleep(sleep);
			}catch(InterruptedException e) {
				this.interrupt();
			}
		}
	}
	
	public String getNom() {
		return this.name;
	}
	
	public void pause() {
		pause=!pause;
	}
	
	public int getLast() {
		return this.last;
	}
	
}
