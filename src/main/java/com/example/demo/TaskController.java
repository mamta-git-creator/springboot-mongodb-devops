package com.example.demo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  private final TaskRepository repository;
  public TaskController(TaskRepository repository) { this.repository = repository; }

  @GetMapping public List<Task> all() { return repository.findAll(); }
  @PostMapping public Task create(@RequestBody Task task) {
    return repository.save(new Task(null, task.title(), task.completed()));
  }
  @GetMapping("/{id}") public Task one(@PathVariable String id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
  @DeleteMapping("/{id}") public void delete(@PathVariable String id) { repository.deleteById(id); }
}
