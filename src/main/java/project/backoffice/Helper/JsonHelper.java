package project.backoffice.Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import project.backoffice.Enum.JsonTypeEnum;
import project.backoffice.Exception.ApiException;
import project.backoffice.Exception.MessageExceptionEnum;


public class JsonHelper {

    public static Object getJsonStringAsObject(String uglyJson, JsonTypeEnum jsonTypeEnum) throws JsonProcessingException {
        String prettyJson = JsonHelper.prettifyJsonString(uglyJson, jsonTypeEnum);
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
    public static String prettifyJsonString(String uglyJsonString, JsonTypeEnum jsonTypeEnum) throws JsonProcessingException {
        switch (jsonTypeEnum) {
            case LIBRARY:
                return prettifyJsonStringForLibrary(uglyJsonString);
            case PRESETS_STANDARD:
                return prettifyJsonStringForPresetsStandard(uglyJsonString);
            default:
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        MessageExceptionEnum.JSON_TYPE_NOT_FOUND);
        }
    }

    public static String prettifyJsonStringForLibrary(String uglyJsonString) throws JsonProcessingException {
        String jsonString = formatLibraryJson(uglyJsonString);
        return formatJsonString(jsonString);
    }

    private static String prettifyJsonStringForPresetsStandard(String uglyJsonString) throws JsonProcessingException {
        String cleanedJsonString = uglyJsonString.replaceAll("\\\\r\\\\n", "").replaceAll("\\\\", "");
        return formatJsonString(cleanedJsonString);
    }

    public static String formatLibraryJson(String jsonData) throws JsonProcessingException {
        try {
            jsonData = jsonData.replaceAll("&quot;", "");
            jsonData = createLibraryJsonString(new JSONObject(jsonData).toString()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    private static JSONObject createLibraryJsonString(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

    private static String formatJsonString(String jsonString) throws JSONException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(jsonString, Object.class);
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return prettyJson;
    }
}
