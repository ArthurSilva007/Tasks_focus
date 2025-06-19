package Gestaodetarefa.demo.scheduler;

import Gestaodetarefa.demo.model.Task;
import Gestaodetarefa.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskNotificationScheduler {

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedRate = 60000)
    public void verificarTarefas() {
        List<Task>  atrasadas = taskService.overdue();
        List<Task>  proximas = taskService.dueSoon();

        if (!atrasadas.isEmpty()){
            System.out.println("TAREFAS ATRASADAS");
            atrasadas.forEach(t -> {
                System.out.println("\uD83D\uDD34" + t.getTitle() + "(vencia em" + t.getDueDate() + ")");
            });
        }
        if (!proximas.isEmpty()){
            System.out.println("TAREFAS PROXIMAS DO VENCIMENTO: ");
            proximas.forEach(t -> {
                System.out.println("\uD83D\uDFE1" + t.getTitle() + "(vence em" + t.getDueDate() + ")");
            });
        }
        if (atrasadas.isEmpty()){
            System.out.println("NENHUMA TAREFA URGENTE NO MOMENTO.");
        }
    }

}
