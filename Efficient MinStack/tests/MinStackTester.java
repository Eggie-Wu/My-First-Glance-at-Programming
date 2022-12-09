class MinStackTester {

	public static void main(String[] args) {
		
		MyStack test = new MyStack(); 
		
		//System.out.println(test.peek());
		
		//System.out.println(test.pop());
		
		int[] a= {1,2,3};
		int[] b=a;
		int[][] c= {a,b};
		
        test.push(3); 
        test.push(9); 
        test.push(2); 
        test.push(5); 
        test.push(90); 
        test.push(31); 
        test.push(-2); 
        test.push(885); 
        test.push(-9); 
        test.push(-22);
        
        //3,9,2,5,90,31,-2,885,-9,-22
        
        System.out.println(test.min()); 
     
        test.pop(); 
        
        System.out.println(test.min());

        test.pop(); 
        
        System.out.println(test.min());
        
        test.pop(); 
        
        System.out.println(test.min());
        
        test.pop(); 
        
        System.out.println(test.min());
        System.out.println(test.peek());


	}

}
