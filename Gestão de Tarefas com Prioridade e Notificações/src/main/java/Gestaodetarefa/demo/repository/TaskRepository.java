package Gestaodetarefa.demo.repository;

import Gestaodetarefa.demo.model.Priority;
import Gestaodetarefa.demo.model.Status;
import Gestaodetarefa.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);
    List<Task> findByDueDateBefore(LocalDateTime dateTime);
    List<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end);
}