package backendClasses;
public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
        setPositionType(1);
        setPositionName("Admin");
    }

    public Admin(String name, String email, String username, String password){
        super(name, email, username ,password);
        setPositionType(1);
        setPositionName("Admin");
    }

    public Admin(String name, String email, String username, String password, String positionName){
        super(name, email, username, password, positionName);
        setPositionType(1);
        setPositionName("Admin");
    }
}
