package datastructure;

public class List {
  
public class List extends DataStructure
{
	private int count;
	
	public List() 
	{
		capacity = 1000;
		arr = new int[capacity];
		count = 0;
	}
	
	public int size() 
	{
		return count;
    }
	
	public boolean isEmpty()
	{
		return (count == 0);
	}
	
	public void insert(int element, int id) 
	{
	    for (int i = count; i > id; i--) 
	    {
	        arr[i] = arr[i - 1];
	    }

	    arr[id] = element;
	    count++;
	}

	@Override
	public void sort() 
	{
	    if (isEmpty()) 
	    {
	        System.out.println("List is empty. Nothing to sort.");
	        return;
	    }

	    binarySort(arr, 0, count - 1);
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
	
	@Override
	public String[] find(int element) 
	{
        StringBuilder sb = new StringBuilder();
        sb.append("Index of element: ");
        
	    for (int i = 0; i < count; i++) 
	    {
	        if (arr[i] == element) 
	        {
	        	sb.append(i);
	        	sb.append(",");
	        }
	    }
	    
        String[] ID = sb.toString().split(",");
	    
	    return ID;
	}
	
	public String toString() 
	{
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < count; i++) {
	        sb.append(arr[i]);

	        if (i < count - 1) 
	        {
	            sb.append(", ");
	        }
	    }

	    return "[" + sb.toString() + "]";
	}
	
	public int delete(int id) 
	{
	    if (isEmpty()) 
	    {
	    	return -1;
	    }

	    if (id < 0 || id >= count) 
	    {
	    	return -1;
	    }
	    
	    int deletedElement = arr[id];

	    for (int i = id; i < count - 1; i++)
	    {
	        arr[i] = arr[i + 1];
	    }

	    count--;
	    return deletedElement;
	}
	
	@Override
	public int delete()
	{
		return -1;
	}
}
