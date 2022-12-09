import java.util.Stack;
import java.util.EmptyStackException;

class MyStack {
	
    // minValue stores the smallest element in MyStack
    private Integer minValue;
	
    // dataStack stores the difference between the current minValue and the real value of the element.
    private Stack<Integer> dataStack = new Stack<>();
    


    //push the new element into the stack
    public void push(int newelement) {
    	
    	if (dataStack.isEmpty()) { // If the stack is empty
            minValue = newelement;	// the smallest element will be the new element which is being pushed in to the stack
            dataStack.push(0);	// the difference between new element and the smallest element is 0.
            
            
            
        }else {//If the stack isn't empty
            Integer diff = newelement - minValue;	// we compute the difference between the new element and the smallest element
            dataStack.push(diff);// and push the difference into the dataStack
            
            if(diff < 0) {// if the difference is less than 0, it shows that the new element is smaller than the current smallest element
                minValue = newelement;//thus we simply update the smallest element.
            }
        }
    }

    
    //pop the top element out of the stack
    public int pop() {
    	
    	if (dataStack.isEmpty()) 
        { 
    		throw new EmptyStackException(); //If the stack is empty, it notifies the user.
        } 
    	
        Integer val = dataStack.pop();
        //the value inside the dataStack is the difference between the actual value and the minValue/previous minValue.
        
        if(val < 0) {
        	// if the top element that is being popped is exactly the smallest element,
        	int temp = minValue;//store the minValue in a temporary parameter
   
            minValue = minValue - val;//we update the minValue to the second smallest element.
            
            return temp;//return the element that is being popped
            
        }else {//if the top element that is being popped isn't the smallest element
        	
        	return minValue + val;//we calculate its real value and return it
        }
    }

    //get the top element
    public int peek() {
        Integer val = dataStack.peek();

        if(val < 0) {
        	// if the top element that is being returned is exactly the smallest element,
        	
        	return minValue;//we simply return the smallest element
        	
        }else {//if it is not
        	
        	return minValue + val;//we calculate the real value and return it
        }
       
    }

    //get the smallest element
    public int min() {
        if(dataStack.isEmpty()) {
        	throw new EmptyStackException(); //If the stack is empty, it notifies the user.
        }else {
        	return minValue;//if it is not empty it simply return the minValue, which is the smallest element.
        }
        
    }
}
