package com.safetynet.alerts.model.application;

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
public class ChildrenByAddressConstructorTest {
    private static String actualChildrenFirstName = "Julien";
    private static String actualChildrenLastName = "Cheviet";
    private static int actualChildrenAge = 10;
    private static String actualAdultFirstName = "Jerome";
    private static String actualAdultLastName = "Cheviet";

    private static String actualToString;

    private static List<ChildrenByAddress> childrenByAddressList = new ArrayList<>();
    private static List<AdultByAddress> adultByAddressList = new ArrayList<>();

    @InjectMocks
    private ChildrenByAddressConstructor childrenByAddressConstructor = new ChildrenByAddressConstructor();

    @Mock
    private ChildrenByAddress childrenByAddress;
    private AdultByAddress adultByAddress;

    @BeforeEach
    private void setUp() {
        childrenByAddress = new ChildrenByAddress(actualChildrenFirstName, actualChildrenLastName, actualChildrenAge);
        adultByAddress = new AdultByAddress(actualAdultFirstName, actualAdultLastName);

        childrenByAddressList.add(childrenByAddress);
        adultByAddressList.add(adultByAddress);
        actualToString = "{" +
                "Childrens=" + childrenByAddressList +
                ", Adults=" + adultByAddressList +
                '}';
    }

    @Test
    public void createChildrenByAddressConstructor() {
        childrenByAddressConstructor.setChildrenByAddressList(childrenByAddressList);
        childrenByAddressConstructor.setAdultByAddressList(adultByAddressList);

        assertEquals(childrenByAddressConstructor.getChildrenByAddressList().toString(), childrenByAddressList.toString());
        assertEquals(childrenByAddressConstructor.getAdultByAddressList().toString(), adultByAddressList.toString());
        assertEquals(childrenByAddressConstructor.toString(), actualToString);
    }

    @Test
    public void createChildrenByAddressConstructorWithConstructor() {
        childrenByAddressConstructor = new ChildrenByAddressConstructor(childrenByAddressList, adultByAddressList);


        assertEquals(childrenByAddressConstructor.getChildrenByAddressList().toString(), childrenByAddressList.toString());
        assertEquals(childrenByAddressConstructor.getAdultByAddressList().toString(), adultByAddressList.toString());
        assertEquals(childrenByAddressConstructor.toString(), actualToString);
    }

}
