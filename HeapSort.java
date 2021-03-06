import java.util.Scanner;

public abstract class HeapSort{

	static int n,comparisons=0; //global variable to store comparisons
	static Integer x,y;
	//overridden function compare
	public static int compare(int l,int r)
	{
		comparisons++;
		x = Integer.valueOf(l);
		y = Integer.valueOf(r);
		return x.compareTo(y);
	}
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);
		//Parse length of array from first line
		int length = Integer.parseInt(input.nextLine().replaceAll("n ", ""));

		int[] myArray= new int[length];

		for(int i=0;i<length;i++){
			myArray[i]=input.nextInt();
		}
		input.close();
		//Record system time at start
		long startTime = System.currentTimeMillis();
		//Call to Heap Sort
		heap_sort(myArray);
		//Record system time at end
		long endTime = System.currentTimeMillis();
		//Difference is elapsed time and convert to integer.
		long elapsedTime = endTime - startTime;
		//Check to see if long goes over max integer value
		if(elapsedTime > Integer.MAX_VALUE){
			throw new IllegalArgumentException(elapsedTime + "cannot convert to int without modifying value");
		}
		//Type cast to integer and write to variable
		int runtime = (int) elapsedTime;

		printArray(myArray);

		//Write runtime and key comparisons to standard error
		System.err.println("runtime," + runtime);
		System.err.println("comparisons," + comparisons);


	}
	public static void heap_sort(int[] myArray)
	{
		n=myArray.length;
		//heapify the array
		for(int i=n/2-1;i>=0;i--)
		{
			heapify(myArray,i);
		}
		
		for(int j=n-1;j>=0;j--)
		{
			//swap the current root to end
			int temp=myArray[0];
			myArray[0]=myArray[j];
			myArray[j]=temp;
			n=n-1;

			//heapify rest of the heap
			heapify(myArray,0);

		}

	}

	public static void heapify(int[] myArray,int i)
	{
		int largest=i;
		int l=left(i);	//function to get left child of node i
		int r=right(i);	//function to get right child of node i
		
		//compare left child to root and update largest
		if(l<n && compare(myArray[l],myArray[i])>=0)
		{
			largest=l;
		}
		else
		{	largest=i;

		}
		
		//compare right child to largest so far
		if(r<n && compare(myArray[r],myArray[largest])>=0)
		{
			largest=r;
		}
		
		//if largest is not the root, swap them and re-heapify
		if(largest!=i)
		{
			int temp=myArray[i];
			myArray[i]=myArray[largest];
			myArray[largest]=temp;

			heapify(myArray,largest);
		}
	}
	//function to find left child of node i
	public static int left(int i)
	{
		return 2*i+1;
	}
	//function to find right child of node i
	public static int right(int i)
	{
		return 2*i+2;
	}
	//function to print any array
	public static void printArray(int[] array)
	{
		for(int i=0;i<array.length;i++)
			System.out.println(array[i]);
	}

}
