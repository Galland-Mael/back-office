package project.backoffice.Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import project.backoffice.Exception.ApiException;
import project.backoffice.Exception.MessageExceptionEnum;

public class JsonHelper {

    public static Object getJsonAsObject(String uglyJson) throws JsonProcessingException {
        String prettyJson = JsonHelper.prettyPrintJsonUsingDefaultPrettyPrinter(uglyJson);
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = null;
        try {
            jsonObject = objectMapper.readValue(prettyJson, Object.class);
        } catch (JsonProcessingException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    MessageExceptionEnum.LIBRARY_ALREADY_EXISTS_FOR_USER);
        }
        return jsonObject;
    }
    public static String prettyPrintJsonUsingDefaultPrettyPrinter(String uglyJsonString) throws JsonProcessingException {
        String cleanedJsonString = uglyJsonString.replaceAll("\\\\r\\\\n", "").replaceAll("\\\\", "");
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(cleanedJsonString, Object.class);
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return prettyJson;
    }
}
