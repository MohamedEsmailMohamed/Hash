package lab03;

import java.util.LinkedList;
import java.util.ListIterator;

public class PerfectHash implements PerfectHashing{
	private subTable []hashTable;
	private int []keys;
	private HashFunction universalHash;
	private int hashTableSize;
	public static final int EmptySlot = 0;
	
	
	
	
	public PerfectHash() {
		
	}

	

	@Override
	public void setUniverseOfKeys(int[] setOfKeys) {
		this.hashTableSize = setOfKeys.length;
		this.keys = setOfKeys;
		this.universalHash = new UniversalHash(hashTableSize);
		hashTable = new subTable[hashTableSize];
	}

	@Override
	public boolean get(int key) {
	
		subTable sub= hashTable[universalHash.hashKey(key)];
		if(sub==null){
			return false;
		}else{
			return sub.get(key) ;
		}
	}

	@Override
	public int buildTable() throws Exception {
		int counter=0;
		if(this.keys==null){
			throw new RuntimeException("Can't Build without setting setOfKeys");
		}else{
			for(int i=0;i<this.keys.length;i++){
				int hashedKey = this.universalHash.hashKey(keys[i]);
				subTable sub;
				if(hashTable[hashedKey]==null){
				 sub = new subTable();
					counter+=sub.addToUniverseOfKeys(keys[i]);
					hashTable[hashedKey]=sub;
				}else{
					System.out.println("collision in outer hash table");
				sub= hashTable[hashedKey];
				sub.addToUniverseOfKeys(keys[i]);
				}
				
			}
		}
		return counter;
	}

	@Override
	public HashFunction getuniversalHashFunctions() {
		return this.universalHash;
	}
	
	
	
	
	
	
	
	private class subTable {
		private int []hashTableSub;
		private int numberOfKeys;
		private LinkedList<Integer> keys;
		private HashFunction universalHashSub;
		private int hashTableSubSize;
		
		public subTable() {
			keys = new LinkedList<>();
			hashTableSub = new int[0];
			numberOfKeys=0;
		}
		public HashFunction getuniversalHashFunctions() {
			return this.universalHashSub;
		}
		
		public int addToUniverseOfKeys(int key) {
			this.hashTableSubSize=(int) Math.pow(numberOfKeys+1,2);
			 hashTableSub = new int[this.hashTableSubSize] ;
			numberOfKeys++;
			universalHashSub = new UniversalHash(hashTableSubSize);
		ListIterator<Integer> i =keys.listIterator();
				while(i.hasNext()){
					int k =i.next().intValue();
					System.out.println(universalHashSub.hashKey(k)+" uni hashing--ky "+k);
					if(hashTableSub[universalHashSub.hashKey(k)]==k){
						continue;
					}else
					if(hashTableSub[universalHashSub.hashKey(k)]!=EmptySlot){
						keys.add(new Integer(key));

						return rebuild();
						
					}
					keys.add(new Integer(key));

					hashTableSub[universalHashSub.hashKey(k)]=k;
				}
				return 0;
		}
		private int rebuild(){
			
			 hashTableSub = new int[this.hashTableSubSize] ;
			this.universalHashSub= new UniversalHash(this.hashTableSubSize);
			ListIterator<Integer> i =keys.listIterator();
			while(i.hasNext()){
				int k =i.next().intValue();
				
				System.out.println(universalHashSub.hashKey(k)+" uni hashing--RE---ky "+k);
				if(hashTableSub[universalHashSub.hashKey(k)]==k){
					continue;
				}else
				if(hashTableSub[universalHashSub.hashKey(k)]!=EmptySlot){
					
					return rebuild()+1;
				}
				hashTableSub[universalHashSub.hashKey(k)]=k;
			}
			System.out.println("rebuilt Success");
			return 0;
		}
		public boolean get(int key) {
			return this.hashTableSub[universalHashSub.hashKey(key)]!=EmptySlot ;
		}

		
	}
}
