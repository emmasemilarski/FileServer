package com.demo.fileserver.controller;

import com.demo.fileserver.entity.File;
import com.demo.fileserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/files")
public class FileController {

    @Autowired
    private FileService fileService;

    // Get a file
    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<File> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(fileService.findById(id));
    }
    
    // Get all files
    @GetMapping
    public @ResponseBody ResponseEntity<List<File>> getAll() {
        return ResponseEntity.ok(fileService.findAll());
    }

    // Create and upload a file
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<File> createFile(@RequestBody MultipartFile file, Long directoryId) throws Exception {
        return ResponseEntity.ok(fileService.createFile(file, directoryId));
    }

    // Download a file
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long fileId) throws FileNotFoundException {
        return ResponseEntity.ok(fileService.downloadFile(fileId));
    }

    // Delete a file
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long fileId) throws Exception {
        return ResponseEntity.ok(fileService.deleteFile(fileId));
    }
}
