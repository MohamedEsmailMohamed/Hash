package lastVersion;

public interface PerfectHashing {
	/**
	 * gets the class responsible for providing the hash functions
	 * @param universalHash
	 */
	HashFunction getuniversalHashFunctions();
	/**
	 * builds the hash table with the previous set universal hash IHash
	 * @param setArr array of keys
	 */
	void setUniverseOfKeys(int [] setOfKeys);
	
	/**L
	 * getting elements by key
	 * @param key
	 * @return true if exists
	 */
	boolean get(int key);
	/**
	 * builds the hash tables , requires setUniverseOfKeys and setUniversalHashFunctions
	   to be set before 
	 * @throws Exception , if data  hasn't been set 
	 */
	int buildTable() throws Exception;
}
