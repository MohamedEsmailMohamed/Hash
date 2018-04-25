package lastVersion;

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
		LinkedList<Integer> filledSlots = new LinkedList<Integer>();

		if(this.keys==null){
			throw new RuntimeException("Can't Build without setting setOfKeys");
		}else{
			for(int i=0;i<this.keys.length;i++){
				int hashedKey = this.universalHash.hashKey(keys[i]);
				subTable sub;
				if(hashTable[hashedKey]==null){
				 sub = new subTable();
				 sub.add(keys[i]);
					hashTable[hashedKey]=sub;
					filledSlots.add(hashedKey);
				}else{
				sub= hashTable[hashedKey];
				 sub.add(keys[i]);

				}
				
			}
		}
		int counter =0;
		System.out.println(filledSlots.size());
		for (int i=0;i<filledSlots.size();i++){
			//System.out.println("--"+i+"   "+counter);
			counter+=hashTable[filledSlots.get(i)].build();
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
		public void add(int key) {
			for(int i=0;i<keys.size();i++){
				if(keys.get(i).intValue()==key){
					return ;
				}
			}
			Integer intt =new Integer(key);
			keys.add(intt);
			
		}
		public int build(){
			this.hashTableSubSize=(int) Math.pow(keys.size(),2);
			 hashTableSub = new int[this.hashTableSubSize] ;
				this.universalHashSub = new UniversalHash(hashTableSubSize);

			 ListIterator<Integer> i =keys.listIterator();
				while(i.hasNext()){
					int k =i.next().intValue();
					
					if(hashTableSub[universalHashSub.hashKey(k)]!=EmptySlot){
						return build()+1;
					}

					hashTableSub[universalHashSub.hashKey(k)]=k;
				}
				return 0;
		
		}
				public boolean get(int key) {
			return this.hashTableSub[universalHashSub.hashKey(key)]!=EmptySlot ;
		}

		
	}
}
