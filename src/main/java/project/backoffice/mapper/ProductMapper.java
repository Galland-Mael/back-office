package project.backoffice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.backoffice.dto.ProductDTO;
import project.backoffice.entity.Product;

@Mapper(componentModel = "spring",
    uses = {FirmwareMapper.class}
)
public interface ProductMapper {
    ProductDTO toDTO(Product product);
}

