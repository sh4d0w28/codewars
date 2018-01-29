package kuy_6;

public class Abbreviator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(fun1());
		
		if (true) return;
		
		// TODO Auto-generated method stub
		String source = "cat'balloon-mat'a: sits";
		
		String[] parts = source.split("[\\W]");
		
		for(int i = 0; i< parts.length; i++){
			if ( parts[i].length() >= 4 ) {
				String part = parts[i];
				part = part.substring(0,1) + (part.length()-2) + part.substring(part.length()-1);
				source = source.replaceFirst(parts[i], part);
			}
		}
		System.out.println(source);		
		System.out.println("c[at'b5n-mat'a]: s2s");
				
	}
	
	public static int fun1(){
		long num1 = 666789L;
		long num2 = 12345667L;
		
		if (
				((Long)num1).toString().matches(".*(000|111|222|333|444|555|666|777|888|999).*") && 
				((Long)num2).toString().matches(".*(00|11|22|33|44|55|66|77|88|99).*")
				) return 1;
		return 0;
		
	}

}
