package com.bhxx.goldenstraw.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public final class FileTypeJudge {  
      
    /** 
     * Constructor 
     */  
    private FileTypeJudge() {}  
      
    private static String bytesToHexString(byte[] src){
          
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {     
            return null;     
        }     
        for (int i = 0; i < src.length; i++) {     
            int v = src[i] & 0xFF;     
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {     
                stringBuilder.append(0);     
            }     
            stringBuilder.append(hv);     
        }     
        return stringBuilder.toString();     
    }  
     
    private static String getFileContent(String filePath) {
          
        byte[] b = new byte[1024];  
          
        InputStream inputStream = null;
          
        try {  
            inputStream = new FileInputStream(filePath);
            inputStream.read(b, 0, 1024);  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {  
            if (inputStream != null) {  
                try {  
                    inputStream.close();  
                } catch (IOException e) {
                    e.printStackTrace();  
                }  
            }  
        }  
        return bytesToHexString(b);  
    }  
      
    public static FileType getType(String filePath) {
          
        String fileHead = getFileContent(filePath);
          
        if (fileHead == null || fileHead.length() == 0) {  
            return null;  
        }  
          
        fileHead = fileHead.toUpperCase(Locale.CHINA);
          
        FileType[] fileTypes = FileType.values();
          
        for (FileType type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {  
                return type;  
            }  
        }  
        return null;  
    }  
}  
