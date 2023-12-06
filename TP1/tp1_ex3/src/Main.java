import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
	public static void main(String[] args) {
		/*
		 * try { String filePath = "src/output.txt"; FileOutputStream fileOutputStream =
		 * new FileOutputStream(filePath); PrintStream printStream = new
		 * PrintStream(fileOutputStream); System.setOut(printStream);
		 */

		// Integers
		CustomArrayList<Integer> intList = new CustomArrayList<>();

		intList.add(5);
		intList.add(10);
		intList.add(5);
		intList.add(10);
		intList.add(5);
		intList.add(15);

		System.out.println("intList: " + intList);

		int n = 10;

		System.out.println("Index of " + n + " in intList: " + intList.find(n));
		System.out.println("intList contains " + n + ": " + intList.contains(n));

		// strings
		CustomArrayList<String> stringList = new CustomArrayList<>();

		stringList.add("Hello");
		stringList.add("World");
		stringList.add("!");

		System.out.println("stringList: " + stringList);

		stringList.remove("World");
		System.out.println("After removing 'World': " + stringList);

		// Additional functions
		intList.addAll(new Integer[] { 20, 30, 40 });
		System.out.println("intList after adding more elements: " + intList);

		intList.removeAll(10);
		System.out.println("intList after removing all '10': " + intList);

		intList.set(1, 25);
		System.out.println("intList after setting index 1 to '25': " + intList);

		intList.reverse();
		System.out.println("intList after reversing: " + intList);

		intList.removeRedundancy();
		System.out.println("intList after removing redundancy: " + intList);

		intList.sort();
		System.out.println("intList after sort: " + intList);

		CustomArrayList<Integer> intListCopy = intList.copy();
		System.out.println("Copy of intList: " + intListCopy);

		intList.resize(4);
		System.out.println("intList after resizing to 4: " + intList);

		intList.resize(8);
		System.out.println("intList after resizing to 8: " + intList);

		intList.clear();
		System.out.println("intList after clearing: " + intList);

		/*
		 * printStream.close(); fileOutputStream.close();
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */
	}

}
