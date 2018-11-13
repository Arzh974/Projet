package main;
import java.util.*;

import main.*;


public class Main {

	public static void main(String[] args) {
		
		LinkedList<Integer> l= new LinkedList<Integer>();
		File file= new File();
		ConsoFile consommateur= new ConsoFile(file,1100,"Cons_1");
		ProdFile producteur= new ProdFile(file,1000,"Prod_1");
		
		consommateur.setDaemon(true);
		producteur.setDaemon(true);
		
		consommateur.start();
		producteur.start();
		
		Scanner sc= new Scanner(System.in);
		sc.nextLine();
	
	}	
	
}
