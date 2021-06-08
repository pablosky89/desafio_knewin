package com.pablosanhueza.desafio.repositories;

import com.pablosanhueza.desafio.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE name LIKE %?1%")
    List<News> findByName( String name);

}
