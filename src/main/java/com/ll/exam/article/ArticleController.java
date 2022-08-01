package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }

    public void showList(Rq rq) {
        List<ArticleDto> articleDtos = articleService.findAll();

        rq.setAttr("articles", articleDtos);
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        long id = articleService.write(title, body);

        rq.println("%d번 게시물이 생성 되었습니다.".formatted(id));
        showList(rq);
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.println("번호를 입력해주세요.");
            return;
        }
        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.println("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }

    public void showdelete(Rq rq) {
        long id = rq.getPostId();

        articleService.delete(id);

        rq.println("<div class=\"alert('정말로 삭제하시겠습니까?')\"></div>".formatted(id));
//        rq.println("<div class=\"alert('게시물이 삭제되었습니다.')\"></div>".formatted(id));
        showList(rq);

    }

    public void showModify(Rq rq) {
        long id = rq.getPostId();

        ArticleDto articleDto = articleService.findById(id);

        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");
    }

    public void doModify(Rq rq) {
        long id = rq.getPostId();

        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        articleService.modify(id, title, body);

//        rq.println("<div>alert('%d번 게시물이 수정되었습니다.');</div>".formatted(id));
        showDetail(rq);
    }
}