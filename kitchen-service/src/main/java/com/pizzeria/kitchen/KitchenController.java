// KitchenController.java

package com.pizzeria.kitchen;

import com.pizzeria.shared.entity.KitchenTask;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchen-tasks")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping
    public List<KitchenTask> getAll() {
        return kitchenService.getAllTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenTask create(@RequestBody KitchenTask task) {
        return kitchenService.createTask(task);
    }

    @PutMapping("/{id}/status")
    public KitchenTask updateStatus(@PathVariable Long id, @RequestBody @Valid StatusUpdateDto dto) {
        return kitchenService.updateTaskStatus(id, dto.getStatus());
    }

    @Data
    public static class StatusUpdateDto {
        @NotBlank
        private String status;
    }
}
