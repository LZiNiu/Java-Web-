package controller;

import jakarta.servlet.http.HttpServletRequest;
import javabean.ScoreDAOTester;
import javabean.ScoreDAO;
import javabean.ScorePO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ScoreAction implements IAction{
    public String execute(HttpServletRequest request, Connection cn) {
        String operation=request.getParameter("operation");
        ScoreDAO scoreDAO=new ScoreDAO(cn);
        try {
            //判断表是否存在没有则调用create_table
            if(!scoreDAO.validateTableNameExist("score")){
                ScoreDAOTester daoTester = new ScoreDAOTester();
                daoTester.createTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (operation.equals("choose")|| operation.equals("rechoose")|| operation.equals("insert") ||operation.equals("delete")){
            ScorePO score=new ScorePO();
            String sno=request.getParameter("sno");
            score.setSno(sno);
            String cno=request.getParameter("cno");
            score.setCno(cno);
            String grade=request.getParameter("grade");
            //由于删除时没有传入grade,需要检查
            if(grade!=null){
                score.setGrade(Float.parseFloat(grade));
            }

            if (operation.equals("choose") && !cno.equals("无")){
                //课程号为无则没有选课
                scoreDAO.chooseCourse(score);
            } else if (operation.equals("insert")) {
                scoreDAO.addScore(score);
            } else if (operation.equals("delete")){
                scoreDAO.deleteScore(score);
            } else if(operation.equals("rechoose")){
                String newCno = request.getParameter("newCno");
                if(!newCno.equals(cno) && !newCno.equals("无")){
                    scoreDAO.reChooseCourse(score, newCno);
                }
            }

        }
        List<ScorePO> scores=scoreDAO.queryScores();
        request.setAttribute("scoreList", scores);
        return "/ScoreList.jsp";
    }
}
