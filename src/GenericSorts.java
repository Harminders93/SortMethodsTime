import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author HPS251, Harminder Singh
 *
 *This is the main class used to test performances of generic sorts!
 *All generic arrays inputed to the generic sorts must implement comparable<E> interface.
 */
public class GenericSorts {
	
	
	/**
	 * This class creates my Integer array used for testing.
	 * @param size - The array size, which dictates how many elements are going into the array.
	 * @return - An array with N (size) elements.
	 */
	public static Integer[] array(int size) {
		//This method only creates a list filled with elements if the size inputed is greater than or equal to 1.
		if(size >= 1) {
			//Creates the array
			Integer[] array = new Integer[size];
			//Fills it up with numbers starting from 1 to size.
			for(int x = 0; x < size; x++) {
				Integer a = new Integer(x);
				array[x] = a;
			}
			//Shuffles the array for randomization
			Shuffle(array);
			//returns the array
			return array;
		}
		else {
			//Return empty array of size 1.
			Integer[] array = new Integer[1];
			return array;
		}
	}
	
	/**
	 * This class is used to shuffle all elements of my Integer list. It uses generics because it can shuffle any
	 * list of type E as long as it implements Comparable<E> interface.
	 * 
	 * @param list - Takes in a list to be shuffled.
	 */
	private static <E> void Shuffle(E[] list) {
		//If the list points to null, return.
		if(list == null) {
			return;
		}
		//If the length of the list is less than or equal to 1, return.
		if(list.length <= 1) {
			return;
		}
		//Create a random reference.
		Random rand = new Random();
		//Have two indices
		int left, right;
		//A place holder for a element of type E.
		E temp;
		//Loop through the list
		for(int i = 0; i < list.length; i++) {
			//Generate two random indices
			left = rand.nextInt(list.length - 1);
			right = rand.nextInt(list.length - 1);
			
			//Do the swap.
			temp = list[left];
			list[left] = list[right];
			list[right] = temp;
		}
	}
	
	/**
	 * This is my performance method that takes in a copied array and returns an arraylist filled with the results generated.
	 * 
	 * To find milliseconds, I divided the nanotime by 10^6.
	 * @param temp - The temporary array (copied version)
	 * @param sort - Sort object used to invoke sort methods
	 * @param results - An arraylist to fill the results collected
	 * @param cases - Invokes different sort methods based on cases int.
	 * @return - ArrayList<String> that holds the results.
	 */
	public static <E extends Comparable<E>> ArrayList<String> Time(E[] temp, SortingMethods sort, ArrayList<String> results, int cases) {

			//Cases 0 invokes the selectionsort method on the copied array and adds it to the results.
			if(cases == 0) {
				long start = System.nanoTime();
				sort.SelectionSort(temp);
				long end = System.nanoTime();
				long result = (end - start)/1000000;
				results.add("1. SelectionSort time: " + Long.toString(result));
			}
			
			//Cases 1 invokes the quicksort method on the copied array and adds it to the results.
			if(cases == 1) {
				long start = System.nanoTime();
				sort.quick(temp);
				long end = System.nanoTime();
				long result = (end - start)/1000000;
				results.add("2. QuickSort time: " + Long.toString(result));
			}
			
			//Cases 2 invokes the mergesort method on the copied array and adds it to the results.
			if(cases == 2) {
				long start = System.nanoTime();
				sort.mergeSort(temp);
				long end = System.nanoTime();
				long result = (end - start)/1000000;
				results.add("3. MergeSort time: " + Long.toString(result));
			}
		
		//Returns the arraylist of results
		return results;
	}
	
	
	/**
	 * This is the main method that calls on the performance method.
	 * @param args - User input. Not used for this project!
	 */
	public static void main(String[] args) {
		
		//Object needed to call sort methods!
		SortingMethods sort = new SortingMethods();
		
		//Creates an array populated with N elements. (N = parameter). Shuffling is used!
		Integer[] arr = array(10000);
	
		//Generate an arraylist to hold string values representing the time.
		ArrayList<String> results = new ArrayList<String>();
		
		System.out.println("Performing Sort methods on the given array!");
		//Call the performance method on the various cases.
		for(int i = 0; i < 3; i++) {
			Integer[] temp = Arrays.copyOf(arr, arr.length);
			Time(temp, sort, results, i);
		}
		
		//Print out the results
		for(String s : results) {
			System.out.println(s);
		}
		
		
		
		
	}
	
}
