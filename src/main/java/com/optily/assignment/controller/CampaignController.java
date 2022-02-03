package com.optily.assignment.controller;

import com.optily.assignment.api.CampaignService;
import com.optily.assignment.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/v1/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    /**
     * @return
     */
    @GetMapping("/find_all")
    @ResponseBody
    public ResponseEntity<ResponseVo> findAllCampaigns() {
        return new ResponseEntity<>(new ResponseVo(campaignService.findAll()), HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/find_by_campaign_group_id/{campaign_group_id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> findByCampaignGroupId(@PathVariable long campaign_group_id) {
        return new ResponseEntity<>(new ResponseVo(campaignService.findByCampaignGroupId(campaign_group_id)),
                HttpStatus.OK);
    }
}
