package project.backoffice.mapper;

import org.mapstruct.Mapper;
import project.backoffice.dto.QualityDTO;
import project.backoffice.entity.Quality;

@Mapper(componentModel = "spring")
public interface QualityMapper {
    QualityDTO toDTO(Quality quality);

    Quality toEntity(QualityDTO qualityDTO);

}
