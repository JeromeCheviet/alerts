package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.dto.PersonDTO;
import com.safetynet.alerts.model.core.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    private static final Logger logger = LogManager.getLogger(PersonMapper.class);

    public Person mapDtoToDomainPerson(PersonDTO personDTO) {
        logger.debug("PersonMapper mapDtoToDomainPerson");
        logger.debug("personDTO : " + personDTO);
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setAddress(personDTO.getAddress());
        person.setCity(personDTO.getCity());
        person.setZip(personDTO.getZip());
        person.setPhone(personDTO.getPhone());
        person.setEmail(personDTO.getEmail());

        logger.debug("return " + person.toString());
        return person;
    }

    public List<Person> mapDtoToDomainPersonList(List<PersonDTO> personDTOArrayList) {
        logger.debug("PersonMapper mapDtoToDomainPersonList");
        logger.debug("personDTOArrayList : " + personDTOArrayList);

        return personDTOArrayList.stream().map(personDTO -> mapDtoToDomainPerson(personDTO)).collect(Collectors.toList());
    }

    public PersonDTO mapDomainPersonToDto(Person person) {
        logger.debug("PersonMapper mapDomainPersonToDto");
        logger.debug("person : " + person.toString());
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setAddress(person.getAddress());
        personDTO.setCity(person.getCity());
        personDTO.setZip(person.getZip());
        personDTO.setPhone(person.getPhone());
        personDTO.setEmail(person.getEmail());

        logger.debug("return " + personDTO.toString());
        return personDTO;
    }
}
