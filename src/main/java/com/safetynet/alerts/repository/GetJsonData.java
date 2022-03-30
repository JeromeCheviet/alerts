package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Interface linked to the repositories classes.
 */
public interface GetJsonData {
    /**
     * Set Json node into model
     *
     * @param node - JsonNode send by LoadData.readJson() method.
     */
    void setModel(JsonNode node);
}
