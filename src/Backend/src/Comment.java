package Backend.src;
import java.util.Date;
public class Comment{
    private String remark;
    private Date date;
    private Member committeeMember;

    Comment(String remark, Date date, Member committeeMember) {
        this.remark = remark;
        this.date = date;
        this.committeeMember = committeeMember;
    }

    public Member getCommitteeMember() {
        return committeeMember;
    }

    public String getRemark() {
        return remark;
    }

    public Date getDate() {
        return date;
    }
}

