package com.covid19tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covid19tracker.Dao.CoronaVirusDataService;
import com.covid19tracker.Model.LocationData;

@Controller
public class CoronaVirusTrackerController {

	@Autowired
	private CoronaVirusDataService cvDataService;
	
	@GetMapping("/home")
	public String getData(Model model)
	{
		List<LocationData> allCases=cvDataService.getLocationData();
		int totalReportedCases=allCases.stream().mapToInt(stat->stat.getLatestStats()).sum();
		int totalNewCases=allCases.stream().mapToInt(stat->stat.getDiffFromPreviousDay()).sum();
		model.addAttribute("coviddata", allCases);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
		
	}
}
