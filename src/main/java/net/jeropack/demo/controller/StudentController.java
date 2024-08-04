package net.jeropack.demo.controller;

import net.jeropack.demo.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Erick", "Jeronimo");
//        return new ResponseEntity<>(student, HttpStatus.OK);
//        return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("cutom-header","erick")
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Erick", "Jeronimo"));
        students.add(new Student(2,"Oriana", "Pacheco"));
        students.add(new Student(3,"Marcos", "Jeronimo"));
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/{name}/{lastName}")
    public Student getStudentById(@PathVariable("id") int studentId,
                                  @PathVariable("name") String firstName,
                                  @PathVariable("lastName") String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    @GetMapping("/query")
    public ResponseEntity<Student> getStudentUsingParam(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("student deleted successfully");
    }

}
