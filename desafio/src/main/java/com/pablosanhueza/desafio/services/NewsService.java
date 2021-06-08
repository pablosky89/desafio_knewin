package com.pablosanhueza.desafio.services;

import com.pablosanhueza.desafio.entities.News;
import com.pablosanhueza.desafio.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    @Transactional
    public List<News> listAll(){
        return repository.findAll();
    }

    @Transactional
    public void save(News news){
        repository.save(news);
    }

    @Transactional
    public News get(Long id){
        return repository.findById(id).get();
    }

    @Transactional
    public List<News> getNewsByName(String name){
        return repository.findByName(name);
    }
}
