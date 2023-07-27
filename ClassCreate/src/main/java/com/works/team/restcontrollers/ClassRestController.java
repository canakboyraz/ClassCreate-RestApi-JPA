package com.works.team.restcontrollers;

import com.works.team.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
public class ClassRestController {
    final ClassService classService;
    final HttpServletRequest req;

    @PostMapping("/save/{className}")
    public ResponseEntity save(@PathVariable String className){
        boolean loginStatus = req.getSession().getAttribute("user")==null;
        if (loginStatus){
            return new ResponseEntity("Please Login", HttpStatus.UNAUTHORIZED);
        }
        return classService.save(className);
    }
}
