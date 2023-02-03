package com.works.services;

import com.works.entities.Note;
import com.works.entities.Person;
import com.works.repositories.NoteRepository;
import com.works.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SagaService {


    final NoteRepository noteRepository;
    final PersonRepository personRepository;

    public Map save() {
        Map map = new HashMap();

        Note note = new Note();
        note.setTitle("New Note");

        Person person = new Person();
        person.setName("Veli Bilirim");

        noteRepository.save(note);
        personRepository.save(person);

        map.put("status", true);
        return map;
    }

}
