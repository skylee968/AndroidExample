package com.orangestudio.mobilereader.Utils;

import android.content.Context;

import java.io.File;

public final class FilesUtils {
	private FilesUtils(){
		
	}
	public static String getExtension(File f) {
        try {
        	String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
		} catch (Exception e) {
		}
        return null;
    }
    public static void createDirectory(Context _context, String _uri,
                                       boolean isOverride) {
        File directory = new File(_uri);
        if (isOverride) {
            if (directory.exists()) {
                directory.delete();
            }
        } else {
            if (directory.exists()) {
                return;
            } else {
                directory.mkdirs();
            }
        }
    }
    public static boolean isExistedFile(String fileName) {
        if(fileName == null && fileName.trim().length() < 1) {
            return false;
        }
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }
}
