package datastructure;

public class Queue extends DataStructure {
		
    private int front;      // front points to the front element in the queue
    private int rear;       // rear points to the last element in the queue
    private int capacity;   // maximum capacity of the queue
    private int count;      // current size of the queue
	
	public Queue() {
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
		if (isFull())
        {
            System.out.println("Overflow\nProgram Terminated");
            System.exit(-1);
        } 
        rear = (rear + 1) % capacity;
        arr[rear] = element;
        count++;		
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(int element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete() {
		if (isEmpty()) {
            System.out.println("Overflow\nProgram Terminated");
            System.exit(-1);
        } 
		int x = arr[front];		 
        System.out.println("Removing " + x); 
        front = (front + 1) % capacity;
        count--;
        return x;
	}
	
}
