package javabean;

import java.sql.Statement;
import java.util.ArrayList;

public class ScoreDAOTester {
    private DatabaseManager dbManager;
    private ScoreDAO dao;

    public ScoreDAOTester() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost:3306/javaCourse";
        String dbUser = "root";
        String dbPassword = "123456";
        dbManager = DatabaseManager.getInstance(driverName, dbUrl, dbUser, dbPassword);
        dao = new ScoreDAO(dbManager.getConnection());
    }

    public void closeConnection() {
        dbManager.closeConnection();
    }

    public void createTable() {
        try {
            Statement stmt = dbManager.getConnection().createStatement();
            stmt.execute("create table score(Sno varchar(12),Cno varchar(12)," +
                    " Grade float,primary key (Sno,Cno))");
            System.out.println("Created table score");
        } catch (Exception e) {
            System.out.println("Created table score error:" + e);
        }
    }

    private void dropTable() {
        try {
            Statement stmt = dbManager.getConnection().createStatement();
            stmt.execute("drop table score");
            System.out.println("drop table score");
        } catch (Exception e) {
            System.out.println("drop table score error:" + e);
        }
    }

    private void insertInitRecords() {
        ArrayList<ScorePO> scores = new ArrayList<ScorePO>();

        ScorePO score = new ScorePO();
        score.setSno("20205401");
        score.setCno("A001");
        score.setGrade(87);
        scores.add(score);

        score = new ScorePO();
        score.setSno("95001");
        score.setCno("A003");
        score.setGrade(95);
        scores.add(score);

        score = new ScorePO();
        score.setSno("95002");
        score.setCno("A002");
        score.setGrade(90);

        scores.add(score);

        dao.addScores(scores);
        System.out.println("insert Init 3 Records");
        System.out.println("==列出现有学生成绩表==");
        testQueryRecords();


    }

    private void testQueryRecords() {

        System.out.printf("%s   %s  %s\n", "学号", "课程序号",  "成绩");
        ArrayList<ScorePO> list = dao.queryScores();
        if (list != null && list.size() > 0) {
            for (ScorePO one : list) {
                System.out.println(one.toString());
            }
        }
    }

    private void testInsertRecord() {
        ScorePO score = new ScorePO();
        score.setCno("A000");
        score.setSno("20205404");
        score.setGrade(98);
        dao.chooseCourse(score);

        System.out.println();
        System.out.println("==增加后的成绩表==");
        testQueryRecords();

    }

    private void testUpdateRecord() {
        ScorePO score = dao.queryScoreByKey("20205404");
        score.setSno("A0004");
        score.setGrade(81);
        dao.chooseCourse(score);
        dao.addScore(score);

        System.out.println();
        System.out.println("==更新后的成绩表==");
        testQueryRecords();

    }

    private void testDeleteRecord() {
        ScorePO score = dao.queryScoreByKey("20205404");
        dao.deleteScore(score);
        System.out.println();
        System.out.println("==进行删除后的成绩表==");
        testQueryRecords();
    }

    public static void main(String arg[]) {
        javabean.ScoreDAOTester daoTester = new javabean.ScoreDAOTester();
        daoTester.dropTable();
        daoTester.createTable();
        daoTester.insertInitRecords();
        daoTester.testInsertRecord();
        daoTester.testUpdateRecord();
        daoTester.testDeleteRecord();
        daoTester.closeConnection();
    }
}
