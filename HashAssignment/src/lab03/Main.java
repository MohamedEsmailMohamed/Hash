package lab03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main implements IHash{
	int [] setArr;
	final private int U_BOUND = 32;
	int binaryHTSize;
	int hashTableSize;
	int [][] hashArr;
	int [] hashTable;
	public Main(int [] setArr){
		this.setArr = setArr;
		if(setArr.length < (int)Math.round(Math.pow(Integer.MAX_VALUE,0.5))){
			hashTableSize =setArr.length * setArr.length;
			System.out.println(hashTableSize);
			String b =  Integer.toBinaryString(hashTableSize);
			this.binaryHTSize = b.length();
			hashTableSize = (int)Math.round(Math.pow(2, binaryHTSize));
			System.out.println(binaryHTSize);
			this.hashArr = new int[binaryHTSize][U_BOUND];
		}else{
			hashTableSize = 20000000;
			System.out.println(hashTableSize);
			String b =  Integer.toBinaryString(hashTableSize);
			this.binaryHTSize = b.length();
			hashTableSize = (int)Math.round(Math.pow(2, binaryHTSize));
			System.out.println(binaryHTSize);
			this.hashArr = new int[binaryHTSize][U_BOUND];
		}
		
	}
	@Override
	public  void createHashMatrix(){
		Random randGenerator = new Random();
		//System.out.println(binaryHTSize);
		for(int i = 0; i < binaryHTSize; i++){
			for(int j = 0; j < U_BOUND; j++){
				this.hashArr[i][j]= randGenerator.nextInt(2);
				//System.out.print(this.hashArr[i][j]);
			}
			//System.out.println();
		}
	}
	@Override
	public boolean creatHashTable(){
		//int m =(int) Math.round(Math.pow(setArr.length, 2));
		//String b =  Integer.toBinaryString(m);
		createHashMatrix();
		hashTable = new int[hashTableSize];
		for(int i = 0; i < hashTableSize; i++){
			hashTable[i] = -1;
		}
		for(int i = 0; i < setArr.length; i++){
			int index = hashKey(setArr[i]);	
			if(hashTable[index] == -1 || setArr[i] == hashTable[index]){
				hashTable[index] = setArr[i];
			}else {
				//System.out.println("e");
				return false;
			}
		}
		return true;
	}
	@Override
	public int hashKey(int key) {
		String hashValueInBinary = new String();
		
		String binaryOfKey = Integer.toBinaryString(key);
		int diff = U_BOUND - binaryOfKey.length();
		String temp = new String();
		for(int i = 0; i < diff; i++){
			temp += "0";
		}
		temp += binaryOfKey;
		binaryOfKey = temp;
		for(int i = 0; i < binaryHTSize; i++){
			//if(binaryOfKey.charAt(i)== '1'){
				int binary = 0;
				for(int j = 0; j < U_BOUND; j++){
					if(binaryOfKey.charAt(j)== '1'){
						binary += hashArr[i][j] ;
					}
				}
				//System.out.println(binary);
				binary = binary % 2;
				hashValueInBinary += String.valueOf(binary);
				
				//System.out.println(hashValueInBinary);
			//}			
		}
		int num = Integer.parseInt(hashValueInBinary, 2);
		//System.out.println(num + " " + key);
		return num;
	}
	@Override
	public int setHashN2(){
		int numberOfCollision = 0;
		while(!creatHashTable()){
			numberOfCollision++;
			//System.out.println(numberOfCollision);
		}		
		return numberOfCollision;		
	}
	@Override
	public int search(int key){
		return hashTable[hashKey(key)];
	}
	
	@Override
	public int setHashN1(){
		
		return 0;
	}



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//FileReader in = new FileReader(
			//	"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys1001000.txt");
		//FileReader in = new FileReader(
		//		"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys10001000.txt");
		//FileReader in = new FileReader(
			//	"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys100001000000.txt");
		FileReader in = new FileReader(
				"D:\\collage\\2nd year\\2nd Semester\\5 Data Structure II\\2_Labs\\testCases_lab3\\keys10000001000000.txt");
	    BufferedReader br = new BufferedReader(in);
	    
	    String s = br.readLine();
	    in.close();
	    String [] numbers = s.split(",");
	    int[] arrSet = new int[numbers.length];
	    
	    for(int i = 0; i < numbers.length; i++){
	    	arrSet[i] = Integer.parseInt(numbers[i]);
	    	//System.out.println(arrSet[i]);
	    }
	    System.out.println(arrSet.length);
	    Main x = new Main(arrSet);
	    
	    System.out.println(x.setHashN2());
	    System.out.println(x.search(475860));
	}
	
	

}
