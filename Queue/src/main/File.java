package main;

import java.util.*;

public class File{
	
	List<Integer> liste;
	int maxSize;
	
	public File() {
		this.liste= new LinkedList<Integer>();
		this.maxSize=20;
	}
	
	public synchronized int add() {
		
		int x= (int)Math.round(Math.random()*(100-1)+1);
		
		while(liste.size()==maxSize) {
			try {
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.liste.add(x);
		String file=affiche();
		System.out.println("Add"+file);
		notifyAll();
		return x;
	}
	
	public synchronized int remove() {
		
		while(liste.isEmpty()) {
			try {
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();

			}
		}
		int rm = liste.get(0);
		this.liste.remove(0);
		if(!liste.isEmpty()) {
			String file= affiche();
			System.out.println("Rm : "+file);
		}
		else {
			System.out.println("Rm : []");
		}
		
		notifyAll();
		return rm;
	}
	
	public synchronized String affiche() {
		if(liste.isEmpty()) {
			return "[]";
		}
		else if(liste.size()==1) {
			String affiche= "["+liste.get(0)+"]";
			return affiche;
		}
		else {
			String affiche= "[";
			
			for(int i=0;i<liste.size()-1;i++) {
				affiche+=liste.get(i)+",";
			}
			affiche+=liste.get(liste.size()-1)+"]";
			return affiche;
		}
	}
	
}
