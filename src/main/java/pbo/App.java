package pbo;

import javax.persistence.*;
import java.util.*;
import pbo.model.Mahasiswa;
import pbo.model.Course;
import pbo.model.Enroll;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("study_plan_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        while (true) {
            String command = scanner.nextLine();
            String[] data = command.split("#");

            switch (data[0]) {
                case "student-add":
                    String nim = data[1];
                    String nama = data[2];
                    String prodi = data[3];

                  
                    List<Mahasiswa> students = entityManager.createQuery(
                            "SELECT s FROM Mahasiswa s WHERE s.nim = :nim", Mahasiswa.class)
                            .setParameter("nim", nim)
                            .getResultList();
                    if (!students.isEmpty()) {
                        continue;
                    }

                    Mahasiswa student = new Mahasiswa(nim, nama, prodi);
                    entityManager.getTransaction().begin();
                    entityManager.persist(student);
                    entityManager.getTransaction().commit();
                    break;

                case "student-show-all":
                    List<Mahasiswa> studentsList = entityManager.createQuery(
                            "SELECT s FROM Mahasiswa s ORDER BY s.nim ASC", Mahasiswa.class)
                            .getResultList();

                    for (Mahasiswa studentItem : studentsList) {
                        System.out.println(studentItem.toString());
                    }
                    break;

                case "course-add":
                    String kode = data[1];
                    String namaCourse = data[2];
                    String semester = data[3];
                    String kredit = data[4];

                 
                    Course existingCourse = entityManager.find(Course.class, kode);
                    if (existingCourse != null) {
                    
                        existingCourse.setNama(namaCourse);
                        existingCourse.setSemester(semester);
                        existingCourse.setKredit(kredit);
                    } else {
              
                        Course course = new Course(kode, namaCourse, semester, kredit);
                        entityManager.persist(course);
                    }

                    entityManager.getTransaction().begin();
                    entityManager.getTransaction().commit();
                    break;

                case "course-show-all":
                    List<Course> coursesList = entityManager.createQuery(
                            "SELECT c FROM Course c ORDER BY c.kode ASC", Course.class)
                            .getResultList();

                    for (Course courseItem : coursesList) {
                        System.out.println(courseItem.toString());
                    }
                    break;

                case "enroll":
                    String nimEnroll = data[1];
                    String kodeEnroll = data[2];

                 
                    Enroll existingEnrollment = entityManager.createQuery(
                            "SELECT e FROM Enroll e WHERE e.nim = :nim AND e.kode = :kode", Enroll.class)
                            .setParameter("nim", nimEnroll)
                            .setParameter("kode", kodeEnroll)
                            .getResultList()
                            .stream()
                            .findFirst()
                            .orElse(null);

                    if (existingEnrollment != null) {
                        continue;
                    }

           
                    Mahasiswa studentEnroll = entityManager.find(Mahasiswa.class, nimEnroll);
                    Course courseEnroll = entityManager.find(Course.class, kodeEnroll);

                    if (studentEnroll == null) {
                        System.out.println("Student not found.");
                        continue;
                    }

                    if (courseEnroll == null) {
                        System.out.println("Course not found.");
                        continue;
                    }

                 
                    Enroll enroll = new Enroll(studentEnroll.getNim(), kodeEnroll);
                    entityManager.getTransaction().begin();
                    entityManager.persist(enroll);
                    entityManager.getTransaction().commit();
                    break;

                case "student-show":
                    String nimDetail = data[1];

           
                    Mahasiswa studentDetail = entityManager.find(Mahasiswa.class, nimDetail);
                    if (studentDetail != null) {
                        System.out.println(studentDetail.toString());

                      
                        List<Enroll> enrolledCourses = entityManager.createQuery(
                                "SELECT e FROM Enroll e WHERE e.nim = :nim ORDER BY e.course.semester ASC, e.course.kode ASC",
                                Enroll.class)
                                .setParameter("nim", nimDetail)
                                .getResultList();

                        for (Enroll enrollItem : enrolledCourses) {
                            Course enrolledCourse = entityManager.find(Course.class, enrollItem.getKode());
                            System.out.println(enrollItem.toString() + " - " + enrolledCourse.getNama());
                        }
                    }
                    break;

                default:
                    scanner.close();
                    entityManager.getTransaction().begin();
                    entityManager.createQuery("DELETE FROM Mahasiswa").executeUpdate();
                    entityManager.createQuery("DELETE FROM Course").executeUpdate();
                    entityManager.createQuery("DELETE FROM Enroll").executeUpdate();
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    entityManagerFactory.close();
                    return;
            }
        }
    }
}
