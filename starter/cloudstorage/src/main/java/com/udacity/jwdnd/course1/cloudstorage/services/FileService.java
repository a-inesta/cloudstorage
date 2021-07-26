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

    public int deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }

    public boolean isFileNameAvailable(String filename) {
        return fileMapper.selectFileByFilename(filename) == null;
    }

    public List<File> getAllFiles(){
        return fileMapper.selectAllFiles();
    }

    public File getFileByName(String name) {
        return fileMapper.selectFileByFilename(name);
    }
}
