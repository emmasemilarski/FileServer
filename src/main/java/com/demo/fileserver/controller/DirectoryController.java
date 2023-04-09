package com.demo.fileserver.controller;

import com.demo.fileserver.entity.Directory;
import com.demo.fileserver.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/directories")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    // Get all directories
    @GetMapping
    public @ResponseBody ResponseEntity<List<Directory>> getAll() {
        return ResponseEntity.ok(directoryService.findAll());
    }

    // Create a directory
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Directory> createDirectory(@RequestParam("directoryName") String directoryName, Long locationDirectoryId) throws Exception {
        return ResponseEntity.ok(directoryService.createDirectory(directoryName, locationDirectoryId));
    }

    // List directory contents
    @GetMapping(path = "/content/{id}")
    public @ResponseBody ResponseEntity<List<Object>> getDirectoryContent(@PathVariable("id") Long locationDirectoryId) throws Exception {
        return ResponseEntity.ok(directoryService.listingContent(locationDirectoryId));
    }

    // Deleting a directory
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long locationDirectoryId) throws Exception {
        return ResponseEntity.ok(directoryService.deleteDirectory(locationDirectoryId));
    }
}
