package com.example.exam.controller;

import com.example.exam.exception.IncompleteOperationException;
import com.example.exam.exception.IncorrectPositionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvicer {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNotFound(Exception e){
        return new ResponseEntity<>(e.getMessage() ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPositionException.class)
    public ResponseEntity<String> handleIncorrectPosition(Exception e){
        return new ResponseEntity<>("Current position is incorrect" ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncompleteOperationException.class)
    public ResponseEntity<Map<String,List<Integer>>> handleIncompleteOperation(IncompleteOperationException e){
        HashMap<String, List<Integer>> map = new HashMap<>();
        map.put("not_found_ids", e.getIds());
        return new ResponseEntity<>(map, HttpStatus.MULTIPLE_CHOICES);
    }
}
