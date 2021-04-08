package Backend.src;

import java.util.ArrayList;
package Backend.src;

/** This class seres to load all the dummy test data into the system
 *  using data structures as required (lists, arrayList, unique objects, etc.)
 *
 *  This class holds the functions required to interact with the front end.
 *  The functions return required data for the current application state.
 * */
public class mainHelperBackend {
    private User currUser;
    private ArrayList<JobApplication> applications;
    // private ArrayList<User> users;

    public mainHelperBackend(){
        // code to load applications and user data for the system
    }

    // set the user that has logged in for ease of access to relevant data
    public boolean setCurrUser(User user){
        this.currUser = user;
        return true;
    }

//    public Application findAppByName (String name){
//
//    }


}
