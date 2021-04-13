package Backend.src;
public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
        setPositionType(1);
        setPositionName("Admin");
    }

    public Admin(String name, String email, String username, String password){
        super(name, email, username ,password);
//        setPositionType(1);
        setPositionName("Admin");
    }

    public Admin(String name, String email, String username, String password, int positionType){
        super(name, email, username, password, positionType);
//        setPositionType(1);
        setPositionName("Admin");
    }
}
