package project.backoffice.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.dto.FirmwareCreationDTO;
import project.backoffice.dto.FirmwareDTO;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.dto.LightFirmwareDto;
import project.backoffice.entity.Firmware;
import project.backoffice.entity.Product;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.helper.StringHelper;
import project.backoffice.mapper.FirmwareMapper;
import project.backoffice.mapper.ProductMapper;
import project.backoffice.repository.FirmwareRepository;
import project.backoffice.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@NoArgsConstructor
@Service
public class FirmwareService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FirmwareRepository firmwareRepository;

    @Autowired
    private FirmwareMapper firmwareMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private FileService fileService;

    @Value("${file.storage-dir}")
    private String storageDir;

    public FirmwareVersionDTO getLatestFirmwareVersion() {
        return firmwareMapper.versionToDTO(firmwareRepository.findLastestFirmware());
    }

    public FirmwareDTO getFirmwareById(Long id) {
        Firmware firmware = firmwareRepository.findById(id).orElseThrow(
                ()->  new ApiException(
                        HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.FIRMWARE_NOT_FOUND, id)));
        fileService.readFile(firmware.getFilePath());
        FirmwareDTO firmwareDTO = firmwareMapper.toDTO(firmware);
        firmwareDTO.setContent(fileService.readFile(firmware.getFilePath()));
        firmwareDTO.setProductDTO(productMapper.toLightDTO(productRepository.findByFirmwareId(firmware.getId())));
        return firmwareDTO;
    }

    public LightFirmwareDto createFirmware(FirmwareCreationDTO firmwareDTO) throws ParseException {
        String originalFilename = firmwareDTO.getFile().getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filenameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String fileName = filenameWithoutExtension + "_" + new Date().getTime() + extension;
        String filePath = storageDir + "/" + fileName;
        try {
            firmwareDTO.getFile().transferTo(new File(filePath));
        } catch (IOException e) {
            System.out.println(e);
        }
        Firmware firmware = new Firmware();

        String dateString = firmwareDTO.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(dateString);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

        firmware.setDate(sqlDate);
        firmware.setVersion(firmwareDTO.getVersion());
        firmware.setFilePath(fileName);

        Firmware savedFirmware = firmwareRepository.save(firmware);
        Product product = productRepository.findById(Long.valueOf(firmwareDTO.getProductId())).orElseThrow(
                ()->  new ApiException(
                        HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.PRODUCT_NOT_FOUND, firmwareDTO.getProductId())));
        product.setFirmware(savedFirmware);
        productRepository.save(product);

        return firmwareMapper.toLightDTO(savedFirmware);
    }

}
