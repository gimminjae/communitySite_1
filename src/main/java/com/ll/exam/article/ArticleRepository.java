package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private static List<ArticleDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;
    }

    public long write(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body);

        datum.add(newArticleDto);

        return id;
    }

    public List<ArticleDto> findAll() {
        return datum;
    }

    public ArticleDto findById(long id) {
        for(ArticleDto ad : datum) {
            if(ad.getId() == id) {
                return ad;
            }
        }
        return null;
    }


    public long delete(long id) {
        for(int i = 0; i < datum.size(); i++) {
            if(datum.get(i).getId() == id) {
                ArticleDto ad = datum.get(i);
                datum.remove(ad);
                return id;
            }
        }
        return 0;
    }
}