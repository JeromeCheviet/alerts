package com.safetynet.alerts.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

//TODO Tests and cleaning
/**
 * Class to load Json Data from file
 */
@Repository
public class LoadData implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(LoadData.class);


    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public void readJson() {
        logger.debug("LoadData readJson");

        Resource resource = resourceLoader.getResource("classpath:data.json");
        String jsonData = null;
        try {
            logger.debug("Get data from file");
            InputStream inputStream = resource.getInputStream();
            byte[] fileData = FileCopyUtils.copyToByteArray(inputStream);
            jsonData = new String(fileData);
        } catch (IOException e) {
            logger.error("Error with file 'data.json' : " + e);
        }

        if (jsonData == null) {
            logger.error("No data get from file. Exit with code error 1");
            System.exit(1);
        }

        logger.debug("data from file content : ");
        logger.debug(jsonData);

        JsonNode node = null;
        try {
            logger.debug("Read Json Object");
            node = objectMapper.readTree(jsonData);
        } catch (JsonProcessingException e) {
            logger.error("Error to process Json Tree : " + e);
        }

        if (node == null) {
            logger.error("Variable node is null. Exit with code error 1");
            System.exit(1);
        }

        personRepository.setModel(node.get("persons"));
        fireStationRepository.setModel(node.get("firestations"));
        medicalRecordRepository.setModel(node.get("medicalrecords"));

    }

    @Override
    public void run(String... args) throws Exception {
        readJson();
    }
}
