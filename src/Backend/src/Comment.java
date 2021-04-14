package Backend.src;
public class Comment{
    private String comments;
    private Member committeeMember;

    Comment(String remark, Member committeeMember) {
        this.comments = remark;
        this.committeeMember = committeeMember;
    }

    public Member getCommitteeMember() {
        return committeeMember;
    }

    public String getRemark() {
        return comments;
    }

}