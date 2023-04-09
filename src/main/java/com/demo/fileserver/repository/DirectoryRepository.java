package com.demo.fileserver.repository;

import com.demo.fileserver.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {

    boolean existsById(Long directoryId);
    List<Directory> findAllByLocationDirId(Long locationDirectoryId);
    void deleteAllByLocationDirId(Long locationDirectoryId);
}

