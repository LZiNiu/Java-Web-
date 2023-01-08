package javabean;

public class CoursePO {
    private String Cno;
    private String Cname;
    private float Ccredit;//学分
    private String prerequisite;//先修课程

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public float getCredit() {
        return Ccredit;
    }

    public void setCcredit(float ccredit) {
        Ccredit = ccredit;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String toString() {
        return String.format("%s    %-12s\t  %.2f  %-12s", this.Cno, this.Cname, this.Ccredit, this.prerequisite);
    }
}
