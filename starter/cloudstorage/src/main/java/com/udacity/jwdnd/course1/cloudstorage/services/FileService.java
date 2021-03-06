package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<String> getAllFilenames() {
        return fileMapper.selectAllFilenames();
    }

    public void addFile(File file) {
        fileMapper.insertFile(file);
    }

    public boolean deleteFile(int fileId) {
        fileMapper.deleteFile(fileId);
        return fileMapper.selectFileById(fileId) == null;
    }

    public boolean isFileNameAvailable(String filename, Integer userid) {
        return fileMapper.selectFileByFilename(filename, userid) == null;
    }

    public File getFileById(Integer fileId) {
        return fileMapper.selectFileById(fileId);
    }

    public List<File> getAllFiles(Integer userid){
        return fileMapper.selectFilesByUserid(userid);
    }

    public File getFileByName(String name, Integer userid) {
        return fileMapper.selectFileByFilename(name, userid);
    }
}
