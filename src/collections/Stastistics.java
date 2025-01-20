/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.util.HashMap;
import java.util.List;
import model.Student;
import model.StastiticalInfo;
import collections.StudentList;
import java.util.ArrayList;

/**
 *
 * @author DANH NGUYEN
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANH NGUYEN
 */
public class Stastistics extends HashMap<String, StastiticalInfo> {

    public Stastistics() {
        super();
    }

    public Stastistics(List<Student> s) {
        super();
        statisticalize(s);
    }

    public final void statisticalize(List<Student> s) {
        for (Student student : s) {
            double tuitionFee = StudentList.deleteCommaToTuitionFee(student.getTuitionFee());
            if (this.containsKey(student.getMountainCode())) {
                StastiticalInfo x = this.get(student.getMountainCode());
                x.setParticipant(x.getParticipant() + 1);
                x.setTotalTuitionFee(x.getTotalTuitionFee() + tuitionFee);
            } else {
                StastiticalInfo x1 = new StastiticalInfo(student.getMountainCode(), 1, tuitionFee);
                this.put(student.getMountainCode(), x1);
            }
        }
    }

    public void show() {
        menu.Menu.displayStastisticBar();
        for (StastiticalInfo value : this.values()) {
            System.out.println(value);
        }
        menu.Menu.DisplayBarLine();
    }
}
