package project.backoffice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.mapper.FirmwareMapper;
import project.backoffice.repository.FirmwareRepository;


@AllArgsConstructor
@Service
public class FirmwareService {
    private FirmwareRepository firmwareRepository;
    private FirmwareMapper firmwareMapper;

    public FirmwareVersionDTO getLatestFirmwareVersion() {
        return firmwareMapper.versionToDTO(firmwareRepository.findLastestFirmware());
    }
}
