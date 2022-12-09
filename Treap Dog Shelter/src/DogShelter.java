import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


class DogShelter implements Iterable<Dog> {
	public DogNode root;

	public DogShelter(Dog d) {
		this.root = new DogNode(d);
	}


	// add a dog to the shelter
	public void shelter(Dog d) {
		if (root == null)
			root = new DogNode(d);
		else
			root = root.shelter(d);
	}

	// removes the dog who has been at the shelter the longest
	public Dog adopt() {
		if (root == null)
			return null;

		Dog d = root.d;
		root =  root.adopt(d);
		return d;
	}

	// overload adopt
	// to remove from the shelter a specific dog
	public void adopt(Dog d) {
		if (root != null)
			root = root.adopt(d);
	}


	// get the oldest dog in the shelter
	public Dog findOldest() {
		if (root == null)
			return null;

		return root.findOldest();
	}

	// get the youngest dog in the shelter
	public Dog findYoungest() {
		if (root == null)
			return null;

		return root.findYoungest();
	}

	// get dog with highest adoption priority with age within the range
	public Dog findDogToAdopt(int minAge, int maxAge) {
		return root.findDogToAdopt(minAge, maxAge);
	}

	// Returns the expected vet cost the shelter has to incur in the next numDays days
	public double budgetVetExpenses(int numDays) {
		if (root == null)
			return 0;

		return root.budgetVetExpenses(numDays);
	}

	// returns a list of Dogs. The dogs in the list at index 0 need to see the vet in the next week.
	// The dogs in the list at index i need to see the vet in i weeks. 
	public ArrayList<ArrayList<Dog>> getVetSchedule() {
		if (root == null)
			return new ArrayList<ArrayList<Dog>>();

		return root.getVetSchedule();
	}





	public class DogNode {
		public Dog d;
		public DogNode younger;
		public DogNode older;
		public DogNode parent;

		public DogNode(Dog d) {
			this.d = d;
			this.younger = null;
			this.older = null;
			this.parent = null;
		}


		private void rotateLeft(DogNode newnode){
			if(newnode.parent==null) {
				return;
			}
			DogNode p = newnode.parent;
			DogNode o = newnode.younger;

			if(newnode.parent.parent==null) {
				newnode.parent=null;
				newnode.younger=p;
				p.parent=newnode;
				p.older=o;
				if(o!=null) {
					o.parent=p;
				}
				return;
			}

			DogNode g = newnode.parent.parent;
			if(g.older==p) {
				g.older=newnode;
				newnode.parent=g;
				newnode.younger=p;
				p.parent=newnode;
				p.older=o;
				if(o!=null) {
					o.parent=p;
				}
			}else if(g.younger==p) {
				g.younger=newnode;
				newnode.parent=g;
				newnode.younger=p;
				p.parent=newnode;
				p.older=o;
				if(o!=null) {
					o.parent=p;
				}
			}
		}

		private void rotateRight(DogNode newnode){
			if(newnode.parent==null) {
				return;
			}

			DogNode p = newnode.parent;
			DogNode o = newnode.older;

			if(newnode.parent.parent==null) {
				newnode.older=p;
				newnode.parent.parent=newnode;
				newnode.parent.younger=o;
				newnode.parent=null;
				if(o!=null) {
					o.parent=newnode.older;
				}
				return;
			}

			DogNode g = newnode.parent.parent;
			if(g.older==p) {
				g.older=newnode;
				newnode.parent=g;
				newnode.older=p;
				p.parent=newnode;
				p.younger=o;
				if(o!=null) {
					o.parent=p;
				}
			}else if(g.younger==p) {
				g.younger=newnode;
				newnode.parent=g;
				newnode.older=p;
				p.parent=newnode;
				p.younger=o;
				if(o!=null) {
					o.parent=p;
				}
			}
		}

		public DogNode shelter (Dog d) {
			if(d==null) {
				return this;
			}


			DogNode newNode = new DogNode(d);

			if(root==null) {
				return newNode;
			}

			DogNode node = this;

			/*while(node.parent!=null) {
				node=node.parent;
			}*/


			while (true){

				if(newNode.d.getAge() > node.d.getAge()){
					if (node.older == null)
						break;
					node = node.older;
				}
				else if(newNode.d.getAge() < node.d.getAge()){
					if(node.younger == null)
						break;
					node = node.younger;
				}
			}


			if(newNode.d.getAge() > node.d.getAge()){
				node.older = newNode;
				newNode.parent=node;

			}
			else if(newNode.d.getAge() < node.d.getAge()){
				node.younger = newNode;
				newNode.parent=node;
			}
			while(newNode.parent!=null) {
				if(newNode.d.getDaysAtTheShelter()>newNode.parent.d.getDaysAtTheShelter()) {
					if(newNode.parent.younger==newNode) {
						rotateRight(newNode);
					}else {
						rotateLeft(newNode);
					}
					if(newNode.parent==null) {
						return newNode;
					}
				}else {
					break;
				}
			}

			DogNode newroot = newNode;
			while(newroot.parent!=null) {
				newroot=newroot.parent;
			}



			return newroot;
		}


