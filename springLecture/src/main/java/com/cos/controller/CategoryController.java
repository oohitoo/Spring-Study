package com.cos.controller;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.cos.domain.CategoryVO;
import com.cos.service.CategoryService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CategoryController {

	@Inject
	private CategoryService ctService;
	
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String index(Model model) throws Exception{
		model.addAttribute("ctg1", ctService.selectGubun1(1));
		model.addAttribute("ctg2", ctService.selectGubun1(2));
		model.addAttribute("ctg3", ctService.selectGubun1(3));
		model.addAttribute("ctg4", ctService.selectGubun1(4));
		return "index";
	}
	
	@RequestMapping(value = "/categoryWriteForm", method = RequestMethod.GET)
	public String categoryWrite(Model model) throws Exception{
		model.addAttribute("ctg1", ctService.selectGubun1(1));
		model.addAttribute("ctg2", ctService.selectGubun1(2));
		model.addAttribute("ctg3", ctService.selectGubun1(3));
		model.addAttribute("ctg4", ctService.selectGubun1(4));
		return "category/categoryWriteForm";
	}
	
	@RequestMapping(value = "/categoryInsert", method = RequestMethod.GET)
	public String categoryInsert(CategoryVO category)  throws Exception{
		ctService.insert(category);
		return "redirect:index";
	}
	
	@RequestMapping(value = "/file")
	public String file(Model model) {
		return "uploadForm";
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		String uploadFolder = "C:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info(multipartFile.getOriginalFilename());
			log.info(multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

}
