package Backend.src;
import java.util.ArrayList;
import java.util.Date;

public class JobApplication {
    private String jobTitle;
    private String position;
    private String candidateName;
    private Date startingDate;
    private Date endingDate;
    private String description;

    private Boolean isApproved;
    private int status;

    private ArrayList<Member> committeeMembers;
    private ArrayList<Member> chair;   //want to be a string

    private ArrayList<Comment> comments = new ArrayList<>();


    public JobApplication(String name, String jobTitle, String description, Date startingDate,
                          Date endingDate, ArrayList<Member> chair, ArrayList<Member> committeeMembers ){
        candidateName = name;
        this.jobTitle = jobTitle;
        this.description = description;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.chair = chair;
        this.committeeMembers = committeeMembers;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public ArrayList<Member> getChair() {
        return chair;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList getRemarks() {
        return comments;
    }

    public void addRemark(String s, String u){

        boolean memberExist = false;
        for(int i = 0; i < committeeMembers.size();i++){
            if(committeeMembers.get(i).getName().equals(u)){
                memberExist = true;
            }
        }
        if(!memberExist) {
            throw new ApplicationException("User not part of committee");
        }
        comments.add(new Comment(s, u));
    }
//    public void setRemarks(Remark[] remarks) {
//        this.remarks = remarks;
//    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addCommitteeMember(Member member){
        committeeMembers.add(member);
    }

    public void addChair(Member member){
        chair.add(member);
    }

    public void addComment(Comment comment){
        comments.add(comment);

    }
    public ArrayList<Comment> getComments(){
        return comments;
    }

    public Comment seeAUsersComment(Member member){
        int remarksSize = comments.size();
        for(int i = 0; i < remarksSize;  i++){
            if(((comments.get(i)).getCommitteeMember()).equals(member)){
                return comments.get(i);

            }
        }
        return null;  //if nothing found return null
    }

    public boolean hasMember(String usrName){
//        ArrayList<String> allmembers = new ArrayList<>();
//
        for(Member user : committeeMembers){
            if(user.getUsername().equals(usrName))
                return true;
        }
        return false;
    }

    public boolean hasChair(String usrName){

        for(Member user : chair){
            if(user.getUsername().equals(usrName))
                return true;
        }
        return false;
    }

    public String getDescription() {
        return description;
    }
}
