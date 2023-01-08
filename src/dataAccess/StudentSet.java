package dataAccess;

import java.util.Iterator;
import java.util.TreeSet;

public class StudentSet extends TreeSet<StudentPO>{  

	private static final long serialVersionUID = 1L;

public  StudentSet(){
	super(new StudentComp());
}

	public String[][] list() {
		String[][] list = new String[this.size()][3];
		Iterator<StudentPO> itr = this.iterator();
		int i = 0;
		while (itr.hasNext()) {
			StudentPO stu = itr.next();
			list[i][0] = stu.getId();
			list[i][1] = stu.getName();
			list[i][2] = stu.getSex();
			list[i][3] = stu.getCollege();
			list[i][4] = stu.getMajor();
			list[i][5] = stu.getGrade();
			i++;
		}
		return list;
}
}
