package internal_code;
import java.util.*;

 class Drone {
    //represent whether the drone have taken off.
    private boolean isstarted;
    //represent whether the camera have focused.
    private boolean isfocused;
    //represent the aptitude of the drone, which can not go below 0
    private double height;


    //constructor of the drone class
    Drone(){
        this.isstarted = false ;
        this.isfocused = false ;
        this.height = 0;
    }

    //move up down forward backward are only allowed when the drone has taken off.
    //focus and capture is allowed when the drone is on the ground.
    //Any movement other than focus and capture is going to defocus the camera of the drone

    //take off and set the height to 0.5 meter.
    private void takeOff(){
        if(this.isstarted){
            System.out.println("The drone has already taken off, not able to take off again while in the sky");
        }else{
            this.isfocused = false;
            this.isstarted = true;
            this.height = 0.5;
            System.out.println("The drone is taking off......The drone has taken off");
        }
    }

    //land and set the height to 0 meter.
    private void land(){
        if(!this.isstarted){
            System.out.println("The drone has already landed on the ground, not able to land again");
        }else {
            this.isfocused = false;
            this.isstarted = false;
            this.height = 0;
            System.out.println("The drone is landing......The drone has landed");
        }
    }

    //focus the camera
    private void focus(){
        this.isfocused = true;
        System.out.println("Focused");
    }

    //check if the camera is focused and capture the photo as the input filename and filetype
    private void capture(String name,String type){
        if(!isfocused){
            System.out.println("Camera haven't focused, yet. Not able to capture any picture.");
        }else{

            System.out.println("Captured a picture as "+name+"."+type+".");
        }
    }

    //move forward for x meters.
    private void moveForward(double x){
        if(!this.isstarted){
            System.out.println("The drone hasn't taken off, yet. Not able to move.");
        }else {
            this.isfocused = false;
            System.out.println("Moving forward......Moved forward for "+x+" meters");
        }
    }

    //move backwards for x meters.
    private void moveBackwards(double x){
        if(!this.isstarted){
            System.out.println("The drone hasn't taken off, yet. Not able to move.");
        }else {
            this.isfocused = false;
            System.out.println("Moving backwards......Moved backwards for "+x+" meters");
        }

    }

    //move up for x meters.
    private void moveUp(double x){
        if(!this.isstarted){
            System.out.println("The drone hasn't taken off, yet. Not able to move.");
        }else {
            this.height+=x;
            this.isfocused = false;
            System.out.println("Moving up......Moved up for "+x+" meters");
        }
    }

    //check if the drone will crash after move down for x meters, if not , move down for x meters.
    private void moveDown(double x){
        if(!this.isstarted){
            System.out.println("The drone hasn't taken off, yet. Not able to move.");
        }else {
            if(this.height<x){
                System.out.println("Not able to move down for "+x+" meters, otherwise the drone will crash");
                return;
            }else{
                this.isfocused = false;
                this.height-=x;
                System.out.println("Moving down......Moved down for "+x+" meters");
            }
        }
    }



    //package private method, only accessible by Remote_control class,
    //take in a sequence of movements and execute it
    void action(ArrayList<String> sequence) {
        if (sequence == null) {
            this.takeOff();
            this.land();
            System.out.println("\n\n");
        } else {
            this.takeOff();

            //construct a iterator
            Iterator<String> sequenceIterator = ((ArrayList<String>) sequence.clone()).iterator();
            while (sequenceIterator.hasNext()) {
                String movestr=sequenceIterator.next();

                //check if the movement is null
                if(movestr==null){
                    System.out.println("Your movement can not be null.");
                }else{

                    //split by ","
                    String[] arrOfStr = movestr.split(",",-1);

                    //case for "take off", "land", "focus"
                    if(arrOfStr.length == 1){
                        if(arrOfStr[0].equals("take off")){
                            this.takeOff();
                        }else if(arrOfStr[0].equals("land")){
                            this.land();
                        }else if(arrOfStr[0].equals("focus")){
                            this.focus();
                        }else{
                            System.out.println("Such command is not supported");
                        }

                        //case for "up, x",  "down, x", "forward, x", "backward, x",
                    }else if(arrOfStr.length == 2){
                        try{
                            double distance = Double.parseDouble(arrOfStr[1]);
                            if(arrOfStr[0].equals("up")){
                                this.moveUp(distance);
                            }else if(arrOfStr[0].equals("down")){
                                this.moveDown(distance);
                            }else if(arrOfStr[0].equals("forward")){
                                this.moveForward(distance);
                            }else if(arrOfStr[0].equals("backwards")){
                                this.moveBackwards(distance);
                            }else{
                                System.out.println("Such command is not supported");
                            }
                        }catch (Exception e){
                            System.out.println("Such command is not supported");
                            continue;
                        }



                        //case for "capture,filename,filetype"
                    }else if(arrOfStr.length >= 3){
                        if(arrOfStr[0].equals("capture")){

                            //check if the file type is jpg, png, raw, or pdf
                            //filename could contain multiple "," so check the last element of arrOfStr.
                            if(arrOfStr[arrOfStr.length-1].equals("jpg")||arrOfStr[arrOfStr.length-1].equals("png")||
                                    arrOfStr[arrOfStr.length-1].equals("pdf")||arrOfStr[arrOfStr.length-1].equals("raw")){

                                String filename=movestr.substring(8,movestr.lastIndexOf(","));
                                this.capture(filename,arrOfStr[arrOfStr.length-1]);
                            }else{
                                System.out.println("File type is not supported");
                            }
                        }else{
                            System.out.println("Such command is not supported");
                        }
                    }else{
                        System.out.println("Such command is not supported");
                    }
                }
            }
            this.land();
            System.out.println("\n\n");
        }
    }
}
