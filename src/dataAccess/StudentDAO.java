package dataAccess;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class StudentDAO {
	private DataAccessManager manager;	
	

	public StudentDAO(DataAccessManager manager) {
		this.manager=manager;
	}
	
	public StudentSet loadStudentsFromXMLFile(){
		StudentSet stus=new StudentSet();
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try{
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db=factory.newDocumentBuilder();
			Document xmldoc=db.parse(manager.openXMLFile());
			Element root=xmldoc.getDocumentElement();
			StudentPO stu;
			Node objNode,attrNode;
			
			for(int i=0;i<root.getChildNodes().getLength();i++){
				objNode=root.getChildNodes().item(i);
				if(objNode.getNodeName().equals("stu")){
					stu=new StudentPO();					
					for (int j=0;j<objNode.getChildNodes().getLength();j++){
						attrNode=objNode.getChildNodes().item(j); 
						if(attrNode.getNodeName().equals("id")){
							stu.setId(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("name")){
							stu.setName(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("sex")){
							stu.setSex(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("college")){
							stu.setCollege(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("major")) {
							stu.setMajor(attrNode.getFirstChild().getNodeValue());
						}
						if(attrNode.getNodeName().equals("grade")) {
							stu.setGrade(attrNode.getFirstChild().getNodeValue());
						}
					}					
					stus.add(stu);
				}
			}			
		
		}catch (ParserConfigurationException error){
			error.printStackTrace();
		}catch (SAXException error2){
			error2.printStackTrace();
		}catch(FileNotFoundException error3)  {
			error3.printStackTrace();
		}catch(IOException error4)  {
			error4.printStackTrace();
		}		
		
		
		return stus;
	}
	public void writeStudentsToXmlFile(StudentSet stus){		
		TransformerFactory transFactory=TransformerFactory.newInstance();
	    
		try{
			
			Transformer transformer=transFactory.newTransformer();
			transformer.setOutputProperty("indent","yes");
			DOMSource source=new DOMSource();
			
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder db=factory.newDocumentBuilder();
			Document xmldoc=db.newDocument();		
			Element root=xmldoc.createElement("stus");
			
            Element theStudent,theAttr;
            for (StudentPO stu : stus){
	          //--- 新建一个朋友信息 ----
	            theStudent=xmldoc.createElement("stu");
	            theAttr=xmldoc.createElement("id");
	            theAttr.setTextContent(stu.getId());
	            theStudent.appendChild(theAttr);
	            
	            theAttr=xmldoc.createElement("name");
	            theAttr.setTextContent(stu.getName());
	            theStudent.appendChild(theAttr);
	            
	            
	            theAttr=xmldoc.createElement("sex");
	            theAttr.setTextContent(stu.getSex());
	            theStudent.appendChild(theAttr);
	            
	            
	            theAttr=xmldoc.createElement("college");
	            theAttr.setTextContent(stu.getCollege());
	            theStudent.appendChild(theAttr);

				theAttr=xmldoc.createElement("major");
				theAttr.setTextContent(stu.getMajor());
				theStudent.appendChild(theAttr);

				theAttr=xmldoc.createElement("grade");
				theAttr.setTextContent(stu.getGrade());
				theStudent.appendChild(theAttr);
	            root.appendChild(theStudent);
            }
            xmldoc.appendChild(root);
			source.setNode(xmldoc);
			StreamResult result=new StreamResult();
			FileOutputStream stream=new FileOutputStream(manager.openXMLFile());
			result.setOutputStream(stream);
			transformer.transform(source,result);
			
			stream.close();
		}catch (ParserConfigurationException error){
			error.printStackTrace();
		}catch(TransformerConfigurationException error1){
			error1.printStackTrace();
		}catch(TransformerException error2){
			error2.printStackTrace();
		}catch(FileNotFoundException error3)  {
			error3.printStackTrace();
		}catch(IOException error4)  {
			error4.printStackTrace();
		}		
	}
	public StudentSet loadStudentsFromExcelFile(){
		StudentSet stus=new StudentSet();
		try{
			FileInputStream inputStream=new FileInputStream(manager.openExcelFile());
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
		    HSSFWorkbook hssfWorkbook =  new HSSFWorkbook(poifsFileSystem);
		    HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		    int rowstart = hssfSheet.getFirstRowNum();
		    int rowEnd = hssfSheet.getLastRowNum();
		    StudentPO stu;
		    for(int i=rowstart+1;i<=rowEnd;i++){
		        HSSFRow row = hssfSheet.getRow(i);
		        if(null == row) continue; 
		        stu=new StudentPO();
		        HSSFCell cell = row.getCell(0);
		        stu.setId(cell.getStringCellValue());

		        cell = row.getCell(1);
		        stu.setName(cell.getStringCellValue());

		        cell = row.getCell(2);
		        stu.setSex(cell.getStringCellValue());

		        cell = row.getCell(3);
		        stu.setCollege(cell.getStringCellValue());

				cell = row.getCell(4);
				stu.setMajor(cell.getStringCellValue());
				cell = row.getCell(5);
				stu.setGrade(cell.getStringCellValue());
		        stus.add(stu);
		    }
		    inputStream.close();
		}catch(FileNotFoundException error){
			System.out.println("excel file cannot find");
		}catch(IOException error){
			System.out.println(error.getMessage());
		}
	    return stus;		
	}
	
	public void writeStudentsToExcelFile(StudentSet stus){		
		HSSFWorkbook workbook = null;
	    workbook = new HSSFWorkbook();
	
	    int columeCount = 6;
	   
	    HSSFSheet sheet = workbook.createSheet("学生信息");
	
	    HSSFRow headRow = sheet.createRow(0);
	    String[] titleArray = {"学号", "名字", "性别", "学院", "专业", "班级"};
	    for(int m=0;m<=columeCount-1;m++){
	        HSSFCell cell = headRow.createCell(m);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        sheet.setColumnWidth(m, 7000);
	        HSSFCellStyle style = workbook.createCellStyle();
	        HSSFFont font = workbook.createFont();
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        short color = HSSFColor.RED.index;
	        font.setColor(color);
	        style.setFont(font);
	         cell.setCellStyle(style);
	        cell.setCellValue(titleArray[m]);

	    }
	    int index = 0;
	  
	    for(StudentPO stu : stus){	        
	        HSSFRow row = sheet.createRow(index+1);
	        for(int n=0;n<=columeCount-1;n++)
	            row.createCell(n);
	        row.getCell(0).setCellValue(stu.getId());
	        row.getCell(1).setCellValue(stu.getName());
	        row.getCell(2).setCellValue(stu.getSex());
	        row.getCell(3).setCellValue(stu.getCollege());
			row.getCell(4).setCellValue(stu.getMajor());
			row.getCell(5).setCellValue(stu.getGrade());
	        index++;
	    }

	  
	    try {
	        FileOutputStream fileOutputStream = new FileOutputStream(manager.openExcelFile());
	        workbook.write(fileOutputStream);
	        fileOutputStream.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }	    
	}
	
	public void addStudent(StudentPO stu){
		try {			
			String sqlStr = "insert into stu(id,name,sex,college,major,grade) values(?,?,?,?,?,?)";
			PreparedStatement prepStmt = manager.getConnection().prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, stu.getId());
			prepStmt.setString(2, stu.getName());
			prepStmt.setString(3, stu.getSex());
			prepStmt.setString(4, stu.getCollege());
			prepStmt.setString(5, stu.getMajor());
			prepStmt.setString(6, stu.getGrade());
			prepStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("add Student error:" + e);
		}
	}

	public void addStudents(StudentSet stus){
		for (StudentPO stu:stus){
			addStudent(stu);
		}
		
	}
	public void deleteStudent(StudentPO stu) {
		try {
			String sqlStr = "delete from stu where id=?";
			PreparedStatement prepStmt = manager.getConnection().prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, stu.getId());
			prepStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deletestu error:" + e);
		}
	}
	
	public void deleteStudents(StudentSet stus){
		for (StudentPO stu:stus){
			deleteStudent(stu);
		}
		
	}
	public void updateStudent(StudentPO stu) {
		try {
			String sqlStr = "update stu set sex=?,college=?,major=?, grade=? "
					+ " where name=?";
			PreparedStatement prepStmt = manager.getConnection().prepareStatement(sqlStr); // create a statement
			prepStmt.setString(1, stu.getSex());
			prepStmt.setString(2, stu.getCollege());
			prepStmt.setString(3, stu.getMajor());
			prepStmt.setString(4, stu.getGrade());
			prepStmt.setString(5, stu.getName());

			prepStmt.executeUpdate();			

		} catch (Exception e) {
			System.out.println("updatestu error:" + e);
		}
	}
	
	public void updateStudents(StudentSet stus){
		for (StudentPO stu:stus){
			updateStudent(stu);
		}
		
	}
	
	public StudentPO queryStudentByName(String name) {
		StudentPO stu=null;
		try {
			if (manager.getConnection() != null) {
				String sqlStr="SELECT * FROM stu  WHERE name=?";
				PreparedStatement prepStmt = manager.getConnection().prepareStatement(sqlStr);
				prepStmt.setString(1, name);
				ResultSet rs = prepStmt.executeQuery();
				if (rs.next()) {
					stu = new StudentPO();
					stu.setId(rs.getString("id"));
					stu.setName(rs.getString("name"));
					stu.setSex(rs.getString("sex"));
					stu.setCollege(rs.getString("college"));
					stu.setMajor(rs.getString("major"));
					stu.setGrade(rs.getString("grade"));
				}
			}

		} catch (Exception e) {
			System.out.println("querystu error:" + e);
		}

		return stu;
		
	}
	public StudentSet queryStudents() {
		StudentSet stus = new StudentSet();
		StudentPO stu;
		try {
			if (manager.getConnection() != null) {
				Statement stmt = manager.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM stu order by name");
				while (rs.next()) {
					stu = new StudentPO();
					stu.setId(rs.getString("id"));
					stu.setName(rs.getString("name"));
					stu.setSex(rs.getString("sex"));
					stu.setCollege(rs.getString("college"));
					stu.setMajor(rs.getString("major"));
					stu.setGrade(rs.getString("grade"));
					stus.add(stu);
				}
			}

		} catch (Exception e) {
			System.out.println("querystu error:" + e);
		}

		return stus;
	}
	
}
