package com.optily.assignment.service;

import com.optily.assignment.api.CampaignGroupService;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.exception.CSVReaderException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;

/**
 *
 */
public class CampaignGroupServiceImpl implements CampaignGroupService {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CampaignGroupServiceImpl.class);

    /**
     * @param file
     * @param campaignGroupName
     * @return
     */
    @Override
    public CampaignGroup uploadCSV(File file, String campaignGroupName) {
        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader fileReader = null;
        CSVParser csvParser = null;

        try {
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            fileReader = new BufferedReader(inputStreamReader);
            csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            CampaignGroup campaignGroup = new CampaignGroup();
            campaignGroup.setName(campaignGroupName);

            boolean isColumnName = true;

            for (CSVRecord csvRecord : csvRecords) {
                if (isColumnName) {
                    isColumnName = false;
                    continue;
                }

                Campaign campaign = new Campaign();
                campaign.setCampaignGroup(campaignGroup);

                campaign.setName(csvRecord.get(0));
                campaign.setBudget(new BigDecimal(csvRecord.get(1)));
                campaign.setImpressions(new Long(csvRecord.get(2)));
            }

            return campaignGroup;

        } catch (Throwable e) {
            e.printStackTrace();
            LOGGER.error("", e);
            throw new CSVReaderException(e.getMessage(), e);

        } finally {
            if (csvParser != null) {
                try {
                    csvParser.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
