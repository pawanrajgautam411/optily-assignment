package com.optily.assignment.controller;

import com.optily.assignment.api.RecommendationService;
import com.optily.assignment.vo.RecommendationResponseVo;
import com.optily.assignment.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping("/v1/recommendation/")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    /**
     * @return
     */
    @PostMapping("/generate/{campaign_group_id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> generate(@PathVariable("campaign_group_id") long campaignGroupId) {
        RecommendationResponseVo recommendationResponseVo = recommendationService
                .generate(campaignGroupId);

        return new ResponseEntity<>(new ResponseVo(recommendationResponseVo), HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/campaign_group/{campaign_group_id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> findByCampaignGroupId(@PathVariable("campaign_group_id") long campaignGroupId) {
        return new ResponseEntity<>(new ResponseVo(recommendationService.findByCampaignGroupId(campaignGroupId)),
                HttpStatus.OK);
    }
}
