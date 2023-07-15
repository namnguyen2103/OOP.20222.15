package datastructure;

public abstract class DataStructure 
{
	
	protected int capacity;
    protected int[] arr;
    
    public DataStructure() {
    }
    
    public void insert(int element) {    	
    };
    
    public void sort() {    	
    };
    
    public abstract String[] find(int element); 
    
    public abstract int delete();      
    
}

