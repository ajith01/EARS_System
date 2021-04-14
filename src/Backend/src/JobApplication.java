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
    private ArrayList<Member> chair;

    private ArrayList<Comment> remarks;


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


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList getRemarks() {
        return remarks;
    }

    public void addRemark(String s, Member u){
        if(!committeeMembers.contains(u) ) {
            throw new ApplicationException("User not part of committee");
        }
        remarks.add(new Comment(s, new Date(), u));
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

    public Comment seeAUsersComment(Member member){
        int remarksSize = remarks.size();
        for(int i = 0; i < remarksSize;  i++){
            if(((remarks.get(i)).getCommitteeMember()).equals(member)){
                return remarks.get(i);

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
        for(Member user : chair){
            if(user.getUsername().equals(usrName))
                return true;
        }
        return false;
    }


    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
}
