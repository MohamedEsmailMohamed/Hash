package lab03;

import java.util.Random;

public class UniversalHash implements HashFunction{
	final private int U_BOUND = 32;
	int binaryHTSize;
	int [][] hashArr;
	
	public UniversalHash(int tableSize){
		this.binaryHTSize= Integer.toBinaryString(tableSize).length()-1;
		this.hashArr = new int[binaryHTSize][U_BOUND];
		
		
		createHashMatrix();
	}
	private UniversalHash(){
		
	}
	private  void createHashMatrix(){
		Random randGenerator = new Random();
		for(int i = 0; i < binaryHTSize; i++){
			for(int j = 0; j < U_BOUND; j++){
				this.hashArr[i][j]= randGenerator.nextInt(2);
			}
		}
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
		int binary = 0;
		for(int i = 0; i < binaryHTSize; i++){
				binary = 0;
				for(int j = 0; j < U_BOUND; j++){
					if(binaryOfKey.charAt(j)== '1'){
						binary += hashArr[i][j] ;
					}
				}
				binary = binary % 2;
				hashValueInBinary += String.valueOf(binary);
				
				
		}
		int num;
		if(binary != 0){
			num = Integer.parseInt(hashValueInBinary, 2);
		}else{
			num = 0;
		}
		return num;
	}
}
