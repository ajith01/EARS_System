package Backend.src;
public class User {
    private String name;
    private int positionType;     // 0 - superadmin , 1 - admin, 2 - member
    private String positionName;  //perhaps redundant
    private String username;    // this is a unique field, Primary Key
    private String password;
    private String email;

    //few different constructors, just in case we need any;
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, String username, String password){
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;

    }

    public User(String name, String email, String username, String password, int positionType){
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.positionType = positionType;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }


    protected void setPassword(String password) {
        this.password = password;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    public int getPositionType() {
        return positionType;
    }

    protected void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    protected void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public boolean matchUsernamePassword(String username, String password){
        //this function returns true if the given username and password match for the user class

        return (username.equals(getUsername()) && password.equals(getPassword()));
    }
    public boolean isSuperadmin(){
        return getPositionType() == 0;
    }

    public boolean isAdmin(){
        return getPositionType() == 1;
    }

    public boolean isMember(){
        return getPositionType() == 2;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof User){
            return  (((User)object).getName() == name && (((User)object).getUsername() == username));

        }else{
            return false;

        }
    }

}
