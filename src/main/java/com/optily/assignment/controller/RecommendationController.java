package com.optily.assignment.controller;

import com.optily.assignment.api.RecommendationService;
import com.optily.assignment.vo.RecommendationVo;
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
@RequestMapping("/v1/recommendation/")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    /**
     * @return
     */
    @GetMapping("/campaign_group/{campaign_group_id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> getRecommendation(@PathVariable long campaign_group_id) {
        RecommendationVo recommendationVo = recommendationService
                .findByCampaignGroupId(campaign_group_id);

        return new ResponseEntity<>(new ResponseVo(recommendationVo), HttpStatus.OK);
    }
}
