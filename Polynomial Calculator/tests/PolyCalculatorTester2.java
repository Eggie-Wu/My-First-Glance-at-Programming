import java.math.BigInteger;

class PolyCalculatorTester2 {
	public static void main(String[] args) {
		testadd1();
		testadd2();
		testadd3();
		testMultiplyTerm1();
		testMultiplyTerm2();
		testMultiply1();
		testMultiply2();
	
	
	
	
	}
	public static void testadd1()
	   {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial expected=new Polynomial();

		p1.addTerm(new Term(4, new BigInteger("5")));
		p1.addTerm(new Term(3, new BigInteger("4")));
		p1.addTerm(new Term(2, new BigInteger("3")));
		p1.addTerm(new Term(1, new BigInteger("2")));
		p1.addTerm(new Term(0, new BigInteger("1")));
		
		p2.addTerm(new Term(4, new BigInteger("-5")));
		p2.addTerm(new Term(3, new BigInteger("-4")));
		p2.addTerm(new Term(2, new BigInteger("-3")));
		p2.addTerm(new Term(1, new BigInteger("-2")));
		p2.addTerm(new Term(0, new BigInteger("-1")));
		
		
		Polynomial actual = Polynomial.add(p1, p2);
      System.out.println("test for add edge case 1");
	     System.out.println("what you got");
	     System.out.println(expected);
	     System.out.println("what should you have");
	     System.out.println(actual);
	     System.out.println("-------------------------");
	   }
	public static void testadd2()
	   {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial expected=p1;

		p1.addTerm(new Term(4, new BigInteger("5")));
		p1.addTerm(new Term(3, new BigInteger("4")));
		p1.addTerm(new Term(2, new BigInteger("3")));
		p1.addTerm(new Term(1, new BigInteger("2")));
		p1.addTerm(new Term(0, new BigInteger("1")));
		
		
		
		
		Polynomial actual = Polynomial.add(p1, p2);
   System.out.println("test for add edge case 2");
	     System.out.println("what you got");
	     System.out.println(expected);
	     System.out.println("what should you have");
	     System.out.println(actual);
	     System.out.println("-------------------------");
	   }
	public static void testadd3()
	   {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial expected=new Polynomial();

		p1.addTerm(new Term(4, new BigInteger("5")));
		p1.addTerm(new Term(3, new BigInteger("4")));
		p1.addTerm(new Term(2, new BigInteger("3")));
		p1.addTerm(new Term(1, new BigInteger("2")));
		p1.addTerm(new Term(0, new BigInteger("1")));
		
		p2.addTerm(new Term(4, new BigInteger("5")));
		p2.addTerm(new Term(3, new BigInteger("4")));
		p2.addTerm(new Term(2, new BigInteger("3")));
		p2.addTerm(new Term(1, new BigInteger("2")));
		p2.addTerm(new Term(0, new BigInteger("1")));
		expected.addTerm(new Term(4, new BigInteger("10")));
		expected.addTerm(new Term(3, new BigInteger("8")));
		expected.addTerm(new Term(2, new BigInteger("6")));
		expected.addTerm(new Term(1, new BigInteger("4")));
		expected.addTerm(new Term(0, new BigInteger("2")));
		
		
		Polynomial actual = Polynomial.add(p1, p2);
   System.out.println("test for add normal case");
	     System.out.println("what you got");
	     System.out.println(expected);
	     System.out.println("what should you have");
	     System.out.println(actual);
	     System.out.println("-------------------------");
	   }
	public static void testMultiplyTerm1()
	   {
	      Polynomial actual = new Polynomial();
	      Polynomial expected = new Polynomial();

	      actual.addTermLast(new Term(9, new BigInteger("-8")));
	      actual.addTermLast(new Term(8, new BigInteger("-2")));
	      actual.addTermLast(new Term(6, new BigInteger("-4")));

	      expected.addTermLast(new Term(3, new BigInteger("4")));
	      expected.addTermLast(new Term(2, new BigInteger("1")));
	      expected.addTermLast(new Term(0, new BigInteger("2")));

	      expected.multiplyTerm(new Term(6, new BigInteger("-2")));
         System.out.println("test for multiplyTerm with a non constant term");
	     System.out.println("what you got");
	     System.out.println(expected);
	     System.out.println("what should you have");
	     System.out.println(actual);
	     System.out.println("-------------------------");
	   }
	public static void testMultiplyTerm2()
	   {
	      Polynomial actual = new Polynomial();
	      Polynomial expected = new Polynomial();

	      actual.addTermLast(new Term(3, new BigInteger("-8")));
	      actual.addTermLast(new Term(2, new BigInteger("-2")));
	      actual.addTermLast(new Term(0, new BigInteger("-4")));

	      expected.addTermLast(new Term(3, new BigInteger("4")));
	     expected.addTermLast(new Term(2, new BigInteger("1")));
	      expected.addTermLast(new Term(0, new BigInteger("2")));

	      expected.multiplyTerm(new Term(0, new BigInteger("-2")));
      System.out.println("test for multiplyTerm with a constant negative term");
	     System.out.println("what you got");
	     System.out.println(expected);
	     System.out.println("what should you have");
	     System.out.println(actual);
	     System.out.println("-------------------------");
	   }
	public static void testMultiply1()
	   {
	      Polynomial p1 = new Polynomial();
	      Polynomial p2 = new Polynomial();
	      
	      Polynomial expected = new Polynomial();

	      p1.addTermLast(new Term(8, new BigInteger("3")));
	      p1.addTermLast(new Term(6, new BigInteger("7")));
	      p2.addTermLast(new Term(3, new BigInteger("5")));
	      p2.addTermLast(new Term(2, new BigInteger("4")));

	      expected.addTermLast(new Term(11, new BigInteger("15")));
	     expected.addTermLast(new Term(10, new BigInteger("12")));
	      expected.addTermLast(new Term(9, new BigInteger("35")));
         expected.addTermLast(new Term(8, new BigInteger("28")));
         Polynomial actual=Polynomial.multiply(p1,p2);
   System.out.println("test for multiply two polynomial");
	     System.out.println("what you got");
	     System.out.println(actual);
	     System.out.println("what should you have");
	     System.out.println(expected);
	     System.out.println("-------------------------");
	   }
	public static void testMultiply2()
	   {
	      Polynomial p1 = new Polynomial();
	      Polynomial p2 = new Polynomial();
	      
	      Polynomial expected = new Polynomial();

	      p1.addTermLast(new Term(8, new BigInteger("3")));
	      p1.addTermLast(new Term(6, new BigInteger("7")));
	      

	     ;
      Polynomial actual=Polynomial.multiply(p1,p2);
System.out.println("test for multiply two polynomial but one ploynomial has a value of 0");
	     System.out.println("what you got");
	     System.out.println(actual);
	     System.out.println("what should you have");
	     System.out.println(expected);
	     System.out.println("-------------------------");
	   }

}
