package com.studentRequest.controller;

import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentRequest.model.Certificate.CATEGORY;
import com.studentRequest.services.SendRequestService;
import com.studentRequest.services.StaffRequestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags= {"all request rating in dashboard"})
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("user/dashboard/")
public class DashbordController {

	@Autowired
	private StaffRequestService staffServive;
	
	@Autowired
	private SendRequestService sendService;
	
	
	public int findSizeOfAllList() {
		int transcriptSize = staffServive.findTrascriptList().size();
		int certificate = staffServive.certificateList().size();
		int recommandationSize = sendService.recommandationList().size();
		int size = transcriptSize+certificate+recommandationSize;
		return size;
	}
	
	@GetMapping(value="transcriptPercentage")
	@ApiOperation("Transcript rating")
	public Integer[]transcript(){
		Integer result[] = new Integer[2];
		Integer transcriptSize = staffServive.findTrascriptList().size();
		Integer percentage = (transcriptSize * 100)/findSizeOfAllList();
		result[1]= percentage;
		result[0]= transcriptSize;
		System.out.println("size: "+transcriptSize+" per: "+percentage);
		return result;
	}
	@GetMapping(value="recommandationPercentage")
	@ApiOperation("recommandation rating")
	public Integer[]recommandation(){
		Integer result[] = new Integer[2];
		Integer recommandationSize = sendService.recommandationList().size();
		Integer percentage = (recommandationSize * 100)/findSizeOfAllList();
		result[1]= percentage;
		result[0]= recommandationSize;
		return result;
	}
	@GetMapping(value="A1Percentage")
	@ApiOperation("A1 rating")
	public Integer[]A1(){
		Integer result[] = new Integer[2];
		Integer A1Size = staffServive.certificateList().stream().filter(i->i.getCategory() == CATEGORY.A1).collect(Collectors.toList()).size();
		Integer percentage = (A1Size * 100)/findSizeOfAllList();
		result[1]= percentage;
		result[0]= A1Size;
		return result;
	}
	@GetMapping(value="COAPercentage")
	@ApiOperation("Certificate rating")
	public Integer[]COA(){
		Integer result[] = new Integer[2];
		Integer COASize = staffServive.certificateList().stream().filter(i->i.getCategory() == CATEGORY.COA).collect(Collectors.toList()).size();
		Integer percentage = (COASize * 100)/findSizeOfAllList();
		result[1]= percentage;
		result[0]= COASize;
		return result;
	}
	
	
}
