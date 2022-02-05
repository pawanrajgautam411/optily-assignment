package com.optily.assignment.service;

import com.optily.assignment.boot.RepositoryBeanFactory;
import com.optily.assignment.entity.Campaign;
import com.optily.assignment.entity.CampaignGroup;
import com.optily.assignment.exception.AlreadyExistException;
import com.optily.assignment.exception.CSVReaderException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
class ActionCreateCampaignGroup {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ActionCreateCampaignGroup.class);

    /**
     * @param file
     * @param campaignGroupName
     * @return
     */
    protected CampaignGroup createNow(File file, String campaignGroupName) {

        List<CampaignGroup> byName = RepositoryBeanFactory.getCampaignGroupRepository()
                .findByName(campaignGroupName);

        if (byName != null && byName.size() > 0) {
            throw new AlreadyExistException("campaign-group-name ("
                    + campaignGroupName.trim() + ") already-used");
        }

        List<Campaign> campaigns = this.readFileNow(file);

        if (campaigns == null || campaigns.size() <= 0) {
            throw new IllegalArgumentException("campaign-not-found");
        }

        CampaignGroup campaignGroup = new CampaignGroup();
        campaignGroup.setName(campaignGroupName);
        campaignGroup.setCampaigns(campaigns);

        RepositoryBeanFactory.getCampaignGroupRepository().save(campaignGroup);

        return campaignGroup;
    }

    /**
     * @param file
     * @return
     */
    protected List<Campaign> readFileNow(File file) {
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


            List<Campaign> list = new ArrayList<>();

            boolean isColumnName = true;

            for (CSVRecord csvRecord : csvRecords) {
                if (isColumnName) {
                    isColumnName = false;
                    continue;
                }

                Campaign campaign = new Campaign();
                campaign.setName(csvRecord.get(0));
                campaign.setBudget(new BigDecimal(csvRecord.get(1)));
                campaign.setImpressions(new Long(csvRecord.get(2)));

                list.add(campaign);
            }

            return list;

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
