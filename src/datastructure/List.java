package datastructure;

public class List extends DataStructure
{
	private int len;
	
	public List() 
	{
		len = 0;
	}
	
	public int size() 
	{
        return len;
    }
	
	public boolean isEmpty() 
	{
        return (size() == 0);
	}    
    public boolean isFull() {
        return (size() == arr.length);
    }    
	
	public void insert(int element, int id) 
	{
	    if (isFull()) 
	    {
	        System.out.println("Overflow\nProgram Terminated");
	        System.exit(-1);
	    }

	    if (id < 0 || id > len) 
	    {
	        System.out.println("Invalid ID\nProgram Terminated");
	        System.exit(-1);
	    }

	    for (int i = len; i > id; i--) 
	    {
	        arr[i] = arr[i - 1];
	    }

	    arr[id] = element;
	    len++;
	}

	@Override
	public void sort() 
	{
	    if (isEmpty()) 
	    {
	        System.out.println("List is empty. Nothing to sort.");
	        return;
	    }

	    binarySort(arr, 0, len - 1);
	}

	private void binarySort(int[] arr, int low, int high) 
	{
	    if (low < high) 
	    {
	        int mid = (low + high) / 2;
	        binarySort(arr, low, mid);
	        binarySort(arr, mid + 1, high);
	        merge(arr, low, mid, high);
	    }
	}

	private void merge(int[] arr, int low, int mid, int high) 
	{
	    int n1 = mid - low + 1;
	    int n2 = high - mid;

	    int[] left = new int[n1];
	    int[] right = new int[n2];

	    for (int i = 0; i < n1; i++) left[i] = arr[low + i];
	    for (int j = 0; j < n2; j++) right[j] = arr[mid + 1 + j];
	    
	    int x = 0, y = 0;
	    int k = low;

	    while (x < n1 && y < n2) 
	    {
	        if (left[x] <= right[y]) 
	        {
	            arr[k] = left[x];
	            x++;
	        } 
	        else 
	        {
	            arr[k] = right[y];
	            y++;
	        }
	        
	        k++;
	    }

	    while (x < n1) 
	    {
	        arr[k] = left[x];
	        x++;
	        k++;
	    }

	    while (y < n2) 
	    {
	        arr[k] = right[y];
	        y++;
	        k++;
	    }
	}

	public void find(int element) 
	{
	    for (int i = 0; i < len; i++) 
	    {
	        if (arr[i] == element) 
	        {
	        	 System.out.println(i);
	        }
	    }
	    System.out.println("Not found"); // Element not found
	}

	@Override
	public int delete(int id) 
	{
	    if (isEmpty()) 
	    {
	        System.out.println("Underflow\nProgram Terminated");
	        System.exit(-1);
	    }

	    if (id < 0 || id >= len) {
	        System.out.println("Invalid ID\nProgram Terminated");
	        System.exit(-1);
	    }

	    int deletedElement = arr[id];

	    // Shift elements to the left to fill the gap created by the deleted element
	    for (int i = id; i < len - 1; i++) {
	        arr[i] = arr[i + 1];
	    }

	    len--;
	    return deletedElement;
	}
}
