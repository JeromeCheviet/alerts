package com.safetynet.alerts.model.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildrenByAddressTest {
    private static String actualFirstName;
    private static String actualLastName;
    private static int actualAge;
    private static String actualToString;

    private ChildrenByAddress childrenByAddress = new ChildrenByAddress();

    @BeforeEach
    private void setUp() {
        actualFirstName = "Jerome";
        actualLastName = "Cheviet";
        actualAge = 41;
        actualToString = "{" +
                "firstName='" + actualFirstName + '\'' +
                ", lastName='" + actualLastName + '\'' +
                ", age=" + actualAge +
                '}';
    }

    @Test
    public void createChildrenByAddress() {
        childrenByAddress.setFirstName(actualFirstName);
        childrenByAddress.setLastName(actualLastName);
        childrenByAddress.setAge(actualAge);

        assertEquals(childrenByAddress.getFirstName(), actualFirstName);
        assertEquals(childrenByAddress.getLastName(), actualLastName);
        assertEquals(childrenByAddress.getAge(), actualAge);
        assertEquals(childrenByAddress.toString(),actualToString);
    }

    @Test
    public void createChildrenByAddressWithConstructor() {
        childrenByAddress = new ChildrenByAddress(actualFirstName, actualLastName, actualAge);

        assertEquals(childrenByAddress.getFirstName(), actualFirstName);
        assertEquals(childrenByAddress.getLastName(), actualLastName);
        assertEquals(childrenByAddress.getAge(), actualAge);
        assertEquals(childrenByAddress.toString(),actualToString);
    }

}
