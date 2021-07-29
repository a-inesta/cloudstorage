package com.udacity.jwdnd.course1.cloudstorage.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class TestUserMapper {

    @Test
    void test() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath() + "files";
        System.out.println(path);
    }
}
