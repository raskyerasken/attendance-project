/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import Classes.Student;

/**
 *
 * @author jacob
 */
public class MockStudents {

    void addStudents(Model model) {
         Student stud2 = new Student();
        stud2.setFamilyName("Jensen");
        stud2.setName("Karl");
        stud2.setStudPic("/Image/happy.png");
      model.add(stud2);
      Student stud3 = new Student();
        stud3.setFamilyName("Søresen");
        stud3.setName("Sofie");
        stud3.setStudPic("/Image/angry.png");
        model.add(stud3);
    }
    
    
    
}
