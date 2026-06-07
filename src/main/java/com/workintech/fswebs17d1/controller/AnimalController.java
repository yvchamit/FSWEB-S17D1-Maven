package com.workintech.fswebs17d1.controller;
import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//http://localhost:workintech/animal

@RestController
@RequestMapping(path="/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;

    @Value("${project.developer.fullname}")
    private String developerName;

    @Value("${course.name}")
    private String courseName;


    @PostConstruct //Sadece metodlar icin
    public void loadAll(){
        System.out.print("postconstruct started");
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1, "Monkey"));
    }


    @GetMapping("/config")
    public String getConfigValues(){
        return developerName + " >> " + courseName;
    }

    @GetMapping
    public List<Animal> getAnimals(){
        System.out.println("<---------- animals get all tirggered ---------->");
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") int id){
        if(id<0){
            System.out.println("Id can not be less than zero! | Id= " + id);
            return null;
        }
        return this.animals.get(id);
    }


    @PostMapping
    public void addAanimals(@RequestBody Animal animal){
        System.out.println("add animal is triggeredd");
        this.animals.put(animal.getId(), animal);
    }


    @PutMapping("{id}")
    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal newAnimal){
        this.animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable("id") int id){
        this.animals.remove(id);
    }

}
