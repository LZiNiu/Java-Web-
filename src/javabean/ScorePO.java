package javabean;

public class ScorePO {
    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public float getGrade() {
        return Grade;
    }
    public void setGrade(float grade) {
        Grade = grade;
    }

    private String Sno;
    private String Cno;
    private float Grade;

    public String toString() {
        return String.format("%s   %s  %.1f", this.Sno, this.Cno, this.Grade);
    }
}
