package com.baidu.hina.web.tail.util;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;


public class FilePathValidTest {
   
    @Test
    public void testvalid(){
        String  filePath = "/home/work/../hahahah" ;
        System.out.println(filePath);
        Assert.assertFalse(FilePathValid.valid(filePath));
        
        File newFile = new File(new File(filePath),"123.txt");
        System.out.println(newFile.getAbsolutePath());
        Assert.assertFalse(FilePathValid.valid(newFile.getAbsolutePath()));
    }
}
