package project.backoffice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.backoffice.dto.LightProductDTO;
import project.backoffice.dto.ProductDTO;
import project.backoffice.entity.Product;

@Mapper(componentModel = "spring",
    uses = {FirmwareMapper.class}
)
public interface ProductMapper {
    @Mapping(source = "firmware", target = "lightFirmwareDto")
    ProductDTO toDTO(Product product);

    LightProductDTO toLightDTO(Product product);

    Product toEntity(ProductDTO productDTO);
}

