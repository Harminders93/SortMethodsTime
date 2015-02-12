import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 
 * @author HPS251
 * 
 * This is the Sorting Methods class. It displays the three sorts required for the project.
 * MergeSort - Implemented using Generics and Recursion
 * SelectionSort - Implemented using Generics and Iteration
 * QuickSort - Implemented using Generics and Recursion
 * 
 * This sorting class uses Generics. All type E lists must implement the Comparable interface!
 */
public class SortingMethods {

	/**
	 * Constructor class for sort methods.
	 */
	public SortingMethods() {
		
	}
	
	/**
	 * This is the main method call for mergeSort. It invokes the recursive method.
	 * @param list - Takes in an Array to be sorted.
	 */
	public <E extends Comparable<E>> void mergeSort(E[] list) {
		mergeSortRec(list, 0, list.length - 1);
	}
	
	/**
	 * This is the recursive method call for mergeSort.
	 * 
	 * @param list - List to be sorted
	 * @param low - First element of array (Always 0)
	 * @param high - Last element of array (List.length - 1).
	 */
	private <E extends Comparable<E>> void mergeSortRec(E[] list, int low, int high) {
		
		//This is the base case. If the first index is greater than or equal to the last index. Return
		if(low >= high) {
			return;
		}
		
		//Create a middle point used to split the initial array into smaller parts.
		int mid = (low + high) / 2;
	
		//Recursive call on both the two arrays.
		mergeSortRec(list, low, mid);
		mergeSortRec(list, mid + 1, high);
		//Merge call on the arrays formed through recursion
		merge(list, low, mid, mid + 1, high);
	}
	
	/**
	 * 
	 * This is the merge method used to combine the arrays recursed on in the recursive method.
	 * @param list - List to be merged
	 * @param lf - The first index of the first array split (usually 0)
	 * @param ll - The last index of the first array split (mid value)
	 * @param rf - The first index of the second array split (mid + 1)
	 * @param rl - The last indexx of the second array split (list.length - 1).
	 */
	private <E extends Comparable<E>> void merge(E[] list, int lf, int ll, int rf, int rl) {
		
		//Generate a generic list using the new instance method.
		@SuppressWarnings("unchecked")
		E arr[] = (E[]) Array.newInstance(list.getClass().getComponentType(), rl - lf + 1);
		
		//Set a low index used for copying contents of temporary array to original array
		int low = lf;
		//Set index for temp array
		int index3 = 0;
		
		//Loop through both split arrays until one of them becomes empty.
		while(lf <= ll && rf <= rl) {
			//Compare the values to see if either of them is smaller than one another. Add the smaller one to the temp array. Increment indices.
			if(list[lf].compareTo(list[rf]) <= 0) {
				arr[index3++] = list[lf++];
			}
			else {
				arr[index3++] = list[rf++];
			}
		}
		
		//If one of these lists is not empty, then it copies the remaining elements
		while(lf <= ll) {
			arr[index3++] = list[lf++];
		}
		while(rf <= rl) {
			arr[index3++] = list[rf++];
		}
		
		//Copies values from temp array (arr) to original array (list).
		for(int i = 0; i < arr.length; i++) {
			list[i + low] = (arr[i]);
		}
		
	}
	
	/**
	 * This is the main method for quick sort. 
	 * It sorts array based on a pivot that is the center pivot!
	 * @param list - List to be sorted!
	 */
	public <E extends Comparable<E>> void quick(E[] list) {
		quickSort(list, 0, list.length - 1);
	}
	
	/**
	 * This is the method that not only partitions the list, but also calls the recursive call!
	 * 
	 * @param list - List to be sorted
	 * @param low - The lower index of the list. - zero
	 * @param high - The highest index of the list - list.length - 1
	 */
	private <E extends Comparable<E>> void quickSort(E[] list, int low, int high) {
		
		//Base case to see if the lower index is greater than or equal to lower index. If so, return.
		if(low >= high) {
			return;
		}
		
		// Set two ints equal to the low and high index.
		//Create the middle int and a type E holder for the pivot.
		int left = low;
		int right = high;
		int mid = (low + high)/2;
		E pivot = list[mid];
		
		//Loop through the list while the two indices don't cross.
		while( left <= right) {
			//Compare the left pointer to the pivot, if it's less than the pivot, increment
			while(list[left].compareTo(pivot) < 0) {
				left++;
			}
			//Compare the right pointer to the pivot, if it's greater than, inrement.
			while(list[right].compareTo(pivot) > 0) {
				right--;
			}
			//If the two pointers stop at a position where they don't meet the while conditions, swap and increment.
			if(left <= right) {
				E temp = list[left];
				list[left] = list[right];
				list[right] = temp;
				left++;
				right--;
			}
		}
		
		//Recurse on the array located to the left of the finalized pivot.
		if(low <= right) {
			quickSort(list, low, right);
		}
		//Recurse on the array located to the right of the finalized pivot.
		if(high >= left) {
			quickSort(list, left, high);
		}
	}
	
	/**
	 * This is the main method for the selection sort. Selection sort is implemented using iteration.
	 * @param list - List to be sorted
	 */
	public <E extends Comparable<E>> void SelectionSort(E[] list) {
		
		//This integer value loops through all the indices in the list.
		for(int x = 0; x < list.length - 1; x++) {
			//Set current minimum value equal to the current index being looped on.
			//Set the minimum element to a place holder.
			E currMin = list[x];
			int minIndex = x;
			
			//Loop through all element located to the right of the first index (x).
			for(int i = x + 1; i < list.length; i++) {
				//If statement to replace currMin and minIndex if a smaller element is found.
				if(currMin.compareTo(list[i]) > 0) {
					currMin = list[i];
					minIndex = i;
				}
			}
			
			//If the element at index minIndex is not the current index (x). Swap.
			if(minIndex != x) {
				list[minIndex] = list[x];
				list[x] = currMin;
			}
		}
	}
	
}
