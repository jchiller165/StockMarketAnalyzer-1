package com.analyzer.framework.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.analyzer.framework.model.Close;
import com.analyzer.framework.model.StockTechData;
import com.analyzer.framework.repo.CloseRepository;
import com.analyzer.framework.repo.StockTechDataRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class RestAPIController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StockTechDataRepository stockTechDataRepo;
	@Autowired
	CloseRepository closeRepo;
	
	@RequestMapping(path="/stock_tech_data", method=RequestMethod.GET)
	public String getStockTechData(){
		List<StockTechData> std = stockTechDataRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(std);
		
		return json;
	}
	
	
}
