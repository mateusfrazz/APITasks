package br.com.mateus.TodoList.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.naming.Binding;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    // Criar task
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody TaskModel taskModel, BindingResult result,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }

        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(200).body(task);
    }

    // Listar tasks
    @GetMapping("/allTasks")
    public List<TaskModel> getAllTasks(HttpServletRequest request) {
        return list(request);
    }

    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    // Atualizar task
    @PutMapping("/update/{id}")
    public TaskModel update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        taskModel.setId(id);
        return this.taskRepository.save(taskModel);
    }
}
