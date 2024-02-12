package project.backoffice.mapper;

import org.mapstruct.Mapper;
import project.backoffice.dto.QualityDTO;
import project.backoffice.entity.Quality;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QualityMapper {
    QualityDTO toDTO(Quality quality);

    List<QualityDTO> toListDTO(List<Quality> qualities);
}
