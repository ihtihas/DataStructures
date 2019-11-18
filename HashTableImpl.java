import java.util.ArrayList;

public class HashTableImpl {

	static class HashTable {

		private ArrayList<String> hashTable;

		private int curSize;
		private int maxSize;
		private int collisons;

		public HashTable(int maxSize) {
			this.hashTable = new ArrayList<>();
			this.maxSize = maxSize;
			this.curSize = 0;
			this.collisons = 0;

			for (int i = 0; i < maxSize; i++)
				hashTable.add(null);
		}

		private int getIndex(String key) {

			int hashCode = key.hashCode();
			int index = hashCode % maxSize;
			return Math.abs(index);
		}

		private int getPosition(String key) {
			int offset = 1;
			int currentPos = getIndex(key);
			System.out.print("HashValue:" + currentPos);

			while (hashTable.get(currentPos) != null) {
				currentPos += (offset * offset);
				currentPos = currentPos % maxSize;
				offset += 1;
				this.collisons++;

			}

			return currentPos;
		}

		private int primeValue(int value) {

			int counter;
			value++;
			while (true) {
				counter = 0;
				for (int i = 2; i <= Math.sqrt(value); i++) {
					if (value % i == 0)
						counter++;
				}
				if (counter == 0)
					return value;
				else {
					value++;
					continue;
				}
			}

		}

		public void add(String key) {

			int position = getPosition(key);

			hashTable.set(position, key);
			System.out.println("  Key:" + key + "  Index:" + position);

			double loadFactor = (1.0 * ++curSize) / maxSize;

			if (loadFactor >= 0.5) {
				System.out.println("collisons before Rehash:" + collisons);
				System.out.printf("LoadFactor %.2f", loadFactor);
				System.out.println("***Rehash***");
				ArrayList<String> temp = hashTable;
				hashTable = new ArrayList<>();
				maxSize = primeValue(2 * maxSize);
				curSize = 0;
				collisons = 0;
				for (int i = 0; i < maxSize; i++)
					hashTable.add(null);

				for (int i = 0; i < temp.size(); i++) {
					if (temp.get(i) != null) {
						add(temp.get(i));
					}
				}

				System.out.println("***completed****");
			}
		}
	}

	public static void main(String[] args) {

		HashTable hash = new HashTable(31);
		System.out.println("Initial Table Size :"+hash.maxSize);
		hash.add("How");
		hash.add("Hot");
		hash.add("six");
		hash.add("tEn");
		hash.add("Many");
		hash.add("loNg");
		hash.add("lioN");
		hash.add("niCe");
		hash.add("police");
		hash.add("Test");
		hash.add("polite");
		hash.add("xxxxxxxx");
		hash.add("xxxxxxxy");
		hash.add("intEger");
		hash.add("dftYi");
		hash.add("abcde");
		hash.add("bcdef");
		hash.add("hello");
		hash.add("ijklmno");
		hash.add("hi hi");
		System.out.println("Table Size at end: " + hash.maxSize);
		System.out.println("Collisons after Rehash:" + hash.collisons);
	}

}