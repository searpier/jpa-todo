package com.jpa.todolist.service.impl;

import com.jpa.todolist.dto.TodoResponse;
import com.jpa.todolist.entity.Todo;
import com.jpa.todolist.repository.TodoRepository;
import com.jpa.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoResponse> todoList() {

        return todoRepository.findAll().stream()
                .map(en->new TodoResponse(en))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteTodos(String id) {
        try{
            todoRepository.deleteById(Long.valueOf(id));
        }catch(Exception e){
            //e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    @Override
    public String insertTodos(String todos) {

        try {
            todoRepository.save(Todo.builder()
                    .todos(todos)
                    .build());
        }catch(Exception e){
            //e.printStackTrace();
            return "failed";
        }
        return "success";
    }
}
