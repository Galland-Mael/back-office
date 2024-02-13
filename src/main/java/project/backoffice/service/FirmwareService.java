package project.backoffice.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.dto.FirmwareDTO;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.entity.Firmware;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.helper.StringHelper;
import project.backoffice.mapper.FirmwareMapper;
import project.backoffice.repository.FirmwareRepository;


@AllArgsConstructor
@Service
public class FirmwareService {
    private FirmwareRepository firmwareRepository;
    private FirmwareMapper firmwareMapper;
    private FileService fileService;

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
        return firmwareDTO;
    }
}
