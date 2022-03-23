package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.model.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PersonMapperTest {
    private final List<PersonDTO> personDTOList = new ArrayList<>();
    private final List<Person> personList = new ArrayList<>();

    @InjectMocks
    private PersonMapper personMapper = new PersonMapper();
    @Mock
    private PersonDTO expectPersonDTO;
    @Mock
    private Person expectPerson;

    @BeforeEach
    private void setUp() {
        String expectFirstName = "Jerome";
        String expectLastName = "Cheviet";
        String expectAddress = "1 rue du Puit";
        String expectCity = "Cluver";
        int expectZip = 97451;
        String expectEmail = "jcheviet@email.com";
        String expectPhone = "123-456-7890";

        expectPersonDTO = new PersonDTO(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectPhone,
                expectEmail);

        expectPerson = new Person(expectFirstName,
                expectLastName,
                expectAddress,
                expectCity,
                expectZip,
                expectPhone,
                expectEmail);
    }

    @Test
    public void testMapDtoToDomainPerson() {
        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        String actualAddress = "1 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "jcheviet@email.com";
        String actualPhone = "123-456-7890";
        PersonDTO actualPersonDTO = new PersonDTO(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualPhone,
                actualEmail);

        Person actualPerson = personMapper.mapDtoToDomainPerson(actualPersonDTO);

        assertEquals(expectPerson.getFirstName(), actualPerson.getFirstName());
        assertEquals(expectPerson.getLastName(), actualPerson.getLastName());
        assertEquals(expectPerson.getAddress(), actualPerson.getAddress());
        assertEquals(expectPerson.getCity(), actualPerson.getCity());
        assertEquals(expectPerson.getZip(), actualPerson.getZip());
        assertEquals(expectPerson.getPhone(), actualPerson.getPhone());
        assertEquals(expectPerson.getEmail(), actualPerson.getEmail());
        assertEquals(expectPerson.toString(), actualPerson.toString());
    }

    @Test
    public void testMapDtoToDomainPersonList() {
        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        String actualAddress = "1 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "jcheviet@email.com";
        String actualPhone = "123-456-7890";
        PersonDTO actualPersonDTO = new PersonDTO(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualPhone,
                actualEmail);
        personDTOList.add(actualPersonDTO);
        personList.add(expectPerson);

        List<Person> actualPersonList = personMapper.mapDtoToDomainPersonList(personDTOList);

        assertEquals(personList.toString(), actualPersonList.toString());
    }

    @Test
    public void testMapDomainPersonToDto() {
        String actualFirstName = "Jerome";
        String actualLastName = "Cheviet";
        String actualAddress = "1 rue du Puit";
        String actualCity = "Cluver";
        int actualZip = 97451;
        String actualEmail = "jcheviet@email.com";
        String actualPhone = "123-456-7890";
        Person actualPerson = new Person(actualFirstName,
                actualLastName,
                actualAddress,
                actualCity,
                actualZip,
                actualPhone,
                actualEmail);

        PersonDTO actualPersonDTO = personMapper.mapDomainPersonToDto(actualPerson);

        assertEquals(expectPersonDTO.getFirstName(), actualPersonDTO.getFirstName());
        assertEquals(expectPersonDTO.getLastName(), actualPersonDTO.getLastName());
        assertEquals(expectPersonDTO.getAddress(), actualPersonDTO.getAddress());
        assertEquals(expectPersonDTO.getCity(), actualPersonDTO.getCity());
        assertEquals(expectPersonDTO.getZip(), actualPersonDTO.getZip());
        assertEquals(expectPersonDTO.getPhone(), actualPersonDTO.getPhone());
        assertEquals(expectPersonDTO.getEmail(), actualPersonDTO.getEmail());
        assertEquals(expectPersonDTO.toString(), actualPersonDTO.toString());
    }

}
