import java.util.ArrayList;
import java.util.Date;

public class Application {

    private int MAX_MEMBERS = 10;
    private String position;
    private String candidateName;
    private Date dateCreated;
    private String description;

    private Boolean isApproved;
    private int status;

    private ArrayList<Member> committeeMembers;
    private ArrayList<Member> chair;

    private ArrayList<Comment> remarks;


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


}
