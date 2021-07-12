//Oscar Ford
//
//Sortation:
//A program that creates a list of integers, converts it to a .txt file,
//extracts from a file, sorts the data in ascending order, and finds a value with binary search.

package ohford;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		run();
	}//main method
	
	public static void run() throws IOException
	{
		ArrayList<Integer> list= listCreator(100);
		listToFile(list);
		list= fileToList(new File("numbers.txt"));
		arrayPrinter(list);
		System.out.println(binarySearch(list, 5, 0,list.size()-1));
		long endTime=System.currentTimeMillis();
	}//run method. returns void. All methods to clear up main method
	
	static void swap(ArrayList<Integer> array, int left, int right)
	{
		int holder = array.get(left);
		array.set(left,array.get(right));
		array.set(right, holder);
	}//swap method returns void. Swaps two numbers in an ArrayList.
	
	public static void quickSort(ArrayList<Integer> array, int left, int right)
	{
	    if(left<right)
	    {
	    	int partitionedIndex=partition(array, left, right);
	    	
	    	quickSort(array, left, partitionedIndex-1);
	    	quickSort(array, partitionedIndex+1,right);
	    }
	}//quickSort method returns void. Sorts an ArrayList<Integer> in ascending order.
	
	public static int partition(ArrayList<Integer> array, int left, int right)
	{
		int pivot=array.get(right);
		
		int leftIndex = (left-1);
		
		for(int rightIndex=left; rightIndex<=right-1;rightIndex++)
		{
			if(array.get(rightIndex)<pivot)
			{
				leftIndex++;
				swap(array, leftIndex,rightIndex);
			}
		}
		swap(array, leftIndex+1, right);
		return(leftIndex+1);
	}//partition method returns int. Splits an ArrayList in half.

	public static void arrayPrinter(ArrayList<Integer> array)
	{
		int arraySize=array.size();
		
		for(int y=0; y<arraySize;)
		{
			for(int x=0; x<10;x++)
			{
				System.out.print(array.get(y++)+"\t");
			}
			System.out.println();
		}

	}//arrayPrinter method returns void. Prints out each element in the ArrayList
	
	public static ArrayList<Integer> listCreator(int listSize)
	{
		ArrayList<Integer> array=new ArrayList<Integer>();
		Random random= new Random();
		
		for(int x=0; x<listSize;x++)
		{
			array.add(random.nextInt(1000));
		}
		
		return array;
	}//listCreator method returns an ArrayList<Integer>. Creates numbers from 1-1000 with any set amount for the size.
	
	public static FileWriter listToFile(ArrayList<Integer> array) throws IOException
	{
		FileWriter file =new FileWriter("numbers.txt");
		
		int size= array.size();
		for(int x=0; x<size;x++)
		{
			file.write(array.get(x)+"\n");
		}
		
		file.close();
		
		return file;
	}//listToFile method returns FileWriter. Creates a .txt file that writes an ArrayList<Integer> to a file.
	
	public static ArrayList<Integer> fileToList(File file) throws FileNotFoundException
	{
		ArrayList<Integer> array=new ArrayList<Integer>();
		Scanner scanner=new Scanner(file);
		
		while(scanner.hasNextInt())
		{
				array.add((Integer)scanner.nextInt());				
		}
		
		scanner.close();
		quickSort(array, 0, array.size()-1);
		
		return array;
	}//fileToList method returns ArrayList<Integer>. Reads a file and converts it to an ArrayList<Integer>.
	
	public static int binarySearch(ArrayList<Integer> array, int value, int left, int right)
	{
		int mid= left+(right-left)/2;
		
		if(right-left<=0 && array.get(mid)!=value)
		{
			return -1;
		}
		else if(right-left<=0 && array.get(mid)==value)
		{
			return mid;
		}
		
		if(right >=1)
		{
			
			if(array.get(mid)==value)
			{
				return mid;
			}
			else
			{
				if(array.get(mid)<value)
				{
					return binarySearch(array, value, mid+1, right);
				}
			
				else
				{
					return binarySearch(array, value, left, mid-1);
				}
			}
		}
		
		return -1;
	}//binarySearch method returns an int. Searches through an ArrayList<Integer> and returns the index. If the element does not exist then it returns -1.
		
}//Main class
