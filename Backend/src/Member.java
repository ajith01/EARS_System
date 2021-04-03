public class Member extends User{

    //assumes that a person can be only a member of 25 committees and member and as chair
    private final int MAX_SIZE = 25;
    private Application[] committees = new Application[MAX_SIZE];
    private Application[] chair = new Application[MAX_SIZE];
    private int numberOfCommittees = 0;
    private int numberOfChairs = 0;


    public Member(String username, String password) {
        super(username, password);
        setPositionType(2);
        setPositionName("Member");
    }

    public Member(String name, String email, String username, String password){
        super(name, email, username ,password);
        setPositionType(2);
        setPositionName("Member");
    }

    public Member(String name, String email, String username, String password, String positionName){
        super(name, email, username, password, positionName);
        setPositionType(2);
        setPositionName("Member");
    }

    public void addToCommittee(Application job){
        if(MAX_SIZE <= numberOfCommittees){
            throw new ApplicationException("Number of Applications exceeded limit");
        }else {
            committees[numberOfCommittees++] = job;
        }
    }

    public void addToCommitteeAsChair(Application job){
        if(MAX_SIZE <= numberOfChairs){
            throw new ApplicationException("Number of Applications exceeded limit");
        }else {
            chair[numberOfChairs++] = job;
        }
    }

}
