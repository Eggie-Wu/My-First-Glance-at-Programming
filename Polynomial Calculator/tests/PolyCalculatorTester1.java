import java.time.Duration;
import java.time.Instant;
import java.math.BigInteger;


class PolyCalculatorTester1
{
   public static void testAddTerm1()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();

      pStudent.addTerm(new Term(1, new BigInteger("1")));
      pExpected.addTermLast(new Term(1, new BigInteger("1")));

      System.out.print("addTerm() test #1: adding a single term...");
      if (pStudent.size() != 1 || pExpected.size() != 1 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testAddTerm2()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
   
      pStudent.addTerm(new Term(4, new BigInteger("5")));
      pStudent.addTerm(new Term(3, new BigInteger("4")));
      pStudent.addTerm(new Term(2, new BigInteger("3")));
      pStudent.addTerm(new Term(1, new BigInteger("2")));
      pStudent.addTerm(new Term(0, new BigInteger("1")));
   
      pExpected.addTermLast(new Term(4, new BigInteger("5")));
      pExpected.addTermLast(new Term(3, new BigInteger("4")));
      pExpected.addTermLast(new Term(2, new BigInteger("3")));
      pExpected.addTermLast(new Term(1, new BigInteger("2")));
      pExpected.addTermLast(new Term(0, new BigInteger("1")));
   
      System.out.print("addTerm() test #2: adding multiple terms in descending order...");
      if (pStudent.size() != 5 || pExpected.size() != 5 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testAddTerm3()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();

      pStudent.addTerm(new Term(0, new BigInteger("1")));
      pStudent.addTerm(new Term(1, new BigInteger("1")));
      pStudent.addTerm(new Term(2, new BigInteger("1")));
      pStudent.addTerm(new Term(3, new BigInteger("1")));
      pStudent.addTerm(new Term(4, new BigInteger("1")));

      pExpected.addTermLast(new Term(4, new BigInteger("1")));
      pExpected.addTermLast(new Term(3, new BigInteger("1")));
      pExpected.addTermLast(new Term(2, new BigInteger("1")));
      pExpected.addTermLast(new Term(1, new BigInteger("1")));
      pExpected.addTermLast(new Term(0, new BigInteger("1")));

      System.out.print("addTerm() test #3: adding multiple terms in ascending order...");
      if (pStudent.size() != 5 || pExpected.size() != 5 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testAddTerm4()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();

      pStudent.addTerm(new Term(4, new BigInteger("4")));
      pStudent.addTerm(new Term(3, new BigInteger("3")));
      pStudent.addTerm(new Term(2, new BigInteger("2")));
      pStudent.addTerm(new Term(1, new BigInteger("1")));
      pStudent.addTerm(new Term(2, new BigInteger("-1")));

      pExpected.addTermLast(new Term(4, new BigInteger("4")));
      pExpected.addTermLast(new Term(3, new BigInteger("3")));
      pExpected.addTermLast(new Term(2, new BigInteger("1")));
      pExpected.addTermLast(new Term(1, new BigInteger("1")));

      System.out.print("addTerm() test #4: combining terms of the same exponent...");
      if (pStudent.size() != 4 || pExpected.size() != 4 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testAddTerm5()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();

      pStudent.addTerm(new Term(11, new BigInteger("4")));
      pStudent.addTerm(new Term(3, new BigInteger("3")));
      pStudent.addTerm(new Term(7, new BigInteger("7")));
      pStudent.addTerm(new Term(1, new BigInteger("1")));
      pStudent.addTerm(new Term(1, new BigInteger("-1")));
      
      pExpected.addTermLast(new Term(11, new BigInteger("4")));

      pExpected.addTermLast(new Term(7, new BigInteger("7")));
      pExpected.addTermLast(new Term(3, new BigInteger("3")));
      

      System.out.print("addTerm() test #4: combining terms of the same exponent while coefficient add up to 0...");
      if (pStudent.size() != 3 || pExpected.size() != 3 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
      
   }
   
   public static void testAddpoly1()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();
      
      pStudent = Polynomial.add(p1, p2);

      System.out.print("addpoly() test #1: add two empty polynomial...");
      if (pStudent.size() != 0 || pExpected.size() != 0 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testAddpoly2()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();
      
      
      p1.addTermLast(new Term(7, new BigInteger("-8")));
      p1.addTermLast(new Term(5, new BigInteger("1")));
      p1.addTermLast(new Term(3, new BigInteger("2")));
      p1.addTermLast(new Term(2, new BigInteger("1")));
      p1.addTermLast(new Term(1, new BigInteger("1")));
      
      p2.addTermLast(new Term(6, new BigInteger("4")));
      p2.addTermLast(new Term(4, new BigInteger("3")));
      p2.addTermLast(new Term(3, new BigInteger("-2")));
      p2.addTermLast(new Term(0, new BigInteger("2")));
      
      pStudent = Polynomial.add(p1, p2);
      
      pExpected.addTermLast(new Term(7, new BigInteger("-8")));
      pExpected.addTermLast(new Term(6, new BigInteger("4")));
      pExpected.addTermLast(new Term(5, new BigInteger("1")));
      pExpected.addTermLast(new Term(4, new BigInteger("3")));
      pExpected.addTermLast(new Term(2, new BigInteger("1")));
      pExpected.addTermLast(new Term(1, new BigInteger("1")));
      pExpected.addTermLast(new Term(0, new BigInteger("2")));
      

      System.out.print("addpoly() test #2: add two polynomial...");
      if (!pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
         System.out.println(p1);
         System.out.println(p2);
         System.out.println(pStudent);
         System.out.println(pExpected);
      } else {
         System.out.println("Passed.");
      }
      
   }
   public static void testAddpoly3()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();

      p1.addTermLast(new Term(7, new BigInteger("-8")));
      p1.addTermLast(new Term(5, new BigInteger("1")));
      p1.addTermLast(new Term(3, new BigInteger("2")));
      p1.addTermLast(new Term(2, new BigInteger("1")));
      p1.addTermLast(new Term(1, new BigInteger("1")));
      
      pStudent = Polynomial.add(p2, p1);

      pExpected.addTermLast(new Term(7, new BigInteger("-8")));
      pExpected.addTermLast(new Term(5, new BigInteger("1")));
      pExpected.addTermLast(new Term(3, new BigInteger("2")));
      pExpected.addTermLast(new Term(2, new BigInteger("1")));
      pExpected.addTermLast(new Term(1, new BigInteger("1")));

      System.out.print("addpoly() test #3: add two polynomial while one is empty...");
      if (!pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
         System.out.println(p1);
         System.out.println(p2);
         System.out.println(pStudent);
         System.out.println(pExpected);
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testAddpoly4()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();


      p1.addTerm(new Term(2, new BigInteger("4")));

      

      p2.addTerm(new Term(2, new BigInteger("-3")));
 
      
      pStudent = Polynomial.add(p1, p2);


      pExpected.addTermLast(new Term(2, new BigInteger("1")));


      System.out.print("addpoly() test #4: add two polynomial....");
      if (pStudent.size() != 1 || pExpected.size() != 1 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   
   public static void testmulpoly1()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();
      
      p1.addTerm(new Term(4, new BigInteger("4")));
      p1.addTerm(new Term(3, new BigInteger("3")));
      p1.addTerm(new Term(2, new BigInteger("2")));
      p1.addTerm(new Term(1, new BigInteger("1")));
      p1.addTerm(new Term(2, new BigInteger("-1")));
      
      pStudent = Polynomial.multiply(p1, p2);

      System.out.print("mulpoly() test #1: multiply two polynomial while one is empty...");
      if (pStudent.size() != 0 || pExpected.size() != 0 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testmulpoly2()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();

      p1.addTerm(new Term(3, new BigInteger("5")));
      p1.addTerm(new Term(3, new BigInteger("-3")));
      p1.addTerm(new Term(2, new BigInteger("2")));
      p1.addTerm(new Term(1, new BigInteger("1")));
      p1.addTerm(new Term(2, new BigInteger("-1")));
      p1.addTerm(new Term(0, new BigInteger("3")));
      
      p2.addTerm(new Term(6, new BigInteger("4")));
      p2.addTerm(new Term(4, new BigInteger("3")));
      p2.addTerm(new Term(0, new BigInteger("2")));
      p2.addTerm(new Term(3, new BigInteger("-2")));
      p2.addTerm(new Term(5, new BigInteger("-1")));

      
      pStudent = Polynomial.multiply(p1, p2);

      pExpected.addTermLast(new Term(9, new BigInteger("8")));
      pExpected.addTermLast(new Term(8, new BigInteger("2")));
      pExpected.addTermLast(new Term(7, new BigInteger("9")));
      pExpected.addTermLast(new Term(6, new BigInteger("10")));
      pExpected.addTermLast(new Term(5, new BigInteger("-2")));
      pExpected.addTermLast(new Term(4, new BigInteger("7")));
      pExpected.addTermLast(new Term(3, new BigInteger("-2")));
      pExpected.addTermLast(new Term(2, new BigInteger("2")));
      pExpected.addTermLast(new Term(1, new BigInteger("2")));
      pExpected.addTermLast(new Term(0, new BigInteger("6")));
      

      System.out.print("addpoly() test #2: multiply two polynomial...");
      if (!pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
      //System.out.println(p1);
      //System.out.println(p2);
      //System.out.println(pStudent);
      //System.out.println(pExpected);
   }
   public static void testmulpoly3()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();

      p1.addTerm(new Term(4, new BigInteger("4")));
      p1.addTerm(new Term(3, new BigInteger("3")));
      p1.addTerm(new Term(2, new BigInteger("2")));
      p1.addTerm(new Term(1, new BigInteger("1")));
      p1.addTerm(new Term(2, new BigInteger("-1")));
      
      p2.addTerm(new Term(4, new BigInteger("4")));
      p2.addTerm(new Term(3, new BigInteger("3")));
      p2.addTerm(new Term(2, new BigInteger("2")));
      p2.addTerm(new Term(1, new BigInteger("1")));
      p2.addTerm(new Term(2, new BigInteger("-1")));
      
      pStudent = Polynomial.add(p1, p2);

      pExpected.addTermLast(new Term(4, new BigInteger("4")));
      pExpected.addTermLast(new Term(3, new BigInteger("3")));
      pExpected.addTermLast(new Term(2, new BigInteger("1")));
      pExpected.addTermLast(new Term(1, new BigInteger("1")));

      System.out.print("addpoly() test #3: add two polynomial...");
      if (pStudent.size() != 4 || pExpected.size() != 4 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
      //System.out.println(p1);
      //System.out.println(p2);
      //System.out.println(pStudent);
      //System.out.println(pExpected);
   }
   public static void testmulpoly4()
   {
      Polynomial pStudent = new Polynomial();
      Polynomial pExpected = new Polynomial();
      Polynomial p1 = new Polynomial();
      Polynomial p2 = new Polynomial();

      p1.addTerm(new Term(4, new BigInteger("4")));
      p1.addTerm(new Term(3, new BigInteger("3")));
      p1.addTerm(new Term(2, new BigInteger("2")));
      p1.addTerm(new Term(1, new BigInteger("1")));
      p1.addTerm(new Term(2, new BigInteger("-1")));
      
      p2.addTerm(new Term(4, new BigInteger("4")));
      p2.addTerm(new Term(3, new BigInteger("3")));
      p2.addTerm(new Term(2, new BigInteger("2")));
      p2.addTerm(new Term(1, new BigInteger("1")));
      p2.addTerm(new Term(2, new BigInteger("-1")));
      
      pStudent = Polynomial.add(p1, p2);

      pExpected.addTermLast(new Term(4, new BigInteger("4")));
      pExpected.addTermLast(new Term(3, new BigInteger("3")));
      pExpected.addTermLast(new Term(2, new BigInteger("1")));
      pExpected.addTermLast(new Term(1, new BigInteger("1")));

      System.out.print("addpoly() test #4: add two polynomial....");
      if (pStudent.size() != 4 || pExpected.size() != 4 || !pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   
   public static void testdeepclone1()
   {
      Polynomial p1 = new Polynomial();
      
      Polynomial pExpected = new Polynomial();
   
      Polynomial pStudent = p1.deepClone();
   
      System.out.print("deepclonetest() test #1: deepclone an empty polynomial...");
      if (!pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testdeepclone2()
   {
      Polynomial p1 = new Polynomial();
      p1.addTerm(new Term(4, new BigInteger("4")));
      p1.addTerm(new Term(3, new BigInteger("3")));
      p1.addTerm(new Term(2, new BigInteger("2")));
      p1.addTerm(new Term(1, new BigInteger("1")));
      p1.addTerm(new Term(2, new BigInteger("-1")));
      Polynomial pExpected = new Polynomial();
      pExpected.addTerm(new Term(4, new BigInteger("4")));
      pExpected.addTerm(new Term(3, new BigInteger("3")));
      pExpected.addTerm(new Term(2, new BigInteger("1")));
      pExpected.addTerm(new Term(1, new BigInteger("1")));
     
   
      Polynomial pStudent = p1.deepClone();
   
      System.out.print("deepclonetest() test #2: deepclone an normal polynomial...");
      if (!pStudent.isDeepClone(pExpected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
  
   public static void testMultiplyTerm1()
   {
      Polynomial actual = new Polynomial();
      Polynomial expected = new Polynomial();

      actual.addTermLast(new Term(1, new BigInteger("2")));
      expected.addTermLast(new Term(1, new BigInteger("1")));

      expected.multiplyTerm(new Term(0, new BigInteger("2")));

      System.out.print("multiplyTerm() test #1: multiplying a single term by a constant...");
      if (actual.size() != 1 || expected.size() != 1 || !actual.isDeepClone(expected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testMultiplyTerm2()
   {
      Polynomial actual = new Polynomial();
      Polynomial expected = new Polynomial();

      actual.addTermLast(new Term(3, new BigInteger("4")));
      expected.addTermLast(new Term(1, new BigInteger("2")));

      expected.multiplyTerm(new Term(2, new BigInteger("2")));

      System.out.print("multiplyTerm() test #2: multiplying a single term by another single term...");
      if (actual.size() != 1 || expected.size() != 1 || !actual.isDeepClone(expected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testMultiplyTerm3()
   {
      Polynomial actual = new Polynomial();
      Polynomial expected = new Polynomial();

      actual.addTermLast(new Term(3, new BigInteger("8")));
      actual.addTermLast(new Term(2, new BigInteger("2")));
      actual.addTermLast(new Term(0, new BigInteger("4")));

      expected.addTermLast(new Term(3, new BigInteger("4")));
      expected.addTermLast(new Term(2, new BigInteger("1")));
      expected.addTermLast(new Term(0, new BigInteger("2")));

      expected.multiplyTerm(new Term(0, new BigInteger("2")));

      System.out.print("multiplyTerm() test #3: multiplying many terms by a constant...");
      if (actual.size() != 3 || expected.size() != 3 || !actual.isDeepClone(expected)) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   public static void testMultiplyTerm4()
   {
      Polynomial actual = new Polynomial();
      Polynomial expected = new Polynomial();

      actual.addTermLast(new Term(3, new BigInteger("6")));
      actual.addTermLast(new Term(2, new BigInteger("2")));
      actual.addTermLast(new Term(1, new BigInteger("4")));

      expected.addTermLast(new Term(2, new BigInteger("3")));
      expected.addTermLast(new Term(1, new BigInteger("1")));
      expected.addTermLast(new Term(0, new BigInteger("2")));

      expected.multiplyTerm(new Term(1, new BigInteger("2")));

      System.out.print("multiplyTerm() test #4: multiplying many terms by a non-constant term...");
      if (actual.size() != 3 || expected.size() != 3 || !actual.isDeepClone(expected)) {
          System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   } 
   public static void stresstestMultiplyTerm()
   {
      int nTerms = 100000;
      Polynomial p = new Polynomial();

      for (int i=nTerms; i>=0; i--) {
         p.addTermLast(new Term(i, new BigInteger(Integer.toString(i+4))));
      }

      System.out.print( "Starting stress test for multiplyTerm(), expecting O(n)..." );

      Instant start = Instant.now();
      p.multiplyTerm(new Term(2, new BigInteger("3")));
      Instant finish = Instant.now();

      long executionTime = Duration.between(start,finish).toMillis();
      
      if (executionTime > 200) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
   }
   
   public static void testEval1()
   {
      int nTerms = 30;
      Polynomial p1 = new Polynomial();

      p1.addTermLast( new Term( 0,new BigInteger( "465" ) ) );

      System.out.print( "eval() test #1: polynomial of stricly zero-value exponent..." );
      String result = p1.eval( new BigInteger( "1" ) ).toString( 10 );
      String result2 = p1.eval( new BigInteger( "0" ) ).toString( 10 );
      String expected = "465";
      
      if( !result.equals( expected ) || !result2.equals( expected ) ) {
         System.out.println( "Failed." );
      } else {
         System.out.println( "Passed." );
      }
      
   }
   public static void testEval2()
   {
      int nTerms = 10;
      Polynomial p1 = new Polynomial();

      for( int i = nTerms - 1; i >= 0; i-- )
          p1.addTermLast( new Term( i,new BigInteger( Integer.toString( i + 1 ) ) ) );

      System.out.print( "eval() test #2: descending order of coefficients and exponents..." );
      String result = p1.eval( new BigInteger( "2" ) ).toString( 10 );
      String expected = "9217";

      if ( !result.equals( expected ) ) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed.");
      }
      
   }
   public static void testEval3()
   {
      Polynomial p1 = new Polynomial();

      p1.addTermLast( new Term( 12,new BigInteger( Integer.toString( 1 ) ) ) );
      p1.addTermLast (new Term( 3,new BigInteger( Integer.toString( -4 ) ) ) );
      p1.addTermLast( new Term( 2,new BigInteger( Integer.toString( 2 ) ) ) );
      p1.addTermLast( new Term( 1,new BigInteger( Integer.toString( -1 ) ) ) );

      System.out.print( "eval() test #3: random non-iterative coefficients and exponents..." );
      String result = p1.eval( new BigInteger( "3" ) ).toString( 10 ) ;
      
      String expected = "531348";

      if( !result.equals( expected ) ) {
         System.out.println( "Failed." );
      } else {
         System.out.println( "Passed." );
      }
      
   }     
   public static void testEval4()
   {
      Polynomial p1 = new Polynomial();

      p1.addTermLast( new Term( 11,new BigInteger( Integer.toString( 1 ) ) ) );
      p1.addTermLast (new Term( 3,new BigInteger( Integer.toString( -4 ) ) ) );
      p1.addTermLast( new Term( 2,new BigInteger( Integer.toString( 2 ) ) ) );
      p1.addTermLast( new Term( 1,new BigInteger( Integer.toString( -1 ) ) ) );

      System.out.print( "eval() test #4: evaluate on negative number..." );
      String result = p1.eval( new BigInteger( "-2" ) ).toString( 10 ) ;
      String expected = "-2006";

      if( !result.equals( expected ) ) {
         System.out.println( "Failed." );
      } else {
         System.out.println( "Passed." );
      }
      
   }
   
   public static void stresstestEval1()
   {
      int nTerms = 50000;
      Polynomial p1 = new Polynomial();

      for (int i = nTerms - 1; i >= 0; i-- )
          p1.addTermLast( new Term( i,new BigInteger( Integer.toString( i + 1 ) ) ) );

      System.out.print( "Starting stress test for eval() #1, expecting O(n)..." );

      Instant start = Instant.now();
      String result = p1.eval( new BigInteger( "1" ) ).toString( 10 ) ;
      Instant finish = Instant.now();

      long executionTime = Duration.between( start,finish ).toMillis();
      String expected = "1250025000";

      if ( executionTime > 500 ) {
         System.out.println("Failed.");
      } else if ( !result.equals( expected ) ) {
         System.out.println("Failed.");
      } else {
         System.out.println("Passed");
      }
   }  
   public static void stresstestEval2()
   {
      int nTerms = 5000;
      Polynomial p1 = new Polynomial();

      for (int i = nTerms - 1; i >= 0; i-= 10 )
          p1.addTermLast( new Term( i,new BigInteger( Integer.toString( i + 1 ) ) ) );

      System.out.print( "Starting stress test for eval() #2, expecting O(n)..." );

      Instant start = Instant.now();
      String result = p1.eval( new BigInteger( "2222222222222" ) ).toString( 10 ) ;
      Instant finish = Instant.now();
      
      long executionTime = Duration.between( start,finish ).toMillis();
      String expected = "1948110742426236235010609385816875382306054986429464522204758235199991033545861072462413023922381491954431783230930785091795388304201097850546537392397320971663988650738116368380559787788077743605576670555783787117504175967453976507221488777117768187099813509300130651284347848197319875862877459108964082883865992584860030407904865380003324614962515664451330497343352915885044885528059143436639392371900971461571331390314233281811879697005626744463794863819583212273729565805023378262887579314107500045166377049997686759594474351736285906048706621298687691876133007699441524138433793336066895820928881899626543223511180912667207415646352958975963621975822038437551187617803152676434481927099870497346668193870553066290684227262577835017304819443368100313160429408412578343701560711152020346228752389137291703755129712958347509690158392081991811595746277173432757597808672162799037635786316990034073455027012280181373843960341413808417100688586474575848181841281368857505042863723892895422359978012899296637796036692038279015269418029392290723528478626928592743734550127560679028516007429008984035031885754464214282673104679584238760625747087987906575684733610721623549148018726369568968372618596434181992521816696466799939724625227527230218478974066743943360219047592608237903833820970972963996375516802802003900937127097749488237614182417747056387130350549917011495007011265009920259667388085119949577427866060616280694254122642337806438250641627219068668031428895079781986827664171610794868447326155241730876270260936676052062951992844479995229561846027647825123818760524998590672200077262457595627870500264911056567071743604210853200920138391864080355383224265489341859179124527140437497585735513352584362621253368825017048960357810141422474505294380364318411233144697944990219651648346918113028284727916224864318986663559087578990907657698066089322269225657727766649741399387243152081516769137430869472121321097506527960054951517744866266663456213670428508271660737006247137212062724556465257518821118805944595631261168460222733688530297261533477840834424436069692424298123491256041470704535661733914478513097362823606682076103047887415480009794501174814374520204737374465097561300112917155001054523912446449472148629633218395602904773146456724583598255773199664926726314614674481616931554381229241144803461194456250887230391175344378076522354144311277158718484310666592055278721989746179727894655565308978722350815432602793042246885104501559175060809227277437738031889037473336980965644043976022385845035485794730277191958530077968138515021146797326468673846986355258669576874652022735714027449736732490725302885353127182557485850052247391113272960109634759084727943535639526728519926515464922241449611801966043220667760622264999747281816499840754172307504248080924415290407804951007861466212686044483482875527180286780652920032461585329060777070747029826555998257188160155630966122514154125029107630932228072217026114845793708860350611170412106122978812168138380065995258164834474845244091038317161426494806051255425049465947767220978564233388366028502966368475449745768155896188506509058726622523428104444502803428805278408552669668645540388074811612294624102012798941449958457827039443320492522314015238421145738162452937902425535325171816874302791266402694354887567904878698480091333437050774143430953003187806239748051957267096822094143832413637846374662127431171809221526321898818320724834806204098516407560326743084128987557792308437682096357542445075139924808038249973389372124440821341059036412107010124270274401339818999461958183975073365796352670772602234263965028367127301091539998462922334815590210131427994890565581020656839121916065432879727333596875881178500879408960613012399776558356189278475991954584741520684781006941552381849243835580048357974123940292625501644511728651747631466490334240051559975307661769192980200340493926772890446343374209555300501854984524867233653004123364630214645975649139484405765558333722577704078962405237122542105391080578327435915656989682386205519421491202425643892335152949311851184521348741087101976045934405783874690547251206408997763013506848560182935412826502538200312391147407425094730856541330579950695975564192214720523469407154808501129971538928904701351565855678883232452780704405277211233897500842472846963765184742395886090836246435027896237963682116973918404132612798392393097306371318735816861603384681296928547169161088183039474431357001348149363143857865200641491623984692923365477848860480346731314727388121708629792715208139625370163568396439483665785129960088815495306966044891566598756170079792789263197750826932190282969031340391133398901564091648777428703065544870371528191142656779085612748815114079327833863173640977776569273845241576390193698223127831431584499083258225624520044322448592373477362332916091250075585193356511257273295406220359540382999351186739336125355807633682433955000415260381427572419087227329224066915297286461214538332962290068919855836626668231069083873681832703713583964503580938336191382482195219077441550758285908008070552369307941580133053882927869598478366673292867990549315224370513859495565224619851814815893185637624407180829800617106148905663524252670212006562898240134165907022468373462475605544640317604046753332362854999642675744040089832997223785490197917972956500563026842414771955822368075847137949026428372575641475101821787255201730424084951855140490397328954394325532457633857380437279591138408678561249434297856189344024546431167572281938148592074808498361660195855611344208222955292298031025852639159438775120569225555134584579706613734837272331585310886993506105411974900449814322352088567146524515592958032865967218981158213293937181530306155908006867662661714685854632175985483448366883919419241666196344849526348795321342083547828643079647508291184559422375116480295384256073840053326352252989934796986956585531573764421021168025953260158315521274713542510762477670729927463547137410952284843174891033743871953210464887206715291248737207075231087071766521016722183983538462966737905025746708312212948259074942624047742713798831290670997425608001706352116024840135978445730931027450299171866942910836213718478535166038968269618294265814232857489112037573674042853311526109994420382703486298997373976548913665415685766028532508900969890718467415679741655095962253506889786121407128827377033295841981801098118408465519000370037063366625996347546440529713317325844510664776600147448124609925232193296716697449606885865750902770001059968723741104229454270746230459213687624749227646352418014974880801883548579618228221201462689855610227421254795590750052903456201973924113709563622587159782004421450269676344314517189407162596816843044712429215666940467282993094812056747717068046055841375398971157423078063561973879600018486997090481369761113288139675028519614363477693824273802653102786069352817020490351110734514326953140554408283503951278133126195667383484795986295256528095834694327823413032492862795232910735648221664752314890457135407855294463708674184646404383929195145694246202271204987938657744925032920357269668720971722553709487316042833315288512600521011188080797467581343222727773820707652401662985191682611318280547089887583091647909748717770469502270465710636402334192572532921694139992396766086147177492997921304493684902678478982275544295019679059382400800789075850944441055635350398450219104398348867725602662338293420540203287629754484118031944080347199021605010707590599619318363382656424450486901059312191930387438975139895166205995111540112970723509832390406939897447852658567754064678692092191538751741132883747402119259009809003488382097986362322544695841936871989321108165469610849024164736502186191095457475210439565242694060446302452292848694424248156767584760588613910406224493970535636454035489628034310126452314974419438523346458105101958636769589295474144960021648166037000061552521348608134610734072554542467051925637076373054827559028192565087415505974454649106041929747862535012479287905210119249456753009348313490098685040519434706986243728075249165524412169342702688086880380956439457292755980933381715181599446757031397994026520342358337254014943318485469083254198304008878592463242340417839082804485317771099621731856446369245966850980214506688514075996504237344187268698408691000275222728878514743360076819013512338894678923541704489309425249784875426052894791955614510541405020867488435444775148977628626610978758098049168696910714671420833979752510133876892596466054424670635833287451495731780145388385880696740163142120395461687950305201766234517217726598156539054152543232100978506713700240880335370278384993767603839980196122596514034918518202393195497680903766609680922063262692638242889060464645740060072164000038224632423673578346724414674988436915857623752206405823604919071172827156150015119597353222458939744175764251759423594613121354219267076978587232924598158863974139461781228697327712933664554515226441988451537791695648546771587361551155121341738305546000297667509892845001172147838143856012926402783902021281609420560987056563667861764018695422465053650927851228268302943797009666653246651499117204067104909243526458862468498256304545260007111765706566036771531184496051758118019744071448941543070820790923954133595191815455960413744669794586166454641040477902670609481032806591600561701012067787972921140007543122862383771384800958940281100884140794279288677904276390636607724340329979046728302834938489202886658854618617437893994232069321402537626156222155342416464866641656486147689158067187287572217418887341766806042829577896745764604828569345140274031287004373797735480327944235261037771863495611876365893731829265068213122439488245237529705924897911377580911071103964312852613854044898516145834941013599536414234970983646285315135333965649312851975763077428818311264140240985622985746976863981554545856651754246347641770417961572059476863234141486130945677428399446270497897295591052397770575002924388759174630295795024458002551762214061389777233133363979413713261834122046476929636615297506790780309836776237544479113719283301020482960900955330334943271453946063675389981172915573108599703449354441913423597754717136745712424390597420471859928282920327273037881717698766195913358631467790242215085760706862920410314253848988062538618345136831690604602347369036937288009534696097310300915560712094248876676664369183190394854102035256169351109218192218679821711847990294998718130788354630472866683546799727491073798404514281698066861747623514068617181343956971310608447493992760974683441288508823880178291825660477371996739700434442598066876374844118053355016866991277295714271520236831978828834914540740096672226796053632599747842111617154340279454177216766162461386316734647342566831023253397765823422744675477127160156899141165185762618630301046605630286824551467903225837228996163356435241036936310841442442708914425173696108707486952427220648962296232045412528574382459696786317200924664686864526890706620160473655847740141411007664088569351239380473784454518010969867527455289181947793140472564411002052214176374688353832805069373670247870860533582130237479325414418637750302353049457477468979027301520548796178466450248290221364059165382812609300325591431866144063200751786913249491531215244786460876514062430457391645112290388113174312319734844655478137788276454947313960206872019621945233657978341780433987167971487034732076893121496061774078136119827594237548618373900120816920762914668547418810705069894416376090796988885114004353351998771879294592050647826419022547872739689614778815165609493562063941280420919319447750619653983435637049418462276315328314904056768512809673925226901209270229950790359830863848211665483673036128122186854071480995986625894385570900056721413772573345721351077031856309747144210304341253263666413402156564924059862827550345682244233760399314023853674405791264780790758196267018224852263055285971975956299294377577263578688692831972032441904110281712731737942957048476619538357839783334542301968967482402813387669900217269227774114303786511167561631150550078999122551546623754090701978217726735106064878831038967382514085254571334816154239515157982024776596099450618394896586357039778370686216341911444203694347879333727163789136085028128532586482436969383158008942675413617771376696346981984929402439664864161392888598067079887424237320540375397542029797557088785142656005052076820257794732821838379352760408939285558389891406648848062737564298207565448733490511545433498739064889787603601079610327168060571592389942701075254858926266809209309598901823587033218163110923904117805003999511356450164095481799898129958585287757625990540972973423931353821753457644766930204883225254706537945878435210928216919593957815008258782489101760296235159923940255659409272326551090048936447948256360748341263283705136133258791864394696935649704834809757690353521996242509363505106822616873530765330004481269159245495657652187838726963975058630063184267329877254495432093634889168164682119242690611123799599241797301207877146491383886214446195528643284303754950590982283870944274040981376830721672010397202448823961054817977679863730939606839872768176301436234935759404749555243240448174966951929417494939548310478504402276211070394630871188444668370163684466747824736854707985957537792861361380827435540997156615078626905586753898951548707569028084881003441561058246823514336466136455477225250483380967799615456360522743315584378208913931651652296641965360617244603279278134791171671810424615526160915197042093632414044410686988493780855206633806467840363921283315515657156001585151116789897527945982602934369749807598815214909163359995442534471460173821526373501437971939116100183076307768776449176676347181339243614037094149853245548146425231355338690707655563396270658546021794209684264114175410278529665716027681461481066531208620062580962168877089166447713088945269004001497476751938590152686949665561866054750405683972933173487881595406802729281017734577395293228876304840327130517843112731615002984594577705018309915033091169773598386981506392434740056609673441172430018915086412841108959768344400973186280379660631085328026027592408094509030130613503763725968272262847044240474905873962090955473943552311772459279745039769647389499795608595210467509975790504809675378173231473247764546605979092936914636774997406094639111767763261526236187333677187615862373967970434860308870308407222864667454916499011154891289243142091921662872871462158361061919290377390619317817803231933450215509198439643544994582684348772319554454728205088483828913648163241358309291881142477668714465098379402255723718006789828077727072067861961069975929372729295756258230620634406978361665497125469919995759225845383393060767847084150376981792477938350473197920939451218491074210661004001152751344620280849800827515176862582667783483852629063782305868178423410171113196684362968438155363836621927002581322523338196895800448820304514622453621935309100564532958834476669069267017985994920504520397303093096258499600790022866255483130069807154017662594291083174171999867728261747731734391908284227150123638801313049777662500641692757883601524462237673856519990341046734727791032819751646945504367535173879010970359306602264277284775381577399131885554522749330290374072499105072702317222310063703856366125503008969273547979303264183279713279792227339982046340369964156289746112232681230014048903468578271750958324987482512657750886378643847183978514037906855634011518371006146224799698628992616051890389472525501329160810303065637785997379282726947456674231385588593124501979275785866211952947877676275298946472924311161285358787466599485056789922912979289444544525656759511700487955923271741478307829247913525355275361235818576978781649994143347127379365300469329585292501086838444206086826183044003390332015902589343800401534902818819540538079022924225633735046086436849940957056028941017469359954307569069740449467913407066423187111030005265438482122422335376084513661714936584016056151631052521543315033163543337947441066476817075162519500341013380245476031058343441403795925008690736108557336913525677648852811162050281052059188661988423189062744476924618689706599404497716156782090077114957433742138169904181647272884006077575526823987749240949954102702926132358338196978192451575016041363644415191132200874970679354289831628009093045811116035920382933649038356007543355585789635215041149377663736323513814006954121003169623558638018910784849256294610647767028271682063775746817114155375853241565954067534839992825346680677523772037348246081262866764294894774220803358822190698156785242207416852331281004884800512386333518733763062655271059845082446654268475501790957574948822704082060982762609111263529446206056446679778449171586184921877675923112687663659977861074413545645950208351348370812574048110904245835419827791375274217326292959927517978639520582297546598588037420291968177441264995501705768326506786435272709791518228913177161632261227401615913609459204306298925358394336881944223353588354868115581477913765741318926437953579151064864588106222522281552365259426343055412307276921675981554793526996648633070666202637765699220387169309384455088245093674769282898902841170560732641289694206831023103693303172513651698802991465805641430024210243908857470359500369513344661200862018667970340513248198420862471730175979783412614739562722407472436828602859905798444316688914970059240892508852228426648841350581667650870383406370025364242802205311218775041634266972014544785849712321950288049755464789640206306953395352118963486117606764257171893150692798160705928436255340846859555242097513789256983537547895195643424248605030535686622271120841535913701664184997245410430390536712204645157572403709601723864741435333267109123803074956875731321234148773429689911022051597103160106821229322665747574459564316023488304185773798523478650031177390820757639698908700105482656463852583669378599166337912146242808212742352903545944976394830613601609183265896690018182959668922661954360502810173107662851259612664436834438944604299390290253649191111918559607406701214342283934739507197567530685470734366082629299278930578192209833366869294879851318940061977826792160347097811631392475623458491453837758173430845350819175220222929616394467005121147630205056815575588102291832706990953621673775827678018566790961434316062576829517983314867017795876237354662988343288889191456168798602943533311180008912146617055053078828195341482464788960650292562203698710157384272726919096906115243179902083223303634037666706168953722176460014431780721762360309275187701016228713079427981777953051697363741271916119393398790626259601732736579633067296719109191316513742575773727192338521138566984826324202783044891662140978375364750187202608480464559544750006002889012641437352682799243521537720725041978748958180674853194317789175750942383071511520752627709006145548443320596319839065333784413334170418249884021770268411917197800610992672037756564231364763994986877022683723363215052151581616891976657202692971954073961012269874053223147484090695548755600827327108210711420186397848367704537380227882307670570854965956589170293792577809320453807372736682284478544433482226890206402180708613941303172787098425388927332641140378156821194356920972892387084250018720943914910871856335776341249153081114938750086655845278465955311296163943358558912096492114658541439996071833351661339331011077370034482571531046965341804790253165883503893813007253041378994689569770457257815273705336074069089181585364109475513264576088897977141153741544875444865195404710670984515463655519496104252591026836511539581535306367140395611279289687860042202056740638019269752473579739376259995349166497653105248600904420369448691127061425665315696464849951560300019655228196620677178415783180871178602579735773872774908558504060157173670901788783971030370815138214507753471683466228783763308327569471865575307925611450267032918441138412091715633781005782656876413103187329637432538301353070241161064879611938008183602833995551381709874479582098586000893000455354337878990337969397954653226993991272619951261020675659341088648122590883686896667505695319421872067843452341432327047417501672129095085537262017544051186232110636465248588999105117581857927321408644983309179167917824178995787550188178952198952610644201199849564993101199957907480901407548525365513116167327803928204048565394733541152027189502861060189551595598738960347721043063233771518209370262463539142818772133215632253764859048527986823306771879048338792400729933925632269105568306618850906991808879181847245166237700493320072733447416857962105311357272530384993724944013305118454293939781029754627678085176946270445944459836533902182933037960283717547981732641281410216054285641198543346356376767085551719533899965883906467171054302953024802127345832350142727831081777907066169069654636687542449922091413810040275647149339878260455547741477186765547613949777057339505436810264069916993246752872529530309619513116601698272919237901184799130884804039848483867258649469132420390460599745669295043456513861549610778488813567175298967965185011025199981275745129221811005077411976846235986446318542900513101808462806867079758774515149342399917684484229813350620716230871373583625798563347014450139749529496470606093199307151859803568587576203657894488874914082829544440030081885381970957406223420504227806337905055468795011631803918808671997845536590492737029969880524723802700813743855019549564245360429250669812213236617220573859184332343620750435040447243532965408194959366287981612734470074293619845226900295540510196584658826377021603467987358929036483671061873969793177366758168479933577727553305784635406819010925407926393164813013814708767155667563415496951406753330244018282413316772009878867032383118001925495296390217401616169001548785828788539266120668383922749323388978000984139657082366260100212055239200228937793023816746531690876979856492807792523100615216622012411580437295443321118478780959126546853298097289608169175926973415772985565323702016609417424803863780156713482902267515462759167785339361318206684620211657789577799043463596388758645521197256813279055739800587660015177248179540944794173837025748122862670088205012084137677449917525289674725564130995829482788258217244776502580129006979533715629469550588295776313934868596554922172356059390988382340972956292902544376905592465969354796789115829301183717769665977658583144209875269635145380721085170584500356587332974678690285330261310300727834100707205159107092777327926875170800649228891883432227270613208857328615991114519296214977196182493669927272468321814558264883937656047349205739282029094146381104663290498148777781794609817847260433497216343028376034734436586335822999567799242232713826571994979995115420627733477923976187335937880838392521216929203361970533120231512887019877840078261246377617126145117478939523174461327657254294440355646766306878280890069056169154040089524997701448449716031810254523415713873631625582129915284203769320691575987105193737054055808416603785909966023939515886869744994819586806869975603258844125267553168835007479045687682061908439929208677333879736950611724405795892823429987653264757562064039455796309351177146487716236394923985036345963986145636974273777693438133899648049877120035708717661929928310812147974873947337305922108343308658249546612573553014464887950833470028660855123562005142454751229600832580560986001535886297623650943716959124817323251770909853836940781434413577069492371853122856876855214537525370195050506990055013927515924528434552243524704892827208372534234163963870330043078805854869064324677392301786810487017583568316608317209645802146417051146232905423495399227774079137850688809495804521824148981532726951569962677005977745722872585237755530844153483060738019875993355830710575890118914570546618099297116251393539739967608362088440107352778128729652768576730220472592747331209396266012178531704056015472455756463727907694647897359868792321049429520494459282836337001786999445403373930758820386269156478302369017801774112891765809084722570604151776846600721520208966399516999157101148872227674354046630461301783521301995745532379831202471453928952380235804972656101575469197800817634979972320787153622808973041725173230034476966889282630222432994990956536119175324716671003731684261129221002680139535916362690923853180855958281755221893856943286966660670584198427319511265979117260762586492270473146872274146822024264201054730938639015822624010243282897178919097455144459987070226317746092136869323267670512193058533436594532697531654968265761568614889754095827397987830482795606995100984768740376595580586745856553080834028592917079157081165480296150785363290311401087587728020271554701174717603424164220158742076038934057279937728507400048783863352430638417609429109923964535109921674658374464955120717492542020256076331728720957373317626204038970721174720289028969673647272431741690082927742098882702129890654194969267574617978292975154763063173691926018811003269829080491506325622893075003363586194193379617793000299686270043430879319723017687881739658216626980901895962965206396007466353571572890755239678730933659777928923923652198702076395852026891378825371400440450717456952019422958109404116531530531345322636378545346289873171278721273809071789591065609994350065157185902832038035449606597014681647264844953416602227820603425062971995240611107246796081971427718406745658450165628098141196412183686336653761151174532357152701104610616528227685149920157164298560120367060877133843296646195607436278800986483320972520654740215745467357242410405432673573284697350300437573276058725756973531383716966303678846512509116928328831548744322335056739258592566231517117522568518919741826449931613610002753617017842752553373605450837910215799071417679244333363588867262846789000594172340195635825461237351429883088353979929976202904458911876975575686132380350207383697825187850945501750931413452810053448596568190906984778360220383668203891389925887518551409679460764768879386581199795582130534333371824194602552877203358508570421878776131627347498907270907503481424887917049752202264480848266929445707120781024786164411171713815795695625042600461668238784669939472426136182870794583099812224967038364141391927077892955825948068102750481115136889177150831884959711166103311332889716379801978702289829286368410146034378078400953332632713042209239893994491273422613561550077078370924954933435616660341280518550789312593700574892723943995152059082262317976193987150772823374554340322071541612973508690308679250795379786657095332184782578330322593854826957166375278060197528021934741285719677680890556238168784437957281382681618123499368058686787908180793904782686078223011403066495714202864952461606358956600272689414004714172410357914995942510958819970088574713827045063900994496899408849061897222603409070720382491397401649802475326014256628250634659663895598975190315769042184594757737950427058530935125004648729711067412040323208306142753219018011658836406776049461404871116422298831974169223218541169063133121290366971474148828706780171511287348601023418599130438255154618905734013389018365843027684343000179373743457549079298950100645673482392871590454284154526770512917987609836186334207200833724856630599431981784355102248787187005248889433251843816239601050226663462494287407216880689740276696397722115104340270361717807597946066120886422416016014680885802595840408880958021978271160336576435694359846257534478584188373547951508932295665301130808185544469537582283737564152839380843658024484529387386707391847346493546569904990976291999597462419863897090361883597417717176555864646156499100413233688466743878646038646718189089874227429813304763656395460591659587197091418563237980918477224835654404344552095651107262142740651706699645078317957897716036864402013424486862904639411918197101310543676563074561411497670122367992062438732701985066721826530191517287973873692029556956425270081742050228589590195997280178014309561853021609024980631237046614602889807465095563771931559845416488157487172851482189473597978020825511657271688862341673934512153789778910257341520247623840976311854448973758107771946671219166673912468527148602537467917919278893936859626664167050134208941873480095011562122086519205350085511885469445895100315330858377161300456963157865029822705856666525572298491760585215371094798920934123159532843765579788019634006400601866364310381492116293603477883695788612957912053299051900245161634331781480054518123486353562021127694591969833362097833366138167757146491435251609432884452645888844715456336274994889246276471824648399653848587186602136582565633140547077866056663159466325754679649360391940148044522264566830137961920965118183570627014299720109804248230944869541089815341985975155775639595879459427256831995678078421901139943679063254193071509954921631643258658996235195003212503136292604576611448518607647016601986056424655436395791420588713116891426044287215224650795606366807783470031942235880052633768466296017709412128081635941165126789893693841089205416383861343105289237116508367358603355549138897731043961268593196748751019173955900991584877146926118190670994763754608949123248945375005220870432230946282778616420430939166537138619910443306744370687328026873293828080954987158091964760924210513914003157824934230255696357682557917531906912990729189457415050160731519771924530494783837983670290256804734508642101826680003526876887587690785649590269665135570280859290283611771533676017439620237194086017532293116287246666271073922060486526335267879752420694925547627109624072201333416667777891402196794644672276738176810123205354720335109798211383444115557102272431675485524436102747914874754047141795058081170044973466319212448223471040651805628676708789440315067676413664044037083867499476485053776899006769003706923920291835359952531223574049454268144815545170448092476569093595926597428787948629423656191238576843821948031290539672670373969699328772512315295952595232448133321180752142931531531264181282185112550955731462205894159195534110546676024366199622533285229948006448874611784491043733538166375747487538821667439425706719070059072327083431167857416760451824590215707560108294220550411103360331840693275053036714504097586852026435700730364088288551451363265740478134498249496892742116794866358724934224440059482865039844903173947416862808518533748346935086896235786027635809329270944992183652574146315886489919591109475959774883867595675471066670562248760127130612405723194769924416865882830022756342939872967406903390792195948871292535602825991637310110988239024145148161208947324141423945813796722404140245357999087361674245513175814877542937643202825718310568646662468970783921245743156716554345471033065562512149824608601075313242758540346765799988516874120549000912660133439901720663127403659761290974811023131161533597829150089152220137810127306741168099805189545114224577811913216733472190880059548880098581121700972837808604763889822465573322738845908575511373722037004640448635094151644662212850083866897593564224355839471125313761658005752698372689656626182357214866144324359737236832961596385482591998236900318742460321377703767496552219582081993765585022033758793986027719333425320882440505961670003889916097558806944473276974790446565734262654752738479130227253664521595928690638366486190004735219820612552719885384467653624182806820902962921673586350156447316054117157394746188852365849000759215206368724755072045435340495315984694612164477366127198904467435675343109557299144093521164797453872510394303049119155685426975135553436939561527508207946453442709243036349331939676153503553952371715642675544796719010472294241192522266960747883010538135398893290676989602048957417242977611504944004708886710126556049397809679894836018262908766146537003815055716626770266786886418238042250179297147404916458210139144949214064725772879932084726725883760454200715740908843324147762181788765739098055645393552217244870227164738659099831462817833037262178079943854924626486272716081521167669460051559414384700259277001888977077539831690543127993385029973326651968696473124028504491231761808493561152042092918862992858381976000237681432237078248313319576627564450357182415665495727414723712952301597245514463494708330030807232245449490977843229893216797318630300447261169175514253633076617427116717290938830910407791641827503697908642093979604908743521443016240678647356784248693033501345419270618455131400008205767868026117209363254544871380849692298571627315182276916575614827306662609840052007331222644406211776082847769570084551570455026169101276336843419464165370313651021332624659706823905646188553540313025361597855607247019700691668261947767570469345441272366423691416014562118325880418253861748991589859444514885971382158847106185384173027493320700305279333337970434711344181584203411651577564032388458175755125578203096964209522471679206164330146758593363823791008588885452435504162098572069652972348223813478879463172705404634116991167554786364161972436991767816870477605745140620340263958968048280200580661353379470101454283105108120996600190802137343288387266289294477223710496446749894177141104651422855768997468935887952853121728850882299100745704496649286574486914048591091976324589869939668543757795410998715818026367906066273533870358487208116705238670730235439562333976172726738987461986642011564149938315523232287557041278851994121864353490284350091885699717196678796089585561158387646285527562868645876532212420798826599486880595561528960530080937860228434301621785553486504747216825883754301629134464098057770374244523594960596009000728786150208950939256594400939000115898098138045314447098027451726678492559493658227971121528140278495027975348440618434371141486442954180782478617962694027027712972352699172819675195673383214438637868063093715535146888843982890909750421044978716949580971082058355150510437232430849202294342502405598208437670038025124292794174507732954905794546078889890275122050850573621376343613478609231003720916144424128583859998246495855750409089883957338477146681765254754531961197328707544329638612276953508662959673637951551960454433483896564636261437211897261608364479535024694856468792975392528008796364545928113837181629450024513033442384552612709111776252547814472369474779847875951218844116066283119593898365340034494021491534723764814688144080553546645311132719498427237481562192254118866107143976538779888750161558611258856716014520502454600312041185685792648774873333020047007815405356903138352565385979179727944289156987856117038073926534669605137841087014007641400291725676285279612481245776134001273562929673947366120023301237957598358182542042218578787702004887004006498806602337757892839428195805653322014073570563857586875238321510078121875600171777939944222637360383949107170689824427394139418326104551958058383734915156829007562804571323684581012657675779535249366571771766982250493932390972831663367302693180130885314422016979469283291402420073502717337955301020444346872695840298854249166916151038730413018305811925634547444071688789643300798322047543750490441276824031908973129923751997762067763415740213257663009840486418266819702348249990198615878646963409994414681333895657667579035320569429506215501621501491627636772277931533231612624511812990093138474213155313262560311171165888482880053974359242223688317046583485607677925478105515034539084645954796199579090172208628554744215549658093015093937665403092966230329637191204122799785544946211438137648005048727974895197615741854568733017286877379749751590713841976722144470691790950670708106048835052092091281090878430356605354149759723476563543592137138109258054714486851145252627255495234659138556509178140609713306592896259071218998671429451282861532908911240839363646429660343983259220296499654411796334781833126946556572227089205234564518830810813176621718608623293902419073928999677786523855404824453693222605486115718187249006549630244100216928991354866612976689110647674863613518284879066806288841943047414709358231244420642310370310051945581990509230195743885540254960966220087576158305806090700898726456256769289808426795057989442157356671567101431787866281513219823507607468368884296564176432455741923019398298943158195142577592459754947285746935390823366428656888018142446969675391774531270636598580459084108527356071545067828516904102933253689925081952273057256544639536876327045234751691409632301963069456418746520038966490706365920273098844623409328981904330926904501894472617418368387036532749225128586516585387502738393035483016507895116312830586814219832330740920638669720890916138870159518685497551285852011353606349331914590898790590736202061621593622319738615858747954623799536952646634576690809111625082085058334056576550981644951195981530440273791806932369034868118296391951853644234226763513637571194305842304740995300894874204414894468891587149315074736929012017240079422941037821964929894276960646523197013823228029345285801064453723691192949258576373171030359084009789295196329646309644961948953245393441781334419447143417861527818453802238344863535838401448507155258613825013880667602314148419006033966173104751024292140168525654946238735453225293333642677702530997169804124362642570953218559235667040515520955566309946160527647081678732510007364741161738059477445634730102023635356430094542400322815244002656043217725197156441826715252975739829662752853145645593976521803692790085882028620347247260555794476446329018985808831786052914187165297252733546446663904212672879813614759291251011770791621485530499620047333184644974610643709654665158778764940710123522046330730386091185072445443255278116798275438708447296879942663070963003101288764371398652785480049163737335989740374002990364344013920137005301358142188565450643204449616127705797210790964594206890956017281436588884786970911517187273590061331734640220887373510374041739570204410379241236733544866318856288375135520382260491265798868005972592871189130756024945189898097528840119972577761736777675895213862300263411433557848144363025684654432909418629044194764367205868468526581312057321162816805213240620716071896826162433818885193389115528190112833266313360992818741492405285629639614938628164637202552989583631734005796083227976622062404336277355813311995880206732262847837955636488972041701779865763643951499825725556987796568689349451510827295681229088917968173608885044745326042183532339714207777714573101914272492808549861765322557883144170752008954250277090929464670318659640325216442089183649890702508766585352622546980249268464597085774088109437957321338370957199752014727749491089131158682796346152358418589822373403728954467251023034437635211474218018959162703104873814412385431500369052542558984405684929214217148618768516258357319311595001456786344893306390927877057521652585416375502108368804908954052098584398900524870615838089403187863040196597329787562948625398231133717128419788949845357438074095858481936788661365014287156753850818976047334974779947984563947901329692221495908690180141171374585752426956699512966127868293272808372594085177043505095674092979767603201740574877257545610768593057606814141195096860728841348290611173347681873367312913588481040986386308220466084632103936186163238814392528777012466604306104421328089671572586029150251245862124015535105621759644722432093539116686758003223300032512057266488674645223039903188279685086495107559970913360655586767593444506210022334537184250421043125682435188239141948342991105918258402522920964677677868610543731906326809033192644984379511474187229476425349331576113632039427601177715407016049164060945883715537211934370692455341732807943282126539247694137414691088873732367886752851618816165177566041037998900373448073167537324179892141848640748967897556586341408789211016569300413529263095511917169341996055705468662068756478756983558082428333753107053522174698305697132983634762252527002111836876343880209256776324755894698821328263070529482289927164901483661121127529022964580068551668108230343193053848604802542177252307147317868060440449628530166773476082597340904687103451541283043989008224840643510239581123301221779451231357026880279519834866590693577743048967339667247124238449434166447145937409543001852221342457803561777314192969337277051789148348147840361309928574853031549593188853031347498821617952859514759213425255029654495646542716438284888877231845678378721089099644571241766909356026849203097886525039335730616868663349363919441333692231305109178685631064481929852634854584512609863272032343926290905783490452443859434578392871599814522310376861213439618399239177194520043001944833163924815836746356415698547553330448152939914747162357680708083739573188909485156536930876791356831976080396508227191160050090706470047817633558165605562658894532962556619054904631600979791515241498108845968262908346523996314158166779403310326973965326180995248204007451541015383337869059789284908736554546953801738650942083414654372229650269124856989754307893571216482794891442940388928652134281329867594914634917464739783499967706240888244522371440325315444415844530410972422673236116272023266942635636258947790696155153933780921930582312057230560517331115038150129704397596555408200586840064543244554522161936946307891781340141430525106352299368532672343410238407761539060583759421079163755459758669837719286170125959350078638450237130275975650872139990053963400120218811360952285091144604826366082557962218203499295748190585551822949918048725861682799413631830944473555484562076997137162885629308831898696911413501835773806580360230312970878618232535439767412401640344254253297712700138989204428857172547301290456800817334827496004696908702589122184822712629046392140454622285425692605090358281728870722708038648997092787420245216241408555969399425437144880243241932668624200520633862725549720991209899200892980690139167266953319191754570035309763849012490863512407864345479177188035219566289084622642655032627583418539523160047456639630577969096894901782337453739159257958343584284744773888554933067670155267074474963645993015003350065549041781968999365092875992723968952691012917941644184603852666957731394922706548172882057431209980363940468421012064821940898274523793831185212782317716594208504193881226599107428062335393125829667276314668507333541752114573941660137176887785326929328366351353108422278414868147414271027747339336370874926655937483828777343580201572859749282765369099584032046796929746790603821798013724108888719914421811673104110603951274252523304008303216526805310355408296719119348865256204771563433264574379348619145356679950239287039947458291999876843413783135099907361484352187550938257766338427978369330878744140833544775978964687933713882021081157677311715336135130748688885305306596098406205794706958590195655560107959563086596007267673044509989118823607311026848723933351663150905979618225008243457478676814386083863458540929893016819765443650983998054754881662455831851408069872864306602872755670310534592850002919657743797399371603098305377913973323664579400908091260143692491916112535004031520862106891122177013693240527133754358630620596581522689390640533675064370244812688945639148843526504771913891415126623216405906977843948877902673784860771729264301032002200004135290920116600070104198400363295036754451742524749807816712510600045302338711968504059675264909395696691039289217468913514132028045289915730640736732347116699382699004715794103654627231040505776784694008687344151195603596397257321098327668290010032489979243099789612764379952914001140753902551792389858264763556190341977096363194359578391927832766264599110302801264015854888290632188930449498249904081541148379934597681071581221410639378746424090514505578860785979763834970884094062869366462533343229450129123925054284889310755578347369332900263139131047433195394418346487940430942173933555802145943792866060996851982434581480709086538074817801419831339509061142236590705007773312146081540568467581845534551634401935038444278933298908909782418193051978735179949907824810390728800067266819473369380730123503308748691984898426457913361356948247067467742815870607571594135566431553305788406414336613177236545822582672165950442910692940453486111820222802976922481223637646171407936099396846952896986330535176655518437446217084201901689976714795838388838954395629199210364683810842261382475608474674073728046335762651144210323041330872123206617913289431463630918634113415074239546549350503411970091471137080853199347875622181284080835791606304503540666075190289324465364026518263429606028516533430862944743250640946624325360807855907278961829060940486568949634270082917366197890665867353653185687042388609341793054429813529236296074501856072723854078300839312893955688200894087139258990515919164279100180948121754602621152787462791234105162725395726272444394938434859827859581338664511732185022842319006973596900404879583061385250451985977280975398301530163570141034564079167109966826918413410881650807556214116281784177032655262140671002952967031508957323506362774958878298663231844515841174333225494674784919560242663037514943734885648293221194294998027229303385967318008085016651523493496223803825579231466342980246832101441261296845205552406586835657319041375897594255758465518645151702691612972774570272178446105249858582641117073006238030624438618431473071999687042091375864202372242203549731314814605907732330135425388246406369246206515565867039155745961896990490682983027000030906576625494255548520097943447601825557312261403163635400028227268322041506297790924790238810316957909761188625027388519483395751812651515265420115291103805353389266768219533610500247147644368977330138572839716587772019428641664948610003057965372938742115117101123739674481779373240476531536463685805320615037904412739526670356939043593721752194770716509223310633578960839056192646424116822497478271808237645237600476640277420529107059421453660853367708495430517813179208286320539316801557230999511202977999639563786216221857349541658207861357076069368410388895992900325615310964396852632923390886382219290959961721320708121128922390963407682452107290445048886456002967361341399510717348891981879064494174585500234236378401210158685441916583132457901836577971551594434665106940215616203540431635863406443110842395694802472736002095142748555357216107970461236112767768662381625130974467052378186508808032060772675975585683205969728060986741515327004418935929727325128027501143051309940679630137279312270985468313973388671809680923587893548382599667643933730361063730647757542204061944105041013756605337198898177180939728806153694244600356420563561258155502311268628550100814415966234734627077882952234884067189966625658595728399343558942104524148767449190623373001363131551045723340889181239901076618061405876743785154127082564294590979118268029940182132375781430302851675547820223153184430345470074982891129303541517906294260077421603988673606999492692669378385635036609104213994624694604653791320563617123797186155694969904823767635104166243020992380136301626166064795192125928000726886639764013044086626181386813525606872825670702055101950292419416664493766008520367841475154014379179753722350714924210701775225415421013282871708528067097721508045939231374994862216920573555919018150672364242814132079478450548506348655247447968909145288022174259547509429705788602517576127877639866030453749570198486131768362828136140096126638687268926464870882805984090661342816836648484205527962837029303323755432860961378271082468420882188666214742671943836129539852901307690105483470698045351526150937991026793552370884159390377357220457539866028916538294751781340256651860646788729499576878599562662718719561802628910985218292605599941383440037758353714515036552650364526000591615459526318108177120208587998641924270872240454483653283677724666429982320587651906844853484485167477896521291779524925925836301541546565181180753437096670473559236556014672605396578602416631105612201450490130299145990219307138294116168651821130896832594758875175306005364447417395848643331304259897149635663445183148502457509814076408523769948640514755180734327843394677653740180390147492606480598046567560818755407551871730727694276951508457360932641266686498296959702672244980874617201882287095192232633656195972506976928862786288709887898385841262420446690283880437996116629338435926979265217377703355948415639480763814625201600681792604991963271112384720750818008942041000801157663138102997265039525609493072588456085040073250469679104684681067703538883113584248147983158235489243383579580185122127682869208780337241671995150761036784013023492981235857298581816328314640546085845485663663931834508695971315477268389644992223695529502500616258850997996795872601464561310706683498587839899109787970042658049240231518571643441483595071723246269258863838442028776425730551329475763027729265619017587407102927230894724593683427687638834156510621060006909368662667590060764794643413957529442605203844589630139603395172324308567586327529555971036504214949199346384647631102297630474564354932895197472423246602319711534284129222876698112576353349526324929732014787233613621998914176730753397371352784992168939309109662495974500181511430487974870273388161383142935365153682173325432335008517585187957425563374755972192662523394732831665968673661146465583472382000683640358178257324804666632071241560799796951429335792177509501170431754923906885034015509389763745101799006041307786622403220258139672180794373419939827046733752094673386456271491376845402641798567892789757976681297134073041745367069896582606373853101814974848287974139642278830451592917201052251579694776575747686547099312645913464510757539062968105608693965004820249402400045640534806056829264465557890676529663275133758303914522378624743032104217041747623899527447534985919489198740007721900625691673494711257508827741256180328646917227886833775945146296051928998059799446256755549336168680652417784100928974626691394519784210798115234421621395565133934540293453178173202492390960636748758901730809385537425468287739439186639997248317694957321428695607327266714808782275624621543322680743170727138513749585464780667694848460994451845930330171178264547533513278992639373336403333808030648337688005620275578296948431080041460379170968645746237365265775809160089835890963979189417077888217319567655815185722803396768459474863999489318262128246369238065953564952520781335604361853649556051334026765228618755072941224295504768652663359375244997324897501305917843814090132930667495688555708990322640305028765449894226833943950146241950622796031736578694745366351555073434693031657023906596562358851162293026995436960387278469895807724015301865991672913321453445991446231960308148764546047093339377727151377011726855042871856226174494916526669406505529212379480351667790001872743374449509704288980726375623616060019779257474201540386201507436620022897002937160546422879565012408289494323907852895093766446213912064406752807461678134461342605700600206153378134071624961713363823740018700308465832689771179439248941585764851569361035922373764801260972891484727594263997421232398572694266985104149641231498757746289977837831771999080242024235253183181076817672726762563193843627793748721017531172928362349829761730129543862546047744579382889488567520203651468993203952469040816086894746797752390890854717451663471966233595490856628334437094272599887080090228595364710460995387632778361065747562671503846505342839754906769608076635992770078590114226514914294822096273767423515834209488180742512553434933018819838015000406029409056402307298740240619915658227837397460201158635609409097783656507844450724968474348976240064968394874570413450070202769601346737875003619553367832699366579950739335666649637096178985718074779347713877552996255461534152064302474094986801844635313630817286557311459987002358357983881588465057332916035955050586282177014293130382843833266150376798086400365601591759299608734120327990797122372483514319913383563989127907748240714480259464547715902154086737809595450194896811554363399670348368828276584714214311650010118393039679466472748764836833067241964825805672867656714320705334581372045450954654257716102877827995002438814646676346719809483386758465479919842851368561471593883417971411535034911406557218726542396594436883946664038043390402689644318701218913111852684056240970117981684012978145398425042131323915225433863582324739128084381769768691712771468071321699571015500082967432431181461985188882439268994573701992615711307241001408485102650196772634622488918140865258541828888146391368349211316956740542108566814964754524261037210630575927083133912722231441314919230704338397988344431227237092114018319179518161541933494655739124364205418720939435324941417662696819050224095867825229249938596070628203799077641245709332721027314337689657422546406409131373812777762212015455174167262951318508538076577965584955280537558059252325886391100659687871349471318209490095579234701182175149413708705642220088039417562947445314959842010824661983463318773167686521141061262068928106099021473714319649846521940693020497642868599501395050153195119159185203857047951345892614977160202969609041957780083782341201112502849521510472824042288163701699818546423608678456273762186961434179629585497429837554684034543263899700569848451474358511508091619933558573548782518518112842754502845742394945333942734445054227346435257722919517298237075474616874379762910881267807345231629472341624140899921630346427268783868816441588077646794292209743299476964215092059619881109345608021039968924157463473871289597567515539771357100876750759991809368612792224062431700900779636551711256530788926184666033767424466267849345417288437335130634276733114267895918213435657709076818435948369568060539231121492586206765208780043360680407125625215326246492933282136645975896737355541802654692871930093637925021906504645990854761351427585365774295837544974634965491460361903959397550626490284003384915556396637916509695646752041903870800516716463285851584788359669826751512468963411382215275083029089159160819774558888076702022648671003753092088484112000623813510446683205659855599467747490164091191079425491171538539533222764551048946085763360408248983425150079684879854870930943712922096471898093285864041175119865141740426054486155065520108191222309819706551795404911824702300037594490392041205747557525570350937538965553520909984028320393273756884919764889316669965602274155298380640676709349644105059774801608428628447077817146086591981372929719808089294934817785542422579907784365787332201034935984685454123638918887705895277278846933881149540397952389673578094574437303197563276614557263182473270371784628894378828894668016236558810702126406384952770861882221641077526850352072424724019710465244417534532766777194024280448054606107141545072094793811508155588871906196185963982791531190587907143604351737248710976765892152795603341676305051848221973732349059182211317294420153820756352812781489455620516792630216783776347599939072584568919460721372491249467000336079330642854612937472031321870092819449446523578314066933017627069104296977549902853503435506687741917457629346130631333477359074308955075349963159810688707271274660253845491022859381718759412289738625853730252254498938748139808909683703618478147298307338706592837479112556512705300496379406608085872501636232913455302307776112796565011814234630081268769178855260488417748175487398278658549447123225784050248422826883738254946286065436376429207065859806781430541158088118530838124852030303240961018651496198376669419261315839379871328457248759811041554846430663827058808888413183458630134916443104055042307572433167249840535367237595118110487421353832678701109698303593685031528937394161938455480577762922134005268852852618273830415387816912562223524855059143274512897420178147092936945918207313247460866068830314966054507681200680537063660097172406260958241345987453061320460649278415372946002151663960395132858634113474812664279979687050411155478333064879866426033518021980647467235110666830258150398423366177989166005956006785781596946407383499103242270976288826858248369749753567325573042444860970999057036379278519858026507299003482033516782648918895227693973950337639424475653869121945992494474884637033323195785182225476237654352211453355753828379266529089235113872212721447127177772886701191429456790877059634442543396351513383861975811768656832427466550329406725719356580212531975510318175311864365125317692113779434029331964659052970902919943866276650393860883433729902316231172870822907142816960622614651388405863610840071311163615397902001413881449232706585544347014862820920473455127590287322071919934357675181648084689207846111975540876709235854803370993976016410848861831393675678921191553619587034546501410376899620752639610734939597389996054610058032267044140877875285816576464727779128922930799246162402014948978392095684990730313773102414642760886078952744923855544000688472037208121205019634855589351397988814307423743889627015114206614375410815038768079307972773757721495035572066547921629664958566019883055831482870468307446995999475424691875121436756981289334528383611571731595550445141461094566053658364085521110198860947991524695475290992062628621640146140982531720719182213728738506659515260832781523025706212178606992090297580336167235636368819250973530465784756541531984086064166680340936095022155811463218113616562428715500761002357415446295952967427825997146501389082145979791258136172063793628392628391766927391401504117800995581047972368764141733505691130617107015895358084902428561938262711582013352649382229679766480094748460553516581762956636515493423089402796951893932813520923499814183039648911982813452309701018276898938621099313038251063888843318024413754885313671876087426680437876998203720396780910734563776276012811205187053315409563768998063152576466914682874202923155199278843413571003787423530627646158513256858659995785164589757148818711681398747087960351437009114828885634397594543053367474712324965179688291295536561470989900795676719324299757083754637180415311723811058122740092827682402874952038936914881840004064410368741912632908192683005793517855725076115961931940023703799577360907495323092301222188185734600595306917446992273152421544737488932170287511670583024334578278152071702100009913541393406913532446600876402510793163643246032225796488723256727212900085410848776273840513887228931140332514329189689322337847224090374401169719014284508268784937323070880907979575255841315066834202559829895734840590565983920401208803845605288426069587449707800076843675789037703387030117851473628348783518972154134405681635308608521088108512802240583656197993208681072604981881114031567967061841413985249152629763229570288724634969335687419959105546046771139874136199310097481431845835644901998379584817212967172434656777454354691522640067002955955778068378978991161813798762176837487897312608304580282079271004155943354947757522269593561001160840673815872051944104127060667562981347937418373161281795719326374238671365991444199069811301442696748116443419165155005868952350698339744879469226241371664093548510149374865412763609062165067721607455997833267850673499537078542509229489675869009502612852847389320319154438771649292866773074741310163864794144659155707756674548303317852294300338286380241957253007063052489824412691670948986933044500546358637230283967518461946750224520900178270413800537835564118058742830812018560944356264570513022475814236619097308305822378413497812839982542639791754761937429096059150999115693332601609266457182135537432951184838191685692114721259204703916548871592275184611389436622758383664913045052303189346707781715142888529325937148183670255483207364732772261128674551848625673930821679432230410600610967952219485500741273445384325535801237590276480944215973755705077298912272550406631427519396833176938350523528519056820098427949467451853344411753931214233722002936955770114276542996693315923269516860409148407363348385703655308101731220612789114038147260529759667423708379578077308332819532854989990295520768306937284045447439510384693433420839545063349788188409277425185972313316502553723134240043707539254150342570719033501214993268969625190175718531807510428691883826295411960562787928448653800919219554953553694864492269042367536790970896149749229648226976322098843300051245580517664608808325337309825175155240469887660095103491417133327866638816929964725160122139315042971140503853774941825996251799008575747986596611768175444419430316969789564971189483236994848977196286628953165259999920330242791685185896587004702873281723796436928950705513441447270680145873273402157963276214009862312062045828547587898160251573334378936422052532304416381665847238925548492630454019418961334860017889273921158138182596869667250170923170376666536729428724275524531206681809319191982541805437089670490346176767517704289036091651995889426960896096856123166578760485111614889825512658220255697731433294873182601427513648112164872738015519298543493216196948648225195225369926768130616606365218065131161322616447423420417223896869699154655377440554996373206086617689443761872102505143383219783336745491532429649892195040167443470143409585534445770120317260718736465956509661694243010744293189180750985960195596749160020313490899829456996285691516246774732758319081800867831168242969130103382681808829306038966907055905115704454324999479230055623606461848016423923290917334943591307622917322182258235397580523655611218400948846996808209348506222811119217737773458810845105625357958715828332737273659132008137290050398800663572389904688797540126920639754319424569005277630447543933487199618717995857761549637458954050117451331259477152244395792456934551331779983534431083160912613473721478722166967638327842494334697541415538970584330730613058081372134362973122538557243038457232626281234316851218891894055926389689106971940346020408794098856771245319778284853093637055033488989289941279563264053788636623931773495166523596506675407789644696368355979273151849513172264304395494120846986741914123943575415581525735452915798093348358619378270295347224123027313625022322799862458569640042844882624557915847989261520337959481827065866517428913667684754039217801167323191785522743493242523520000";
      
      

      if( executionTime > 550 ) System.out.println("Failed.");
      else if( !result.equals( expected ) ) System.out.println("Failed.");
      else System.out.println("Passed");
   } 
     
   public static void main( String[] args )
   {
      testAddTerm1();
      testAddTerm2();
      testAddTerm3();
      testAddTerm4();
      testAddTerm5();
      
      System.out.println("");
      
      testMultiplyTerm1();
      testMultiplyTerm2();
      testMultiplyTerm3();
      testMultiplyTerm4();
      stresstestMultiplyTerm();
      
      System.out.println("");
      
      testdeepclone1();
      testdeepclone2();
      
      System.out.println("");
      
      testAddpoly1();
      testAddpoly2();
      testAddpoly3();
      testAddpoly4();
      
      System.out.println("");
      
      testmulpoly1();
      testmulpoly2();
      //testmulpoly3();
      //testmulpoly4();
      
      System.out.println("");
      
      testEval1();
      testEval2();
      testEval3();
      testEval4();
      stresstestEval1();
      stresstestEval2();
      
      System.out.println("\nDone.");
   }
} 
 