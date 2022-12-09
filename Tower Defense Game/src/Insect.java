abstract class Insect {
	
	private Tile position;
	private int hp;
	
	public Insect(Tile position,int hp) {
		this.position = position;
		this.hp = hp;
		
		if(!position.addInsect(this)) {
			this.position = null;
		}	
	}
	
	public final Tile getPosition() {
		return this.position;
	}
	
	public final int getHealth() {
		return this.hp;
	}
	
	public void setPosition(Tile position) {
		this.position = position;
		
	}
	
	public void takeDamage(int damage) {
		if(damage>=0 && this.getPosition() != null) {
			if(this instanceof HoneyBee && this.getPosition().isHive()) {
				this.hp -= (int)(damage*0.9);
			}else {
				this.hp -= damage;
			}
			
			if(this.getHealth()<=0) {
				this.position.removeInsect(this);
			}
		}	
	}
	
	public abstract boolean takeAction();
	
	public boolean equals(Object obj) {
		boolean checker = false;
		if (this == obj) {
            checker = true;
		}
		else if(obj != null && obj.getClass() == this.getClass()) {
			if(((Insect)obj).getPosition() == this.getPosition()&&((Insect)obj).getHealth() == this.getHealth()) {
				checker = true;
			}
		}
		return checker;	
	}

}
