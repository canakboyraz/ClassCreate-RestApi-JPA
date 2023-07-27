package com.works.team.restcontrollers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.works.team.entites.Student;
import com.works.team.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentRestController {

    final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody Student student){

        return studentService.register(student);
    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Student student){
        return studentService.login(student);
    }

    @GetMapping("/classCreate")
    public ResponseEntity classCreate(){
        return studentService.classCreate();
    }

    @GetMapping("/backUpCreate")
    public ResponseEntity backUpCreate(){
        return studentService.classBackupCreate();
    }

}
