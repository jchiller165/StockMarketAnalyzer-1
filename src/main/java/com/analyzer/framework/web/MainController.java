package com.analyzer.framework.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.analyzer.framework.model.Close;
import com.analyzer.framework.model.Stock;
import com.analyzer.framework.model.Symbol;
import com.analyzer.framework.repo.CloseRepository;
import com.analyzer.framework.repo.StockRepository;
import com.analyzer.framework.repo.SymbolRepository;
import com.analyzer.framework.repo.TenDaySMADataRepository;
import com.google.gson.Gson;

@Controller
public class MainController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StockRepository stockRepo;
	@Autowired 
	SymbolRepository symbolRepo;
	@Autowired 
	CloseRepository closeRepo;
	@Autowired
	TenDaySMADataRepository tenDayRepo;


	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String welcome(Model model) {
  
		return "home"; 	    		
	}
	
	@RequestMapping(value = {"/stock/{id}"}, method = RequestMethod.GET)
	public String getStock(@PathVariable("id") String id, Model model) {
		Gson gsonObj = new Gson();
		
		model.addAttribute("stock", stockRepo.findById(id).orElse(null));
		model.addAttribute("symbol", symbolRepo.findById(id).orElse(null));
		
		String dataPoints = gsonObj.toJson(new ChartDataRetreiver().getData(closeRepo.findById(id).orElse(null).get100DayClose()));
		logger.info("" + dataPoints);
		model.addAttribute("closePrices", dataPoints);
		
		String dataPoints1 = gsonObj.toJson(new ChartDataRetreiver().getData(tenDayRepo.findById(id).orElse(null).get100DayClose()));
		logger.info("" + dataPoints1);
		model.addAttribute("tenDayPrices", dataPoints1);
		
		return "stock";
	}
}
