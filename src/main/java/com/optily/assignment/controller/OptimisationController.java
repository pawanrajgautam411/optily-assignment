package com.optily.assignment.controller;

import com.optily.assignment.api.OptimisationService;
import com.optily.assignment.entity.Optimisation;
import com.optily.assignment.vo.OptimisationRequestVo;
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
@RequestMapping("/v1/optimisation")
public class OptimisationController {

    @Autowired
    private OptimisationService optimisationService;



    /**
     * @return
     */
    @GetMapping("/campaign_group/{campaign_group_id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> findByCampaignGroupId(@PathVariable("campaign_group_id") long campaignGroupId) {
        return new ResponseEntity<>(new ResponseVo(optimisationService.findByCampaignGroupId(campaignGroupId)),
                HttpStatus.OK);
    }


    /**
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseVo> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(new ResponseVo(optimisationService.findById(id)),
                HttpStatus.OK);
    }

}
