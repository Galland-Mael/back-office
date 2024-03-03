package project.backoffice.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@NoArgsConstructor
public class FileService {

    @Value("${file.storage-dir}")
    private String storageDir;

    public String readFile(String filePath) {
        String fullPath = storageDir + "/" + filePath;
        String content = "";
        try {
            content = Files.readString(Paths.get(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
