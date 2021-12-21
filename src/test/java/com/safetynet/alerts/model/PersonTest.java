package com.safetynet.alerts.model;

import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest {

    @Test
    public void createAPerson() {

        Person person = new Person(
                1,
                "Jérôme",
                "Cheviet",
                "1980/09/02",
                "1 allée du Chateau",
                "Ranton",
                86200,
                "06.07.08.09.10",
                "jcheviet@hotmail.com"
        );

        assertEquals(person.getId(), 1);
        assertEquals(person.getFirstName(), "Jérôme");
        assertEquals(person.getLastName(), "Cheviet");
        assertEquals(person.getBirthday(), "1980/09/02");
        assertEquals(person.getAddress(), "1 allée du Chateau");
        assertEquals(person.getCity(), "Ranton");
        assertEquals(person.getZip(), 86200);
        assertEquals(person.getPhone(), "06.07.08.09.10");
        assertEquals(person.getMail(), "jcheviet@hotmail.com");
    }
}
