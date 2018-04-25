package lastVersion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		
			//FileReader in = new FileReader(
				//	"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys1001000.txt");
		
		
			//FileReader in = new FileReader(
			//		"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys10001000.txt");
		
		
			//FileReader in = new FileReader(
			//		"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys100001000000.txt");
		
		
			FileReader in = new FileReader(
					"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys10000001000000.txt");
		
		
			//FileReader in = new FileReader(
			//		"C:\\Users\\ABDO\\Desktop\\2nd S 2nd Y\\tc3\\testCases_lab4\\keys10000001000000.txt");
			
			
			
			//FileReader in = new FileReader(
			//		"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys.txt");
		    
			BufferedReader br = new BufferedReader(in);
		    
		    String s = br.readLine();
		    in.close();
		    String [] numbers = s.split(",");
		    int[] arrSet = new int[numbers.length];
		    
		    for(int i = 0; i < numbers.length; i++){
		    	arrSet[i] = Integer.parseInt(numbers[i]);
		    }
		  
		   
		    
		    PerfectHashing p2 = new PerfectHashN2();
			p2.setUniverseOfKeys(arrSet);
			try{
				System.out.println(p2.buildTable());
			}catch(Exception e)
			{
				throw new RuntimeException(e);
			}
			
			boolean keysFounded = true;
			for(int i = 0; i < arrSet.length; i++){
				keysFounded = keysFounded && p2.get(arrSet[i]);
			}
			System.out.println(keysFounded);
			
			
		    PerfectHashing p = new PerfectHash();
			p.setUniverseOfKeys(arrSet);
			try{
				System.out.println(p.buildTable());
			}catch(Exception e)
			{
				throw new RuntimeException(e);
			}
			
			keysFounded = true;
			for(int i = 0; i < arrSet.length; i++){
				keysFounded = keysFounded && p.get(arrSet[i]);
			}
			System.out.println(keysFounded);
	}

}
