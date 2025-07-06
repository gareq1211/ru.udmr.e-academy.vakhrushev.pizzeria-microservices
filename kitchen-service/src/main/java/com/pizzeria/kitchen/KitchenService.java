package com.pizzeria.kitchen;

import com.pizzeria.shared.entity.KitchenTask;
import lombok.RequiredArgsConstructor;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenTaskRepository repository;

    public List<KitchenTask> getAllTasks() {
        return repository.findAll();
    }

    public KitchenTask createTask(KitchenTask task) {
        return repository.save(task);
    }

    public KitchenTask updateTaskStatus(Long id, String status) {
        KitchenTask task = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Задача с ID " + id + " не найдена"));

        task.setStatus(status);
        return repository.save(task);
    }

}
