package project.backoffice.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.entity.Quality;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.helper.StringHelper;
import project.backoffice.mapper.QualityMapper;
import project.backoffice.repository.QualityRepository;

@Service
@AllArgsConstructor
public class QualityService {
    private final QualityRepository qualityRepository;
    private final QualityMapper qualityMapper;

    public Quality getQualityById(Long id) {
        return qualityRepository.findById(id).orElseThrow(() ->  new ApiException(HttpStatus.NOT_FOUND,
                StringHelper.format(MessageExceptionEnum.QUALITY_NOT_FOUND, id)));
    }
}
