package datastructure;

public abstract class Linkedlist 
{
    Node head; // head of list
    public Linkedlist(Linkedlist arr) 
    {
    	head = null;
    }
    
    public Linkedlist insertFirst(Linkedlist list, int data)
    {
        Node new_node = new Node(data);
  
        // Set the next of new_node as the current head
        new_node.next = list.head;
  
        // Make the new_node as the new head
        list.head = new_node;
  
        // Return the updated list
        return list;
    }

	public void insertLast(Linkedlist list, int data)
    {
        Node new_node = new Node(data);
        new_node.next = null;
  
        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) 
        {
            list.head = new_node;
        }
        else 
        {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) 
            {
                last = last.next;
            }
  
            // Insert the new_node at last node
            last.next = new_node;
        }
  
        // Return the list by head
    }
  
    // Method to print the LinkedList.
    public void printList(Linkedlist list)
    {
        Node currNode = list.head;
  
        while (currNode != null) 
        {
            System.out.print(currNode.data + " ");
             currNode = currNode.next;
        }
    }
    
    public void deleteByKey(Linkedlist list, int key)
    {
		Node currNode = list.head, prev = null;

		if (currNode != null && currNode.data == key) 
		{
			list.head = currNode.next; // Changed head
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
    
    public int findByNum(Linkedlist list, int key)
    {
    	int count = 0;
		Node currNode = list.head, prev = null;

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
    
    public int findByID(Linkedlist list, int id)
    {
    	int count = 0;
		Node currNode = list.head, prev = null;

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
    
    public void bubbleSort(Linkedlist list) 
    {
        int n = length(list);
        if (n <= 1) {
            // Already sorted or empty list
            return;
        }

        boolean swapped;
        do 
        {
            swapped = false;
            Node current = list.head;
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
                        list.head = nextNode;
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

    // Helper method to calculate the length of the linked list
    private int length(Linkedlist list) 
    {
        int count = 0;
        Node current = list.head;
        while (current != null) 
        {
            count++;
            current = current.next;
        }
        return count;
    }
    
  
    
}
