package com.works.team.services;

import com.works.team.entites.Student;
import com.works.team.entites.projections.IStudent;
import com.works.team.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    final StudentRepository studentRepository;

    final HttpServletRequest req;

    public ResponseEntity register(Student student){
        try {
            Optional<Student> optionalStudent = studentRepository.findByEmailEqualsIgnoreCase(student.getEmail());
            if (optionalStudent.isPresent()){
                return new ResponseEntity(student.getEmail() + ": This Mail Using! ", HttpStatus.BAD_REQUEST);
            }else{
                studentRepository.save(student);
                return new ResponseEntity(student,HttpStatus.OK);
            }
        } catch(Exception ex){
                return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    public ResponseEntity login (Student student){
        Optional<Student> optionalStudent = studentRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(student.getEmail(), student.getPassword());
        if (optionalStudent.isPresent()){
            req.getSession().setAttribute("user", optionalStudent.get());
            return new ResponseEntity(optionalStudent.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity("Email or Password Fail", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity classCreate(){
        List<IStudent> alist = studentRepository.getStudentsBy("A",10,0);
        List<IStudent> blist = studentRepository.getStudentsBy("B",10,0);
        List<IStudent> clist = studentRepository.getStudentsBy("C",10,0);

        Map hm =new LinkedHashMap();
        hm.put("A",alist);
        hm.put("B",blist);
        hm.put("C",clist);
        return new ResponseEntity(hm,HttpStatus.OK);
    }

    public ResponseEntity classBackupCreate(){
        List<IStudent> alist = studentRepository.getStudentsBy("A",10,10);
        List<IStudent> blist = studentRepository.getStudentsBy("B",10,10);
        List<IStudent> clist = studentRepository.getStudentsBy("C",10,10);

        Map hm =new LinkedHashMap();
        hm.put("A",alist);
        hm.put("B",blist);
        hm.put("C",clist);
        return new ResponseEntity(hm,HttpStatus.OK);
    }
}
