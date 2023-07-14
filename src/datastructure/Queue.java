package datastructure;

import java.util.Arrays;

public class Queue extends DataStructure {
		
    private int front;      // front points to the front element in the queue
    private int rear;       // rear points to the last element in the queue
    private int capacity;   // maximum capacity of the queue
    private int count;      // current size of the queue
	
    public Queue(int capacity)
    {
        arr = new int[capacity];
        this.capacity = capacity;
        front = 0;
        rear = -1;
        count = 0;
    }
	
	//Utility functions
	public int size() {
        return count;
    }
	public boolean isEmpty() {
        return (size() == 0);
	}    
    public boolean isFull() {
        return (size() == capacity);
    }    
	
	@Override
	public void insert(int element) {
        rear = (rear + 1) % capacity;
        arr[rear] = element;
        count++;		
	}

	@Override
	public void sort() {
	    if (isEmpty() || count == 1) {
	        return;
	    }

	    int[] sortedArray = new int[count]; // Create a new array to store the sorted elements
	    int index = 0;

	    // Copy elements from the queue to the sorted array
	    while (!isEmpty()) {
	        sortedArray[index] = delete();
	        index++;
	    }

	    Arrays.sort(sortedArray);

	    // Insert the sorted elements into the new queue
	    for (int element : sortedArray) {
	        this.insert(element);
	    }

	}


	@Override
	public String[] find(int element) {
    	Queue queueCopy = new Queue(capacity);
        StringBuilder sb = new StringBuilder();
        String delimiter = "#"; 
        boolean notFound = true;

        int size = size();

        for (int i = 0; i < size; i++) {
            int currentElement = arr[front];
            queueCopy.insert(currentElement);
            front = (front + 1) % capacity;
        }                

        sb.append("We will apply the method Dequeue and Search here, ...").append(delimiter);
        sb.append("The original queue: \n").append(queueCopy.toString()).append(delimiter);
        
        for (int i = 0; i < size; i++) {
            int currentElement = queueCopy.delete();
            sb.append("The front element is ").append(currentElement).append(".");
            if (currentElement == element) {
                sb.append(delimiter);
            	sb.append("Element ").append(element).append(" found in the queue.");
                notFound = false;
                break;
            }
            sb.append("The queue after dequeuing: ").append(queueCopy.toString()).append(delimiter);
        }
        
        if (notFound == true) {
        	sb.append("Element ").append(element).append(" not found in the queue.\n");
        }
        
        String[] steps = sb.toString().split(delimiter);
        return steps;
    }

	@Override
	public int delete() {
		int x = arr[front];	
        front = (front + 1) % capacity;
        count--;
        return x;
	}
	
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("front => [");
        if (!isEmpty()) {
            int index = front;
            for (int i = 0; i < count; i++) {
                sb.append(arr[index]);
                if (i != count - 1) {
                    sb.append(", ");
                }
                index = (index + 1) % capacity;
            }
        }
        sb.append("] <= rear");
        return sb.toString();
    }
	
	public static void main (String[] args)
    {
        // create a queue of capacity 5
        Queue q = new Queue(8);
 
        q.insert(5);
        q.insert(-3);
        q.insert(4);
        q.insert(2);
        q.insert(1);
        System.out.println(q);
        q.sort();
        System.out.println(q);
    } 
}
