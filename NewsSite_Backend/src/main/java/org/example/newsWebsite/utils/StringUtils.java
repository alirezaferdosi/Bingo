package org.example.newsWebsite.utils;

public class StringUtils {
        /**
         * Extracts the postfix of a filename (part before the first period).
         * @param fileName The name of the file.
         * @return The postfix of the filename, or null if no valid postfix exists.
         */
        public static String extractPostfix(String fileName) {
            if (fileName == null || fileName.isEmpty()) {
                return null; // Handle invalid input
            }
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                return fileName.substring(dotIndex + 1); // Extract the extension
            }
            return ""; // Return empty if no extension found
        }
}
