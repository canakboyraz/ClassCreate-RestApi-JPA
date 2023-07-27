package com.works.team.services;

import com.works.team.entites.Class;
import com.works.team.entites.Student;
import com.works.team.repositories.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {
    final ClassRepository classRepository;
    final HttpServletRequest req;

    public ResponseEntity save(String className){
        Student student = (Student) req.getSession().getAttribute("user");
        Optional<Class> optionalClass = classRepository.findByNameEqualsAndSidEquals(className, student.getSid());
        if (optionalClass.isPresent()){
            return new ResponseEntity("Save Fail!", HttpStatus.BAD_REQUEST);
        }else {
            Class aClass = new Class();
            aClass.setSid(student.getSid());
            aClass.setName(className);
            classRepository.save(aClass);
            return new ResponseEntity(aClass,HttpStatus.OK);
        }

    }
}
