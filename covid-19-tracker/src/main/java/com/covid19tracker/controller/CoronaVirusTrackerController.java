package com.covid19tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.covid19tracker.Dao.CoronaVirusDataService;

@Controller
public class CoronaVirusTrackerController {

	@Autowired
	private CoronaVirusDataService cvDataService;
	
	public String getData(Model model)
	{
		model.addAttribute("coviddata", cvDataService.getLocationData());
		return "home";
		
	}
}