		private DogNode delete(DogNode node) {
			if(node.parent==null) {
				if(node.younger==null&&node.older==null) {
					return null;
				}else if(node.younger == null) {
					node.older.parent=null;
					return node.older;
				}else if(node.older == null) {
					node.younger.parent=null;
					return node.younger;
				}else {
					DogNode oldD=node.younger;
					if(oldD.older==null) {
						oldD.parent=null;
						node.older.parent=oldD;
						oldD.older=node.older;
					}else {
						while(oldD.older!=null) {
							oldD=oldD.older;
						}
						oldD.parent.older=oldD.younger;
						oldD.parent=null;
						node.older.parent=oldD;
						node.younger.parent=oldD;
						oldD.older=node.older;
						oldD.younger=node.younger;
					}
					while(oldD.younger!=null||oldD.older!=null) {
						if(oldD.younger!=null&&oldD.older!=null) {
							if(oldD.younger.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()||oldD.older.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()) {
								if(oldD.younger.d.getDaysAtTheShelter()>oldD.older.d.getDaysAtTheShelter()) {
									rotateRight(oldD.younger);
								}else {
									rotateLeft(oldD.older);
								}
							}else {
								break;
							}
						}else if(oldD.younger!=null){
							if(oldD.younger.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()) {
								rotateRight(oldD.younger);
							}else {
								break;
							}
						}else {
							if(oldD.older.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()) {
								rotateLeft(oldD.older);
							}else {
								break;
							}
						}
					}
					DogNode result=oldD.parent;
					while(result.parent!=null) {
						result=result.parent;
					}
					return result;
				}
			}else {
				if(node.younger==null&&node.older==null) {
					if(node.parent.younger==node) {
						node.parent.younger=null;
					}else {
						node.parent.older=null;
					}
					DogNode result=node.parent;
					while(result.parent!=null) {
						result=result.parent;
					}
					return result;
				}else if(node.younger == null) {
					if(node.parent.younger==node) {
						node.parent.younger=node.older;
						node.older.parent=node.parent;
					}else {
						node.parent.older=node.older;
						node.older.parent=node.parent;
					}
					DogNode result=node.parent;
					while(result.parent!=null) {
						result=result.parent;
					}
					return result;
				}else if(node.older==null&&node.younger!=null) {
					if(node.parent.younger==node) {
						node.parent.younger=node.younger;
						node.younger.parent=node.parent;
					}else {
						node.parent.older=node.younger;
						node.younger.parent=node.parent;
					}
					DogNode result=node.parent;
					while(result.parent!=null) {
						result=result.parent;
					}
					return result;
				}else {
					DogNode oldD=node.younger;
					if(oldD.older==null) {
						if(node.parent.younger==node) {
							node.parent.younger=oldD;
						}else {
							node.parent.older=oldD;
						}
						oldD.parent=node.parent;
						node.older.parent=oldD;
						oldD.older=node.older;
					}else {
						while(oldD.older!=null) {
							oldD=oldD.older;
						}
						oldD.parent.older=oldD.younger;

						if(node.parent.younger==node) {
							node.parent.younger=oldD;
						}else {
							node.parent.older=oldD;
						}
						oldD.parent=node.parent;
						node.older.parent=oldD;
						node.younger.parent=oldD;
						oldD.older=node.older;
						oldD.younger=node.younger;
					}
					while(oldD.younger!=null||oldD.older!=null) {
						if(oldD.younger!=null&&oldD.older!=null) {
							if(oldD.younger.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()||oldD.older.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()) {
								if(oldD.younger.d.getDaysAtTheShelter()>oldD.older.d.getDaysAtTheShelter()) {
									rotateRight(oldD.younger);
								}else {
									rotateLeft(oldD.older);
								}
							}else {
								break;
							}

						}else if(oldD.younger!=null){
							if(oldD.younger.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()) {
								rotateRight(oldD.younger);
							}else {
								break;
							}
						}else {
							if(oldD.older.d.getDaysAtTheShelter()>oldD.d.getDaysAtTheShelter()) {
								rotateLeft(oldD.older);
							}else {
								break;
							}
						}
					}
					DogNode result=oldD.parent;
					while(result.parent!=null) {
						result=result.parent;
					}
					return result;
				}

			}

		}

