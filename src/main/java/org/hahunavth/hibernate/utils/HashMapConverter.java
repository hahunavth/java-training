//package org.hahunavth.hibernate.utils;
//
//
//import javax.persistence.AttributeConverter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {
//
//    @Override
//    public String convertToDatabaseColumn(Map<String, Object> customerInfo) {
//
//        String customerInfoJson = null;
//        try {
//            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
//        } catch (final JsonProcessingException e) {
//            logger.error("JSON writing error", e);
//        }
//
//        return customerInfoJson;
//    }
//
//    @Override
//    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {
//
//        Map<String, Object> customerInfo = null;
//        try {
//            customerInfo = objectMapper.readValue(customerInfoJSON,
//            	new TypeReference<HashMap<String, Object>>() {});
//        } catch (final IOException e) {
////            logger.error("JSON reading error", e);
//        }
//
//        return customerInfo;
//    }
//}