//import Backend.src.*;
//
///** This class seres to load all the dummy test data into the system
// *  using data structures as required (lists, arrayList, unique objects, etc.)
// *
// *  This class holds the functions required to interact with the front end.
// *  The functions return required data for the current application state.
// * */
//public class mainHelperBackend {
//
//    private User currUser;
//    private int userType = -1;
//    // -1 - instantiated
//    // 0, 1, 2 - types defined
//    private boolean loaded = false;
//
//    // IGNORE FOR NOW
//    // this user should only be able to select from the applications they are part of,
//    // otherwise, in case of admin or chair, all applications are selectable
//
//    // private ArrayList<JobApplication> applications;
//    // private ArrayList<User> users;
//
//    public void main(String args[]){
//
//        if(userType == -1){
//            LoginFrontend login = new LoginFrontend();
//            login.main(null);
//            setCurrUser(login.getUser());
//        }
//        if(!loaded) {
//            if (!loadData()) {
//                // error
//            }
//            loaded = true;
//        }
//        mainHelperBackend();
//    }
//
//    public boolean loadData(){
//        // TODO: if files were not read, throw exception
//        return true;
//    }
//    public void mainHelperBackend(){
//        // code to load applications and user data for the system
//        if(userType == 1) {
//            BigoneAdmin adminPanel = new BigoneAdmin();
//            adminPanel.main(null);
//        }
//        else if(userType == 2){
//            BigOneMember memberPanel = new BigOneMember();
//            memberPanel.main(null);
//        }
//
//    }
//
//    // set the user that has logged in for ease of access to relevant data
//    public void setCurrUser(User user){
//        this.currUser = user;
//        userType = user.getPositionType();
//        //return true;
//    }
//
////    public Application findAppByName (String name){
////
////    }
//
//
//}
