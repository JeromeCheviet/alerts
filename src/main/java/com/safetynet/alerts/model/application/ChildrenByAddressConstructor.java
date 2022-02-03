package com.safetynet.alerts.model.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChildrenByAddressConstructor {

    private static final Logger logger = LogManager.getLogger(ChildrenByAddressConstructor.class);

    private List<ChildrenByAddress> childrenByAddressList;
    private List<AdultByAddress> adultByAddressList;

    public ChildrenByAddressConstructor() {

    }

    public ChildrenByAddressConstructor(List<ChildrenByAddress> childrenByAddressList, List<AdultByAddress> adultByAddressList) {
        logger.debug("ChildrenByAddressConstructor constructor");
        logger.debug("childrenByAddressList : " + childrenByAddressList + " | adultByAddressList : " + adultByAddressList);
        this.childrenByAddressList = childrenByAddressList;
        this.adultByAddressList = adultByAddressList;
    }

    public List<ChildrenByAddress> getChildrenByAddressList() {
        return childrenByAddressList;
    }

    public void setChildrenByAddressList(List<ChildrenByAddress> childrenByAddressList) {
        this.childrenByAddressList = childrenByAddressList;
    }

    public List<AdultByAddress> getAdultByAddressList() {
        return adultByAddressList;
    }

    public void setAdultByAddressList(List<AdultByAddress> adultByAddressList) {
        this.adultByAddressList = adultByAddressList;
    }

    @Override
    public String toString() {
        return "{" +
                "Childrens=" + childrenByAddressList +
                ", Adults=" + adultByAddressList +
                '}';
    }
}
