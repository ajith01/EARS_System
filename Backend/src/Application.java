import java.util.ArrayList;
import java.util.Date;

class Remark {
    protected String remark;
    protected Date date;
    protected User user;

    Remark(String remark, Date date, User user) {
        this.remark = remark;
        this.date = date;
        this.user = user;
    }
}

public class Application {

    private int MAX_MEMBERS = 10;
    private String position;
    private String candidateName;
    private Date dateCreated;
    private String description;

    private Boolean isApproved;
    private int status;

    private ArrayList<User> committeeMembers;
    private ArrayList<Remark> remarks;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList getRemarks() {
        return remarks;
    }
    public void addRemark(String s, User u){
        if(!committeeMembers.contains(u)) {
            throw new ApplicationException("User not part of committee");
        }
        remarks.add(new Remark(s, new Date(), u));
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
}