		public DogNode adopt(Dog d) {

			if(d==null) {
				return this;
			}

			DogNode newnode=new DogNode(d);

			DogNode node = this;



			if(node.younger==null&&node.older==null&&node.d==d){
				return null;
			}else if(node.younger==null&&node.older==null&&node.d!=d){
				return this;
			}

			DogNode newroot=node;

			boolean found = false;
			while (!found){
				if(newnode.d.getAge() > node.d.getAge()){
					if (node.older == null)
						break;
					node = node.older;
				}
				else if(newnode.d.getAge() < node.d.getAge()){
					if(node.younger == null)
						break;
					node = node.younger;
				}else if (newnode.d.getAge() == node.d.getAge()){
					found=true;
				}
			}
			if(!found) {
				return newroot;
			}else {
				return delete(node);

			}
		}

		public Dog findOldest() {
			DogNode node = this;
			/*while(node.parent!=null) {
				node=node.parent;	
			}*/
			while(node.older!=null) {
				node=node.older;
			}
			return node.d;
		}

		public Dog findYoungest() {
			DogNode node = this;
			/*while(node.parent!=null) {
				node=node.parent;	
			}*/

			while(node.younger!=null) {
				node=node.younger;
			}
			return node.d;
		}

		private Dog findAdopt(int minAge, int maxAge) {
			DogNode node = this;

			if(minAge<=node.d.getAge()&&maxAge>=node.d.getAge()) {
				return this.d;
			}else if(maxAge<node.d.getAge()){
				if(node.younger!=null) {
					return node.younger.findAdopt(minAge, maxAge);
				}else {
					return node.d;
				}

			}else if(minAge>node.d.getAge()) {
				if(node.older!=null) {
					return node.older.findAdopt(minAge, maxAge);
				}else {
					return node.d;
				}

			}else {
				return null;
			}

		}

		public Dog findDogToAdopt(int minAge, int maxAge) {
			DogNode node = this;
			
			/*while(node.parent!=null) {
				node=node.parent;	
			}*/

			return node.findAdopt(minAge, maxAge);

		}

		private void intraverse(DogNode root, ArrayList<Dog> doglist){
			if (root == null)
				return;
			intraverse(root.younger, doglist);
			doglist.add(root.d);
			intraverse(root.older, doglist);
		}


		public double budgetVetExpenses(int numDays) {
			double result=0;
			DogNode node = this;
			
			/*while(node.parent!=null) {
				node=node.parent;	
			}*/

			ArrayList<Dog> list = new ArrayList<Dog>();
			intraverse(node,list);

			for(int i=0;i<list.size();i++) {
				if(list.get(i).getDaysToNextVetAppointment()<=numDays) {
					result += list.get(i).getExpectedVetCost();
				}
			}
			return result;
		}





		public ArrayList<ArrayList<Dog>> getVetSchedule() {
			DogNode node = this;
			
			/*while(node.parent!=null) {
				node=node.parent;	
			}*/

			ArrayList<Dog> list = new ArrayList<Dog>();
			intraverse(node,list);

			ArrayList<ArrayList<Dog>> vetSchedule = new ArrayList<ArrayList<Dog>>();
			int maxday=0;
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getDaysToNextVetAppointment()>maxday) {
					maxday=list.get(i).getDaysToNextVetAppointment();
				}
			}

			for(int i=0;i<=maxday/7;i++) {
				vetSchedule.add(new ArrayList<Dog>());
			}
			for(int i=0;i<list.size();i++) {
				vetSchedule.get(list.get(i).getDaysToNextVetAppointment()/7).add(list.get(i));
			}


			return vetSchedule;
		}

		public String toString() {
			String result = this.d.toString() + "\n";
			if (this.younger != null) {
				result += "younger than " + this.d.toString() + " :\n";
				result += this.younger.toString();
			}
			if (this.older != null) {
				result += "older than " + this.d.toString() + " :\n";
				result += this.older.toString();
			}
			/*if (this.parent != null) {
				result += "parent of " + this.d.toString() + " :\n";
				result += this.parent.d.toString() +"\n";
			}*/
			return result;
		}

	}
	public Iterator<Dog> iterator() {
		return new DogShelterIterator();
	}


	private class DogShelterIterator implements Iterator<Dog> {
		ArrayList<Dog> doglist;

		private void intraverse(DogNode root, ArrayList<Dog> doglist){
			if (root == null)
				return;
			intraverse(root.older, doglist);
			doglist.add(root.d);
			intraverse(root.younger, doglist);
		}

		private DogShelterIterator() {

			if(root==null){
				doglist=null;
				return;
			}
			DogNode node = root;

			while(node.parent!=null) {
				node=node.parent;
			}
			doglist = new ArrayList<Dog>();
			intraverse(node,doglist);

		}

		public Dog next(){
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}
			Dog dog = doglist.get(doglist.size()-1);
			doglist.remove(doglist.size()-1);
			return dog;
		}

		public boolean hasNext() {
			if(doglist==null){
				return false;
			}else if(doglist.size()==0) {
				return false;
			}else if(doglist.get(doglist.size()-1)==null){
				return false;
			}else{
				return true;
			}

		}

	}

}
