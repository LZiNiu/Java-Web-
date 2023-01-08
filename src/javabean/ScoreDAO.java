package javabean;

import java.sql.*;
import java.util.ArrayList;

public class ScoreDAO {
    private Connection cn;

    public ScoreDAO(Connection con) {
        cn=con;
    }
    public boolean validateTableNameExist(String tableName) throws SQLException {
        //判断数据库是否存在表
        ResultSet rs = this.cn.getMetaData().getTables(null, null, tableName, null);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
    public void chooseCourse(ScorePO score){
        try {
        ScorePO unique = this.queryChooseByKey(score.getSno(),score.getCno());
        if(unique!=null){
            throw new UniqueException("该学生已经选过相同课程!");
        }
            String sqlStr = "insert into score(Sno,Cno) values(?,?)";
            PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create a statement
            prepStmt.setString(1, score.getSno());
            prepStmt.setString(2, score.getCno());
            prepStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("chooseCourse error:" + e);
        }
    }
    public void reChooseCourse(ScorePO score, String newCno){
        try {
            ScorePO unique = this.queryChooseByKey(score.getSno(), newCno);
            if(unique!=null){
                throw new UniqueException("该学生已经选修了此课程!");
            }
            //此处似乎可以不做单一性判断,由于sql主键不能重复,如果重选选到已经选过的也不会对数据库生效
            String sqlStr = "update score set Cno=? where Sno=? and Cno=?";
            PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create a statement
            prepStmt.setString(1, newCno);
            prepStmt.setString(2, score.getSno());
            prepStmt.setString(3, score.getCno());
            prepStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("reChooseCourse error:" + e);
        }
    }
    public void addScore(ScorePO score){
        try {

            String sqlStr = "update score set Grade=? where Sno=? and Cno=?";
            PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create a statement
            prepStmt.setFloat(1,score.getGrade());
            prepStmt.setString(2, score.getSno());
            prepStmt.setString(3, score.getCno());
            prepStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("addScore error:" + e);
        }
    }
    public void addScores(ArrayList<ScorePO> scores){
        for (ScorePO score:scores){
            chooseCourse(score);
        }

    }
    public void deleteScore(ScorePO score) {
        //根据姓名删除成绩
        try {
            if (cn != null) {
                String sqlStr = "delete from score where Sno=? and Cno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create a statement
                prepStmt.setString(1, score.getSno());
                prepStmt.setString(2, score.getCno());
                prepStmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("deleteScore error:" + e);
        }
    }

    public void deleteScores(ArrayList<ScorePO> scores){
        for (ScorePO score:scores){
            deleteScore(score);
        }

    }

    public ScorePO queryScoreByKey(String sno) {
        ScorePO score=null;
        try {
            if (cn != null) {
                String sqlStr="SELECT * FROM score  where Sno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr);
                prepStmt.setString(1, sno);
                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    score = new ScorePO();
                    score.setSno(rs.getString("Sno"));
                    score.setCno(rs.getString("Cno"));
                    score.setGrade(rs.getFloat("Grade"));
                }
            }

        } catch (Exception e) {
            System.out.println("queryScore error:" + e);
        }
        return score;

    }

    public ScorePO queryChooseByKey(String sno,String cno) {
        ScorePO score=null;
        try {
            if (cn != null) {
                String sqlStr="SELECT * FROM score  where Sno=? and Cno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr);
                prepStmt.setString(1, sno);
                prepStmt.setString(2, cno);
                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    score = new ScorePO();
                    score.setSno(rs.getString("Sno"));
                    score.setCno(rs.getString("Cno"));
                }
            }

        } catch (Exception e) {
            System.out.println("queryChoose error:" + e);
        }
        return score;

    }
    public ArrayList<ScorePO> queryScores() {
        ArrayList<ScorePO> scores = new ArrayList<ScorePO>();
        ScorePO score;
        try {
            if (cn != null) {
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM score order by Cno");
                while (rs.next()) {
                    score = new ScorePO();
                    score.setSno(rs.getString("Sno"));
                    score.setCno(rs.getString("Cno"));
                    score.setGrade(rs.getFloat("Grade"));
                    scores.add(score);
                }
            }

        } catch (Exception e) {
            System.out.println("queryScore error:" + e);
        }

        return scores;
    }
}
