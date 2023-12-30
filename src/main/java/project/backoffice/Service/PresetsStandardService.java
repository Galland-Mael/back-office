package project.backoffice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import project.backoffice.DTO.PresetsStandardDTO;
import project.backoffice.Entity.PresetsStandard;
import project.backoffice.Helper.JsonHelper;
import project.backoffice.Mapper.PresetsStandardMapper;
import project.backoffice.Repository.PresetsStandardRepository;


@AllArgsConstructor
@Service
public class PresetsStandardService {
    private PresetsStandardRepository presetsStandardRepository;
    private PresetsStandardMapper presetsStandardMapper;
    public PresetsStandardDTO getPresetStandardByType(String type) throws JsonProcessingException {
        PresetsStandard presetsStandard = presetsStandardRepository.getPresetStandardByType(type);
        PresetsStandardDTO presetsStandardDTO = presetsStandardMapper.toDTO(presetsStandard);
        presetsStandardDTO.setJson(JsonHelper.getJsonAsObject(presetsStandard.getJson()));
        return presetsStandardDTO;
    }

//    public getById(String type) {
    // inspi library
//    }

}

