class Hornet extends Insect {
	
	private int damage;

	public Hornet(Tile position, int hp, int damage) {
		super(position, hp);
		this.damage = damage;
	}
	
	public boolean takeAction() {
		
		if(this.getPosition() == null) {
			return false;
		}
		
		Tile temp = this.getPosition().towardTheHive();
		
		
		
		if(this.getPosition().getBee() != null) {
			
			this.getPosition().getBee().takeDamage(this.damage);
			return true;
		}else{
			if(this.getPosition().isHive()==true) {
				return false;
			}
			if(this.getPosition().removeInsect(this)) {
				if(temp.addInsect(this)) {
					return true;
				}
			}	
		}
		return false;
	}
	
	public boolean equals(Object obj) {
		boolean checker = false;
		if (super.equals(obj)) {
			if(((Hornet)obj).damage == this.damage){
				checker = true;
			}
            
		}
		return checker;	
	}


}
