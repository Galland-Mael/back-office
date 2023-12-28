package project.backoffice.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.Entity.Library;
import project.backoffice.Entity.PresetsStandard;
import project.backoffice.Entity.User;
import project.backoffice.Exception.ApiException;
import project.backoffice.Exception.MessageExceptionEnum;
import project.backoffice.Repository.PresetsStandardRepository;


@AllArgsConstructor
@Service
@Transactional
public class PresetsStandardService {
    private PresetsStandardRepository presetsStandardRepository;

    public PresetsStandard getPresetStandardByType(String type) {
        return presetsStandardRepository.getPresetStandardByType(type);
    }


}

