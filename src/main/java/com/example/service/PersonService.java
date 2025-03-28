package com.example.service;

import com.example.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private static final String JSON_FILE = "person.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Person> getAllPersons() throws IOException {
        File file = new File(JSON_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Person>>() {});//return list of persons in json
    }

    public Optional<Person> getPersonByName(String name) throws IOException {
        return getAllPersons().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Person createPerson(Person person) throws IOException {
        List<Person> persons = getAllPersons();
        persons.add(person);
        saveAllPersons(persons);//write to file
        return person;
    }

    public Optional<Person> updatePerson(String name, Person updatedPerson) throws IOException {
        List<Person> persons = getAllPersons();
        Optional<Person> existingPerson = persons.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();

        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setAge(updatedPerson.getAge());
            person.setCity(updatedPerson.getCity());
            saveAllPersons(persons);
            return Optional.of(person);
        }
        return Optional.empty();
    }

    public boolean deletePerson(String name) throws IOException {
        List<Person> persons = getAllPersons();
        boolean removed = persons.removeIf(p -> p.getName().equalsIgnoreCase(name));
        if (removed) {
            saveAllPersons(persons);
        }
        return removed;
    }

    private void saveAllPersons(List<Person> persons) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(JSON_FILE), persons);
    }
}