package project.backoffice.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.backoffice.Repository.LibraryRepository;
import project.backoffice.entity.Library;

@AllArgsConstructor
@Service
@Transactional
public class LibraryService {
    private LibraryRepository libraryRepository;

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }
}
