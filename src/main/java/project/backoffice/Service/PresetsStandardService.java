package project.backoffice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.Entity.PresetsStandard;
import project.backoffice.Exception.ApiException;
import project.backoffice.Exception.MessageExceptionEnum;
import project.backoffice.Repository.PresetsStandardRepository;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Arrays;


@AllArgsConstructor
@Service
@Transactional
public class PresetsStandardService {
    private PresetsStandardRepository presetsStandardRepository;

    public PresetsStandard getPresetStandardByType(String type) throws JsonProcessingException {
        PresetsStandard result = presetsStandardRepository.getPresetStandardByType(type);

        String jsonStringFromDatabase = result.getJson();
//        String cleanedJsonString = jsonStringFromDatabase.replaceAll("\\\\r\\\\n", "");
//        cleanedJsonString = cleanedJsonString.replaceAll("\\\\", "");
//        result.setJson(cleanedJsonString);
        System.out.println(result.getJson());
        return result;

    }


}

