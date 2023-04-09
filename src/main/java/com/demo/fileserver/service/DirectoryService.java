package com.demo.fileserver.service;

import com.demo.fileserver.entity.Directory;
import com.demo.fileserver.entity.File;
import com.demo.fileserver.repository.DirectoryRepository;
import com.demo.fileserver.repository.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DirectoryService {

    @Autowired
    DirectoryRepository directoryRepository;

    @Autowired
    FileRepository fileRepository;

    public List<Directory> findAll() {
        return directoryRepository.findAll();
    }

    public Directory createDirectory(String directoryName, Long locationDirectoryId) throws Exception {
        Directory newDirectory = new Directory();
        newDirectory.setDirectoryName(directoryName);
        newDirectory.setCreationDate(LocalDate.now());
        if (locationDirectoryId != null && directoryRepository.existsById(locationDirectoryId)) newDirectory.setLocationDirId(locationDirectoryId);
        else if (locationDirectoryId == null) newDirectory.setLocationDirId(null);
        else throw new Exception("The directory, where you want to create the new directory, does not exist.");

        return directoryRepository.save(newDirectory);
    }

    public List<Object> listingContent(Long directoryId) throws Exception {
        if (directoryRepository.existsById(directoryId)) {
            List<Object> allContent = new ArrayList<>();
            List<File> allFiles = fileRepository.findAllByDirectoryId(directoryId);
            List<Directory> allDirectories = directoryRepository.findAllByLocationDirId(directoryId);
            allContent.addAll(allDirectories);
            allContent.addAll(allFiles);
            return allContent;
        } else {
            throw new Exception("Directory with this id does not exist.");
        }
    }

    public boolean deleteDirectory(Long directoryId) throws Exception {
        if (fileRepository.findAllByDirectoryId(directoryId).isEmpty() && directoryRepository.findAllByLocationDirId(directoryId).isEmpty()) {
            directoryRepository.deleteById(directoryId);
            return Boolean.TRUE;
        } else {
            throw new Exception("Directory is not empty!");
        }
    }
}
