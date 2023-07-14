package datastructure;

public abstract class MyLinkedlist 
{
    private Node head;     
    public MyLinkedlist(Node nodehead) 
    {
    	head = nodehead;
    }
    
    public void insertFirst(int data)
    {
        Node new_node = new Node(data);
        
        new_node.next = this.head;
        this.head = new_node;
 
    }

	public void insertLast(int data)
    {
        Node new_node = new Node(data);
        new_node.next = null;
  
        if (this.head == null) 
        {
            this.head = new_node;
        }
        else 
        {
            Node last = this.head;
            while (last.next != null) 
            {
                last = last.next;
            }
  
            last.next = new_node;
        }
  
    }
  
    public void deleteByKey(int key)
    {
		Node currNode = this.head, prev = null;

		if (currNode != null && currNode.data == key) 
		{
			this.head = currNode.next; // Changed head
			System.out.println(key + " found and deleted");
		}
		
		
		 while (currNode != null && currNode.data != key) 
		 {
	        prev = currNode;
	        currNode = currNode.next;
		 }
	  
	     if (currNode != null) 
	     {
	         prev.next = currNode.next;
	         System.out.println(key + " found and deleted");
	     }
	        
	     if (currNode == null) 
	     {
	    	 System.out.println(key + " not found");
	     }
    	  
    }
    
    public int findByNum(int key)
    {
    	int count = 0;
		Node currNode = this.head, prev = null;

		if (currNode != null && currNode.data == key) 
		{
			return count; // Changed head		
		}
		
		
		 while (currNode != null && currNode.data != key) 
		 {
	        prev = currNode;
	        currNode = currNode.next;
	        ++count;
		 }
	  
	     if (currNode != null) 
	     {
	         return count;
	     }
	     
	     return -1;
    	     	
    }
    
    public int findByID(int id)
    {
    	int count = 0;
		Node currNode = this.head, prev = null;

		if (currNode != null && count == id) 
		{
			return currNode.data; // Changed head
		}
		
		
		 while (currNode != null && count < id) 
		 {
	        prev = currNode;
	        currNode = currNode.next;
		 }
	  
	     if (currNode != null) 
	     {
	         return currNode.data;
	     }
	     
	     return -1;
    	  
    }
    
    public void bubbleSort() 
    {
        int n = this.length();
        if (n <= 1) {
            // Already sorted or empty list
            return;
        }

        boolean swapped;
        do 
        {
            swapped = false;
            Node current = this.head;
            Node previous = null;

            while (current != null && current.next != null) 
            {
                Node nextNode = current.next;
                if (current.data > nextNode.data) 
                {
                    // Swap adjacent nodes
                    if (previous == null) 
                    {
                        // Update the head
                        this.head = nextNode;
                    } else {
                        previous.next = nextNode;
                    }
                    current.next = nextNode.next;
                    nextNode.next = current;
                    swapped = true;
                }
                previous = current;
                current = current.next;
            }
        } 
        while (swapped);
    }

    private int length() 
    {
        int count = 0;
        Node current = this.head;
        while (current != null) 
        {
            count++;
            current = current.next;
        }
        return count;
    }

  
}
