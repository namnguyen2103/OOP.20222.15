package datastructure;

public class List extends Linkedlist 
{
	private int noElement;
	private Linkedlist arr;
		
	public List(Linkedlist arr,int noElement)
	{
		super(arr);
		this.noElement = noElement;
	}
	
	public void insert(int num)
	{
		insertLast(arr, num);
	}
	
	
	public void deleteByID(int id)
	{
		int key = findByID(arr, id);
		deleteByKey(arr, key);
	}
	
	public void find(int num)
	{
		int id = findByNum(arr, num);
		System.out.println(id);
	}
	
    public void sort()
    {
        // Bubble Sort Algorithm
        if (noElement <= 1) 
        {
            return; // Already sorted or empty list
        }

        boolean swapped;
        Node currentNode;
        Node nextNode = null;

        do 
        {
            swapped = false;
            currentNode = arr.head;

            while (currentNode.next != nextNode) 
            {
                if (currentNode.data > currentNode.next.data) 
                {
                    // Swap the nodes
                    int temp = currentNode.data;
                    currentNode.data = currentNode.next.data;
                    currentNode.next.data = temp;
                    swapped = true;
                }
                
                currentNode = currentNode.next;
            }
            
            nextNode = currentNode;

        } 
        while (swapped);
    }vn    

}

