package com.pablosanhueza.desafio.controllers;

import com.pablosanhueza.desafio.entities.News;
import com.pablosanhueza.desafio.scraper.Scraper;
import com.pablosanhueza.desafio.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService service;

    @Autowired
    private Scraper scraper;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<News> listNews = service.listAll();
        model.addAttribute("listNews",listNews);
        return "index";
    }

    @GetMapping("/search")
    public String newsSearch(Model model, @RequestParam(value = "freeText") String freeText) {
        model.addAttribute("listNews",service.getNewsByName(freeText));
        return "index";
    }

    @RequestMapping("/new")
    public String showNewUrlForm(Model model){
        News news = new News();
        model.addAttribute("news",news);
        return "add_news";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNews(@Valid @ModelAttribute("news") News news, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "add_news";
        }
        News requestNews = scraper.getData(news.getName());
        service.save(requestNews);
        return "redirect:/";
    }

    @RequestMapping("/inspect/{id}")
    public ModelAndView inspectNews(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("inspect_news");
        News newsDetails = service.get(id);
        mav.addObject("newsDetails", newsDetails);
        return mav;
    }
}
