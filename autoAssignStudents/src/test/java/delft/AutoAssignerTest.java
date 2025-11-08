package delft;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.AllArguments;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AutoAssignerTest {

    private ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }

    private ZonedDateTime date1 = date(2025, 11, 04, 10, 30);
    private ZonedDateTime date2 = date(2026, 12, 05, 11, 31);
    private ZonedDateTime date3 = date(2027, 10, 06, 12, 32);

    private Map<ZonedDateTime, Integer> spotsPerDate1 = Map.of(date1, 1);
    private Map<ZonedDateTime, Integer> spotsPerDate2 = Map.of(date2, 3);
    private Map<ZonedDateTime, Integer> spotsPerDate3 = Map.of(date3, 2);

    private List<AllArguments.Assignment> assignments = new ArrayList<>();

    private List<Student> students = new ArrayList<>();
    private Student Louis = new Student(1, "Louis", "Louis@gmail.com");
    private Student Julien = new Student(2, "Julien", "Julien@gmail.com");
    private Student Houria = new Student(3, "Houria", "Houria@gmail.com");
    private Student Anas = new Student(4, "Anas", "Anas@gmail.com");

    private List<Workshop> workshops = new ArrayList<>();
    private Workshop workshop1 = new Workshop(1, "workshop1", spotsPerDate1);
    private Workshop workshop2 = new Workshop(2, "workshop2", spotsPerDate2);
    private Workshop workshop3 = new Workshop(3, "workshop3", spotsPerDate3);



    // ——————————————— Test on students —————————————
    @Test
    public void goodCreationStudentTest(){
        students.add(Louis);
        Student louisTemp = students.get(0);
        assertEquals(louisTemp.getName(), "Louis");
        assertEquals(louisTemp.getId(), 1);
        assertEquals(louisTemp.getEmail(), "Louis@gmail.com");
    }

    @Test
    public void goodCreationSameStudentTest(){
        students.add(Louis);
        Student louisTemp = students.get(0);
        Student louisTemp2 = new Student(1, "Louis", "Louis@gmail.com");
        assertEquals(louisTemp2, louisTemp);
        assertEquals(louisTemp2.hashCode(), louisTemp.hashCode());
        assertNotEquals(0, louisTemp.hashCode());
    }
    // ————————————— Test on workshop ——————————————

    @Test
    public void goodCreationWorkshopTest(){
        workshops.add(workshop1);
        Workshop workshopTemp = new Workshop(4, "workshop1", spotsPerDate1);
        workshops.add(workshopTemp);
        assertEquals(workshopTemp.getSpotsPerDate(), workshop1.getSpotsPerDate());
    }

    // ———————————————— Test on map —————————————————

    @Test
    public void goodCreationOfMapTest(){
        ZonedDateTime dateTemp = date(2027, 10, 06, 12, 32);
        Map<ZonedDateTime, Integer> spotsPerDate = Map.of(dateTemp, 2);

        assertEquals(spotsPerDate, spotsPerDate3);
        assertEquals(spotsPerDate.get(0), spotsPerDate3.get(0));
        assertTrue(spotsPerDate.equals(spotsPerDate3));
        assertEquals(spotsPerDate.hashCode(), spotsPerDate3.hashCode());
        assertNotEquals(0, spotsPerDate.hashCode());
    }


    // —————— Test on students with workshops ———————

    @Test
    public void AddMoreStudentThanPlaceInWorkshopTest() {
        students.add(Louis);
        students.add(Julien);
        workshops.add(workshop1);

        AutoAssigner autoAssigner = new AutoAssigner();
        AssignmentsLogger assignmentsLogger = autoAssigner.assign(students, workshops);

        assertNotNull(assignmentsLogger.getErrors()) ;
        assertNotNull(assignmentsLogger.getAssignments()) ;
        assertEquals(1, assignmentsLogger.getAssignments().size());
        assertEquals(1, assignmentsLogger.getErrors().size());
    }

    @Test
    public void AddStudentsInSeveralWorkshopsTest() {
        students.add(Louis);
        students.add(Julien);
        workshops.add(workshop1);
        workshops.add(workshop2);

        AutoAssigner autoAssigner = new AutoAssigner();
        AssignmentsLogger assignmentsLogger = autoAssigner.assign(students, workshops);

        System.out.println(assignmentsLogger.getErrors());
        System.out.println(assignmentsLogger.getAssignments());
        assertNotNull(assignmentsLogger.getErrors()) ;
        assertNotNull(assignmentsLogger.getAssignments()) ;
        assertEquals(3, assignmentsLogger.getAssignments().size());
        assertEquals(1, assignmentsLogger.getErrors().size());
    }


    @Test
    public void goodNameofStudentInWorkshop() {
        students.add(Louis);
        students.add(Julien);
        workshops.add(workshop2);

        AutoAssigner autoAssigner = new AutoAssigner();
        AssignmentsLogger assignmentsLogger = autoAssigner.assign(students, workshops);

        List<String> listeNameOfStudent = new ArrayList<>();
        for (String assignments : assignmentsLogger.getAssignments()) {
            String[] parts = assignments.split(",");
            listeNameOfStudent.add(parts[1]);
        }
        int i = 0;
        for (Student student : students) {
            assertEquals(listeNameOfStudent.get(i), student.getName());
            i++;
        }
    }

}
