package com.sbs.untact.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.ArticleService;
import com.sbs.untact.util.Util;
//49강 할차례 입니다.
@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public ResultData showDetail(Integer id) {
		if (id == null) {
			return new ResultData("F-1","id를 입력해주세요.");
		}
		
		Article article = articleService.getPringtArticle(id);
		if(article == null) {
			return new ResultData("F-2","존재하지 않는 게시물 번호입니다..");
		}
		return new ResultData("S-1","성공", "article", article);
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public ResultData showList(String searchKeywordType, String searchKeyword) {
		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}
		if (searchKeyword == null) {
			searchKeywordType = null;
		}
		List<Article> articles = articleService.getForPrintArticles(searchKeywordType, searchKeyword);
		return  new ResultData("S-2", "성공", "articles", articles);
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpSession session) {
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		if (loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해 주세요.");
		}
		
		if (param.get("title") == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}

		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		param.put("memberId", loginedMemberId);
		return articleService.addArticle(param);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id, HttpSession session) {
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		if (loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해 주세요.");
		}
		
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		ResultData getActorCanDeleteRd= articleService.getActorCanDeleteRd(article, loginedMemberId);
		if(getActorCanDeleteRd.isFail()) {
			return getActorCanDeleteRd;
		}

		return articleService.deleteArticle(id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body, HttpSession session) {
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		if (loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해 주세요.");
		}
		
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		if (title == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}

		if (body == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		ResultData getActorCanModifyRd= articleService.getActorCanModifyRd(article, loginedMemberId);
		if(getActorCanModifyRd.isFail()) {
			return getActorCanModifyRd;
		}

		return articleService.modifyArticle(id, title, body);
	}
}
