package com.optily.assignment.controller;

import com.optily.assignment.api.CampaignGroupService;
import com.optily.assignment.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 *
 */
@Controller
@RequestMapping("/v1/campaign_group/")
public class CampaignGroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignGroupController.class);

    @Autowired
    private CampaignGroupService campaignGroupService;

    /**
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload_file")
    public ResponseEntity<ResponseVo> uploadCampaignCSV(
            @RequestPart("file") MultipartFile multipartFile,
            @RequestParam("campaign_group_name") String campaignGroupName) {

        File file = new MultipartUtil().extractFile(multipartFile, campaignGroupName);
        campaignGroupService.uploadCSV(file, campaignGroupName);

        return new ResponseEntity<>(new ResponseVo("file-uploaded-successfully"),
                HttpStatus.OK);
    }
}
