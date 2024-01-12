package project.backoffice.Service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import project.backoffice.DTO.PresetsStandardDTO;
import project.backoffice.Entity.PresetsStandard;
import project.backoffice.Enum.JsonTypeEnum;
import project.backoffice.Helper.JsonHelper;
import project.backoffice.Mapper.PresetsStandardMapper;
import project.backoffice.Repository.PresetsStandardRepository;


@AllArgsConstructor
@Service
public class PresetsStandardService {
    private PresetsStandardRepository presetsStandardRepository;
    private PresetsStandardMapper presetsStandardMapper;
    public PresetsStandardDTO getPresetStandardByType(String type) throws Exception {
        PresetsStandard presetsStandard = presetsStandardRepository.getPresetStandardByType(type);
        PresetsStandardDTO presetsStandardDTO = presetsStandardMapper.toDTO(presetsStandard);
        presetsStandardDTO.setJson(JsonHelper.getJsonStringAsObject(presetsStandard.getJson(), JsonTypeEnum.PRESETS_STANDARD));
        return presetsStandardDTO;
    }

}

