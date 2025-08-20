package org.example.sbecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // Get the original file name
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || !originalFileName.contains(".")) {
            throw new IOException("File must have an extension");
        }

        // Generate a unique file name
        String randomId = UUID.randomUUID().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String fileName = randomId + fileExtension;
        String filePath = path + File.separator + fileName;

        // Ensure the directory exists
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Upload the file to the server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Return the new file name
        return fileName;
    }

}
