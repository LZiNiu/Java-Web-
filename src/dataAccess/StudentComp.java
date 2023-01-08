package dataAccess;

// Use a custom comparator.
import java.util.*;

// A reverse comparator for strings.
class StudentComp implements Comparator {
public int compare(Object a, Object b) {
    StudentPO aStudent = (StudentPO) a;
    StudentPO bStudent = (StudentPO) b;
    return aStudent.getId().compareTo(bStudent.getId());
}
}
