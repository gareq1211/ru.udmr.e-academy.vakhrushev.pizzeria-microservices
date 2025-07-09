package com.pizzeria.menu.controller;

import com.pizzeria.menu.entity.Pizza;
import com.pizzeria.menu.repository.PizzaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final PizzaRepository pizzaRepository;

    @GetMapping
    public List<Pizza> getAll() {
        return pizzaRepository.findAll();
    }

    @PostMapping
    public Pizza addPizza(@RequestBody @Valid Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @GetMapping("/{id}")
    public List<Pizza> getById(@PathVariable List<Long> id) {
        List<Pizza> pizzas = pizzaRepository.findAllById(id);
        if (pizzas.isEmpty()) {
            throw new NoSuchElementException("Пиццы с указанными ID не найдены");
        }
        return pizzas;
    }
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Long id, @RequestBody @Valid Pizza updatedPizza) {
        Pizza existing = pizzaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пицца с id=" + id + " не найдена"));
        existing.setName(updatedPizza.getName());
        existing.setPrice(updatedPizza.getPrice());
        return pizzaRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пицца с id=" + id + " не найдена"));
        pizzaRepository.delete(pizza);
    }

}
