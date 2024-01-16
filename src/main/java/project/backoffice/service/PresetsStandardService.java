package project.backoffice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import project.backoffice.dto.PresetsStandardDTO;
import project.backoffice.entity.PresetsStandard;
import project.backoffice.enumeration.JsonTypeEnum;
import project.backoffice.helper.JsonHelper;
import project.backoffice.mapper.PresetsStandardMapper;
import project.backoffice.repository.PresetsStandardRepository;


@AllArgsConstructor
@Service
public class PresetsStandardService {
    private PresetsStandardRepository presetsStandardRepository;
    private PresetsStandardMapper presetsStandardMapper;
    public PresetsStandardDTO getPresetStandardByType(String type) throws JsonProcessingException {
        PresetsStandard presetsStandard = presetsStandardRepository.getPresetStandardByType(type);
        PresetsStandardDTO presetsStandardDTO = presetsStandardMapper.toDTO(presetsStandard);
        presetsStandardDTO.setJson(JsonHelper.getJsonStringAsObject(presetsStandard.getJson(), JsonTypeEnum.PRESETS_STANDARD));
        return presetsStandardDTO;
    }

//    public getById(String type) {
    // inspi library
//    }

}

