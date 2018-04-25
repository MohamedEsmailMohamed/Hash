package lastVersion;

public class PerfectHashN2 implements PerfectHashing{

	int [] setArr;
	int hashTableSize;
	int [] hashTable;	
	HashFunction hashFun;
	
	@Override
	public HashFunction getuniversalHashFunctions() {
		// TODO Auto-generated method stub
		return hashFun;
	}

	@Override
	public void setUniverseOfKeys(int[] setOfKeys) {
		setArr = setOfKeys;
		if(setArr.length < (int)Math.round(Math.pow(Integer.MAX_VALUE,0.5))){
			hashTableSize =setArr.length * setArr.length;
			int binaryHTSize = Integer.toBinaryString(hashTableSize).length()-1;
			hashTableSize = (int)Math.round(Math.pow(2, binaryHTSize));
		}else{
			hashTableSize = 20000000;
			int binaryHTSize = Integer.toBinaryString(hashTableSize).length()-1;
			hashTableSize = (int)Math.round(Math.pow(2, binaryHTSize));
		}			
	}

	@Override
	public boolean get(int key) {
		
		return hashTable[hashFun.hashKey(key)] == key;
	}

	@Override
	public int buildTable() throws Exception {
		int numberOfCollision = 0;
		while(!rebuild()){
			numberOfCollision++;
		}		
		return numberOfCollision;	
	}
	private boolean rebuild(){
		hashFun = new UniversalHash(hashTableSize);
		hashTable = new int[hashTableSize];
		for(int i = 0; i < hashTableSize; i++){
			hashTable[i] = -1;
		}		
		for(int i = 0; i < setArr.length; i++){
			int index = hashFun.hashKey(setArr[i]);	
			if(hashTable[index] == -1 || setArr[i] == hashTable[index]){
				hashTable[index] = setArr[i];
			}else {
				return false;
			}
		}
		return true;		
	}

}

