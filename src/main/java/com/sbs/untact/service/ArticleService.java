package com.sbs.untact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.dto.Util;

@Service
public class ArticleService {
	private int articlesLastId;
	private List<Article> articles;
	
	public ArticleService() {
		articlesLastId = 0;
		articles = new ArrayList<>();

		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목1 입니다.", "내용1 입니다."));
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목2 입니다.", "내용2 입니다."));
	}

	public Article getArticle(int id) {
		for (Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}		
		return null;
	}

	public List<Article> getArticles(String serarchKeywordType, String serarchKeyword) {
		if(serarchKeyword == null) {
			return articles;
		}
		List<Article> filtered = new ArrayList<>();
		for(Article article : articles) {
			boolean contains = false;
			
			if (serarchKeywordType.equals("title")) {
				contains = article.getTitle().contains(serarchKeyword);
			}
			else if (serarchKeywordType.equals("body")) {
				contains = article.getBody().contains(serarchKeyword);
			}
			else {
				contains = article.getTitle().contains(serarchKeyword);
				if(contains == false) {
					contains = article.getBody().contains(serarchKeyword);
				}
			}
			if (contains) {
				filtered.add(article);
			}


		}
		return filtered;
	}

	public ResultData add(String title, String body) {
		int id = articlesLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		
		articles.add(new Article(id, regDate, updateDate, title, body));

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);

				return new ResultData("S-1", "삭제하였습니다.", "id", id);
			}
		}
		return new ResultData("F-1", "해당게시물은 존재하지 않습니다.", "id", id);
	}

	public ResultData modify(int id, String title, String body) {
		Article article = getArticle(id);
		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDateStr());
		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);
	}



}
