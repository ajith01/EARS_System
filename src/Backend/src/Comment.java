package Backend.src;
public class Comment{
    private String comments;
    private String committeeMember;

    public Comment(String remark, String committeeMember) {
        this.comments = remark;
        this.committeeMember = committeeMember;
    }

    public String getCommitteeMember() {
        return committeeMember;
    }

    public String getRemark() {
        return comments;
    }

}