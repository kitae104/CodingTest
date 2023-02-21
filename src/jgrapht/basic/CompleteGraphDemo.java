package jgrapht.basic;

import java.util.function.Supplier;

public class CompleteGraphDemo {

	private static final int SIZE = 10;
	
	public static void main(String[] args) {
		Supplier<String> vSupplier = new Supplier<String>() {
			private int id = 0; 
			@Override
			public String get() {
				return "v" + id++;
			}
			
		};
	}
}
