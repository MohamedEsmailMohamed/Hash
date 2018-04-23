package lab03;

public interface IHash {
	/**
	 * generate random 0 and 1 matrix of size b(binaryHTSize)*u(U_BOUND) and save it in (hashArr)
	 */
	public void createHashMatrix();
	/**
	 * hash all keys in the table(hashTable) using hashKey method implicitly.
	 * @return
	 * 			true if no collisions happened
	 * 			false if a collision happened
	 */
	public boolean creatHashTable();
	/**
	 * calculate h matrix(hashArr) * x matrix (binary representation of key of size U)
	 * @param key : the value of key is wanted to be hashing
	 * @return
	 * 		the index
	 */
	public int hashKey(int key);
	/**
	 * loop to hash the set of keys until no collision happened using creatHashTable method
	 * @return
	 * 			number of collisions
	 */
	public int setHashN2();
	/**
	 * return the value in hash table
	 * @param key
	 * @return
	 */
	public int search(int key);
	
	
	public int setHashN1();
}
