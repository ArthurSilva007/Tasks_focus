package Gestaodetarefa.demo.TaskController;

import Gestaodetarefa.demo.model.Priority;
import Gestaodetarefa.demo.model.Status;
import Gestaodetarefa.demo.model.Task;
import Gestaodetarefa.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task created = service.create(task);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Task>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findbyId(@PathVariable Long id) {
        return service.findBbyId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
   @PutMapping("/{id}")
   public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task taskAtualizada) {
        try {
            Task updated = service.update(id, taskAtualizada);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
   }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> findByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> filterByPriority(@PathVariable Priority priority) {
        return ResponseEntity.ok(service.filterByPriority(priority));
   }
   @GetMapping("/due-soon")
    public ResponseEntity<List<Task>> dueSoon() {
        return ResponseEntity.ok(service.dueSoon());
   }
   @GetMapping("/overdue")
    public ResponseEntity<List<Task>> overdue() {
        return ResponseEntity.ok(service.overdue());
   }
}