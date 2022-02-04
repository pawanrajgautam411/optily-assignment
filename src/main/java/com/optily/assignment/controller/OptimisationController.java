package com.optily.assignment.controller;

import com.optily.assignment.api.OptimisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/v1/optimisation")
public class OptimisationController {

    @Autowired
    private OptimisationService optimisationService;


}
