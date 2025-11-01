package me.smorodin.exam.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

@Component
public class FileExtensionUtils {

    public String getExtension(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "";
        }

        return getExtension(file.getOriginalFilename());
    }

    public String getExtension(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return "";
        }

        String cleanFileName = new File(fileName).getName();
        int lastDotIndex = cleanFileName.lastIndexOf(".");

        if (lastDotIndex > 0 && lastDotIndex < cleanFileName.length() - 1) {
            return cleanFileName.substring(lastDotIndex + 1).toLowerCase();
        }

        return "";
    }

    public String getExtensionWithDot(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "";
        }

        return getExtensionWithDot(file.getOriginalFilename());
    }

    public String getExtensionWithDot(String fileName) {
        String extension = getExtension(fileName);
        return extension.isEmpty() ? "" : "." + extension;
    }

    public boolean hasExtension(MultipartFile file, String... expectedExtensions) {
        String actualExtension = getExtension(file);

        if (actualExtension.isEmpty()) {
            return false;
        }

        for (String expected : expectedExtensions) {
            String cleanExpected = expected.startsWith(".")
                    ? expected.substring(1)
                    : expected;

            if (actualExtension.equals(cleanExpected.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public void validateExtension(MultipartFile file, Set<String> allowedExtensions) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }

        String extension = getExtension(file);

        if (extension.isEmpty()) {
            throw new IllegalArgumentException("File must have an extension");
        }

        if (!allowedExtensions.contains(extension.toLowerCase())) {
            throw new IllegalArgumentException("File type not allowed. Allowed types: " + allowedExtensions);
        }
    }
}
