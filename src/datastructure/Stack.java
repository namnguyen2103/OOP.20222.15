package datastructure;

public class Stack extends DataStructure{

    private int top;        // top points to the top element of the stack
    private int capacity;   // maximum capacity of the stack
    private int count;      // current size of the stack

    public Stack(int capacity)
    {
        arr = new int[capacity];
        this.capacity = capacity;
        top = -1;
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
    public int peek() {
        return arr[top];
    }

    @Override
    public void insert(int element) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        arr[++top] = element;
        count++;
    }

    @Override
    public void sort() {
        Stack tempArr = new Stack(capacity);
        while (!isEmpty()) {
            int temp = delete();
            while (!tempArr.isEmpty() && temp < tempArr.peek()) {
                insert(tempArr.delete());
            }
            tempArr.insert(temp);
        }
        while (!tempArr.isEmpty()) {
            insert(tempArr.delete());
        }
    }

    @Override
    public String[] find(int element) {
        Stack tempArr = new Stack(capacity);
        StringBuilder sb = new StringBuilder();
        String delimiter = "#";
        boolean notFound = true;
        
        int size = size();

        for (int i = 0; i < size; i++) {
            tempArr.insert(arr[i]);
        }

        sb.append(tempArr.toString()).append(delimiter);

        for (int i = top; i >= 0; i--) {
            tempArr.delete();
            if (arr[i] == element) {
                sb.append("Element ").append(element).append(" is found in the stack.");
                notFound = false;
                break;
            }
            sb.append(tempArr.toString()).append(delimiter);
        }

        if (notFound == true) {
            sb.append("Element ").append(element).append(" is not found in the stack.");
        }

        String[] steps = sb.toString().split(delimiter);
        return steps;
    }

    @Override
    public int delete() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        count--;
        return arr[top--];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!isEmpty()) {
            for (int i = 0; i < count; i++) {
                sb.append(arr[i]);
                if (i != count - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("] <= top");
        return sb.toString();
    }

    public static void main (String[] args) {
        // create stack of capacity 5
        Stack s = new Stack(5);

        s.insert(2);
        s.insert(5);
        s.insert(3);
        s.insert(4);
        s.insert(1);
        String[] steps = s.find(6);
        for (String step: steps) {
            System.out.println(step);
        }
    }
}