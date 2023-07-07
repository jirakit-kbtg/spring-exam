package com.example.exam.exception;

import java.util.List;

public class IncompleteOperationException extends RuntimeException{
    private List<Integer> ids;
    public IncompleteOperationException(String message, List<Integer> ids) {
        super(message);
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
