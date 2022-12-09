package internal_code;

import java.util.Objects;

class Turn extends Movement{
    private Direction direction;
    private Speed speed;
    private boolean isRecord;

    /**
     * Constructor
     * @param direction,speed,isRecord of the Movement
     */
    Turn(Direction direction, Speed speed, boolean isRecord){
        this.direction=direction;
        this.speed=speed;
        this.isRecord=isRecord;
    }

    Direction getDirection(){
        return this.direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turn)) return false;
        Turn turn = (Turn) o;
        return direction == turn.direction && speed == turn.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, speed);
    }

    //print the movement
    void execute() {
        if(this.isRecord){
            System.out.println("Turn "+this.direction.toString()+
                    " at "+this.speed.toString()+" speed. (Recording)");
        }else{
            System.out.println("Turn "+this.direction.toString()+
                    " at "+this.speed.toString()+" speed. (Not Recording)");
        }
    }
}
