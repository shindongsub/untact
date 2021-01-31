package com.sbs.untact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.dto.Util;
import com.sbs.untact.service.ArticleService;

//Port: 8021
@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		Article article = articleService.getArticle(id);
		return article;
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(String serarchKeywordType, String serarchKeyword) {
		if (serarchKeywordType != null) {
			serarchKeywordType = serarchKeywordType.trim();
		}

		if (serarchKeywordType == null || serarchKeywordType.length() == 0) {
			serarchKeywordType = "titleAndBody";
		}

		if (serarchKeyword != null && serarchKeyword.length() == 0) {
			serarchKeyword = null;
		}
		if (serarchKeyword != null) {
			serarchKeyword = serarchKeyword.trim(); // trim 글씨 양옆에 공백 날리기
		}
		return articleService.getArticles(serarchKeywordType, serarchKeyword);
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		if (title != null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		if (body != null) {
			return new ResultData("F-1", "body을 입력해주세요.");
		}

		return articleService.addArticle(title, body);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요..");
		}
		Article article = articleService.getArticle(id);
		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");

		}

		return articleService.deleteArticle(id);

	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam(defaultValue = "0") Integer id, String title, String body) {
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요..");
		}
		if (title == null) {
			return new ResultData("F-1", "title을 입력해주세요..");
		}
		if (body == null) {
			return new ResultData("F-1", "body를 입력해주세요..");
		}

		Article article = articleService.getArticle(id);
		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		return articleService.modifyArticle(id, title, body);
	}

}
