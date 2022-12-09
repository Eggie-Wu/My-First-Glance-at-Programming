

import java.math.BigInteger;
import java.util.Iterator;

class Polynomial implements DeepClone<Polynomial>
{
 private SLinkedList<Term> polynomial;
 public int size()
 { 
  return polynomial.size();
 }
 private Polynomial(SLinkedList<Term> p)
 {
  polynomial = p;
 }
 
 public Polynomial()
 {
  polynomial = new SLinkedList<Term>();
 }
 
 // Returns a deep copy of the object.
 public Polynomial deepClone()
 { 
  return new Polynomial(polynomial.deepClone());
 }
 

 public void addTerm(Term t){ 
  /**** ADD CODE HERE ****/
	 if(t.getCoefficient().compareTo(BigInteger.ZERO)==0) {
		 return;
	 }
	 if(this.size() == 0) {
		 this.polynomial.addFirst(t);
		 return;
	  }
	 
	 int i = 0;
	 
	 for (Term currentTerm: this.polynomial){
		 i++;
		 
		 if(currentTerm.getExponent()==t.getExponent()) {
			  currentTerm.setCoefficient(currentTerm.getCoefficient().add(t.getCoefficient()));
			  if(currentTerm.getCoefficient().compareTo(BigInteger.ZERO)==0){
				  this.polynomial.remove(i-1);
			  }
			  return;
		  }else if(currentTerm.getExponent()<t.getExponent()) {
			  this.polynomial.add(i-1,t);
			  return;
		  }
	 }
	 
	 polynomial.addLast(t);
	 return;
 }

 public Term getTerm(int index)
 {
  return polynomial.get(index);
 }
 
public static Polynomial add(Polynomial p1, Polynomial p2) {
	 Polynomial result = new Polynomial();
	 if(p1.polynomial.size()==0 &&p2.polynomial.size()==0  ) {
		 return result;
	 }
	 if(p1.size()==0) {
		 result=p2.deepClone();
		 return result;
	 }else if(p2.size()==0) {
		 result=p1.deepClone();
		 return result;
	 }
	 BigInteger[] x=new BigInteger[Math.max(p1.polynomial.get(0).getExponent(),p2.polynomial.get(0).getExponent())+1];
	 for(int i = x.length-1;i>=0;i--) {
		 x[i]=BigInteger.ZERO;
	 }
	 for(Term t1: p1.polynomial) {
		 x[t1.getExponent()]= t1.getCoefficient();
	 }
	 for(Term t2: p2.polynomial) {
		 x[t2.getExponent()]= x[t2.getExponent()].add(t2.getCoefficient());
	 }
	 for(int i = x.length-1;i>=0;i--) {
		 if(x[i].compareTo(BigInteger.ZERO)!=0) {
			 Term t= new Term(i,x[i]);
			 result.addTermLast(t);
		 } 
	 }
	 return result;
} 
 
 
 
 
 
 
 //TODO: multiply this polynomial by a given term.
 public void multiplyTerm(Term t)
 { 
  /**** ADD CODE HERE ****/ 
	 if(t.getCoefficient().compareTo(BigInteger.ZERO)==0) {
		 this.polynomial = new SLinkedList<Term>();
		 return;
	 }
	 BigInteger coe = t.getCoefficient();
	 int exp = t.getExponent();
	 for (Term currentTerm: this.polynomial) {
		 currentTerm.setCoefficient(currentTerm.getCoefficient().multiply(coe));
		 currentTerm.setExponent(currentTerm.getExponent()+exp);
	 }
 }
 
 
 //TODO: multiply two polynomials
 public static Polynomial multiply(Polynomial p1, Polynomial p2) {
	 Polynomial result = new Polynomial();
	 
	 if(p1.polynomial.size()==0 ||p2.polynomial.size()==0  ) {
		 return result;
	 }
	 
	 BigInteger[] x=new BigInteger[p1.polynomial.get(0).getExponent()+p2.polynomial.get(0).getExponent()+1];
	 
	 for(int i = x.length-1;i>=0;i--) {
		 x[i]=BigInteger.ZERO;
	 }
	 
	 for(Term t1: p1.polynomial) {
		 for(Term t2: p2.polynomial) {
			 x[t1.getExponent()+t2.getExponent()] = x[t1.getExponent()+t2.getExponent()].add(t1.getCoefficient().multiply(t2.getCoefficient()));
		 }
	 }
	 
	 for(int i = x.length-1;i>=0;i--) {
		 if(x[i].compareTo(BigInteger.ZERO)!=0) {
			 Term t= new Term(i,x[i]);
			 result.addTermLast(t);
		 } 
	 }
	 return result;
 }
 


 public BigInteger eval(BigInteger x) {
	 if(this.size()==0) {
		 return new BigInteger("0"); 
	 }
	 
	 BigInteger  result = this.polynomial.get(0).getCoefficient();
	 int exp = this.polynomial.get(0).getExponent();
	 
	 int i = 0;
	 for (Term currentTerm: this.polynomial) {
		 i ++;
		 if(i == 1) {
			 continue;
		 }
		 
		 
		 for(int k=exp;k>currentTerm.getExponent();k--) {
			 result = result.multiply(x);
		 }
		 
		 exp = currentTerm.getExponent();
		 result = result.add(currentTerm.getCoefficient());
		 
	 }
	 for(int k=exp;k>0;k--) {
		 result = result.multiply(x);
	 }
	 return result; 
 }
 

 public boolean isDeepClone(Polynomial p)
 { 
  if (p == null || polynomial == null || p.polynomial == null || this.size() != p.size())
   return false;

  int index = 0;
  for (Term term0 : polynomial)
  {
   Term term1 = p.getTerm(index);

   // making sure that p is a deep clone of this
   if (term0.getExponent() != term1.getExponent() ||
     term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)  
    return false;

   index++;
  }
  return true;
 }
 

 public void addTermLast(Term t)
 { 
  polynomial.addLast(t);
 }
 
 
 @Override
 public String toString()
 { 
  if (polynomial.size() == 0) return "0";
  return polynomial.toString();
 }
}
