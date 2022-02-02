package com.optily.assignment.controller;

import com.optily.assignment.exception.CSVReaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
class MultipartUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultipartUtil.class);

    /**
     * @param multipartFile
     * @param campaignGroupName
     * @return
     */
    protected File extractFile(MultipartFile multipartFile, String campaignGroupName) {
        try {
            if (multipartFile == null || multipartFile.isEmpty()) {
                throw new IllegalArgumentException("file-not-found");
            }

            if (campaignGroupName == null || campaignGroupName.trim().isEmpty()) {
                throw new IllegalArgumentException("invalid-campaign-name");
            }

            LOGGER.info("fileName=" + multipartFile.getOriginalFilename()
                    + ", campaignGroupName=" + campaignGroupName);

            // Get the file and save it somewhere
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(multipartFile.getOriginalFilename());
            Files.write(path, bytes);

            return path.toFile();

        } catch (CSVReaderException e) {
            throw e;

        } catch (Throwable e) {
            e.printStackTrace();
            LOGGER.error("", e);
            throw new CSVReaderException(e.getMessage(), e);
        }
    }

}
