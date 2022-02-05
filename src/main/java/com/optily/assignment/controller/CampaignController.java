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
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(new ResponseVo(campaignService.findById(id)),
                HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/campaign_group/{campaign_group_id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> findByCampaignGroupId(@PathVariable("campaign_group_id") long campaignGroupId) {
        return new ResponseEntity<>(new ResponseVo(campaignService.findByCampaignGroupId(campaignGroupId)),
                HttpStatus.OK);
    }

}
