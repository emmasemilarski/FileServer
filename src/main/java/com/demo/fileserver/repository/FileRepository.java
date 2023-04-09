package com.demo.fileserver.repository;

import com.demo.fileserver.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllByDirectoryId(Long directoryId);
    void deleteAllByDirectoryId(Long directoryId);
}
