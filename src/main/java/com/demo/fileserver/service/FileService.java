package com.demo.fileserver.service;

import com.demo.fileserver.entity.File;
import com.demo.fileserver.repository.DirectoryRepository;
import com.demo.fileserver.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired DirectoryRepository directoryRepository;

    public File findById(Long fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        return file.orElse(null);
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public File createFile(MultipartFile file, Long directoryId) throws Exception {
        File newFile = new File();
        newFile.setFilename(file.getOriginalFilename());
        newFile.setCreationDate(LocalDate.now());
        newFile.setlastAccessDate(LocalDate.now());
        newFile.setSize(file.getSize());
        newFile.setDirectoryId(directoryId);

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }
            fileRepository.save(newFile);
            Path targetLocation = Path.of(("files/") + newFile.getId());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileRepository.save(newFile);
    }

    public Resource downloadFile(Long fileId) throws FileNotFoundException {
        File file = fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));
        Path filePath = Paths.get("files/" + fileId);
        Resource fileResource;
        try {
            fileResource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("File not found");
        }
        file.setlastAccessDate(LocalDate.now());
        fileRepository.save(file);
        return fileResource;
    }

    public boolean deleteFile(Long fileId) throws Exception {
        try {
            fileRepository.deleteById(fileId);
            return Boolean.TRUE;
        } catch (final Exception e) {
            throw new Exception(e);
        }
    }
}
