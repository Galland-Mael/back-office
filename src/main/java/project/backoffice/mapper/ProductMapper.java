package project.backoffice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.backoffice.dto.ProductDTO;
import project.backoffice.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "firmware.id", target = "firmwareId")
    @Mapping(source = "firmware.version", target = "firmwareVersion")
    ProductDTO toDTO(Product product);
}

