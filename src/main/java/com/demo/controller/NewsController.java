package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.News;
import com.demo.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class NewsController {
	
	@Autowired
	NewsService newsService;
	
	//@RequestMapping("index")
	public String index(Model model,HttpServletRequest request){
		int page;
		if (request.getParameter("pageNum") != null) {
			page = Integer.parseInt(request.getParameter("pageNum"));
		} else {
			page = 1;
		}
		PageHelper.startPage(page,15);
		List<News> newsList = newsService.selectAllNewsByType();
	    PageInfo<News> p=new PageInfo<News>(newsList);
	    System.out.println(p.isIsFirstPage());
		model.addAttribute("newsList", newsList);
		model.addAttribute("pagehelper",p);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("selectNewsByPrimary")
	public News selectNewsByPrimary(HttpServletRequest request){
		String id = request.getParameter("primaryId");
		News news = newsService.selectNewsByPrimary(id);
		return news;
	}
	
	@ResponseBody
	@RequestMapping("updateNews")
	public boolean updateNews(HttpServletRequest request){
		String id = request.getParameter("primaryId");
		String title = request.getParameter("newsTitle");
		String context = request.getParameter("newsContext");
		String type = request.getParameter("newsType");
		String clickRate = request.getParameter("newsClickRate");
		System.out.println("primaryId:" + id);
		System.out.println("newsClickRate:" + clickRate);
		System.out.println("newsTitle:" + title);
		System.out.println("newsType:" + type);
		newsService.updateNews(id, title, context, type, clickRate);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("deleteNewsByPrimary")
	public boolean deleteNews(HttpServletRequest request){
		String id = request.getParameter("primaryId");
		System.out.println(id);
		newsService.deleteNewsByPrimaryId(id);
		return true;
	}
}
