package client_code;

import internal_code.Remote_control;
import java.util.*;
import static java.util.Arrays.*;

public class RunDrone {

    public static void main(String arg[]){

        Remote_control remote=new Remote_control();

        //execute while only contain take off and empty
        System.out.println(remote.access());
        remote.execute();

        //index of command range from 1 to n, including the takeoff at the beginning and land at the end.
        //remote.delete(1) or delete the last command will show error message.
        //only delete from 2 to n-1 could be successful.

        //delete while sequence is null
        remote.delete(1);

        //normal removal
        remote.add("up,30");
        remote.delete(2);


        //delete while arraylist is empty
        remote.delete(100);

        remote.add("up,30");

        //show index out of range
        remote.delete(100);
        remote.delete(0);



        //test required functionality
        /*
        format:
        remote.add("up,x") for move up x meters.
        remote.add("down,x") for move down x meters.
        remote.add("forward,x") for move forward x meters.
        remote.add("backwards,x") for move backwards x meters.
        remote.add("focus") for focus.
        remote.add("take off") for take off.
        remote.add("land") for land.
        remote.add("capture,ass1,png") for capture a picture as ass1.png.
        separate token by ,
        extra space in the first field will result error message.
        */


        remote.add("up,30");
        remote.add("down,25");
        remote.add("focus");
        remote.add("capture,ass1,png");
        remote.add("land");

        //should not be able to land again.
        remote.add("land");

        //should be able to focus and capture picture while on the ground
        //saved picture should be efawega.eaw,age.aw,geagw.raw, which contain multiple . and ,
        remote.add("focus");
        remote.add("capture,efawega.eaw,age.aw,geagw,raw");

        //take off and set height to 0.5 meter.
        remote.add("take off");

        //should not be able to take off again
        remote.add("take off");

        remote.add("forward,20");

        //should not be able to move down 1 meters, or the drone will crash
        remote.add("down,1");

        //should not be able to add null movement
        remote.add(null);
        //
        //should be able to add, but should not be able to execute these wrong format of commands
        remote.add("forward,jpg");
        remote.add("COMP303");
        remote.add("");
        remote.add(",,,,,,,,,,,,,");

        //file type not supported
        remote.add("capture,test,jpeg");

        remote.add("up,33");
        remote.add("backwards,10");

        //access the copy of the entire sequence of movement
        System.out.println(remote.access());
        //execute the sequence of movement
        remote.execute();


        //immutable sequence
        Remote_control remote1=new Remote_control();
        remote1.add("up,30");
        System.out.println(remote1.access());
        remote1.execute();

        ArrayList<String> mirror= remote1.access();
        mirror.add("down,2");
        System.out.println(remote1.access());
        remote1.execute();



    }
}
