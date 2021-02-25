package com.sbs.untact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.Reply;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.ArticleService;
import com.sbs.untact.service.ReplyService;
//55강 할차례 입니다.
@Controller
public class UsrRepliesController {
	@Autowired
	private ReplyService replisService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/reply/list")
	@ResponseBody
	public ResultData showList(String relTypeCode, Integer relId) {		
		if (relId == null) {
			return new ResultData("F-1", "id를 입력해 주세요.");
		}
		if (relTypeCode.equals("article")) {
			Article article= articleService.getArticle(relId);
			if (article == null) {
				return new ResultData("F-1", "존재하지 않는 게시물 입니다.");	
			}
		}
		List<Reply> replies = replisService.getForPrintRepies(relTypeCode, relId);
		return  new ResultData("S-2", "성공", "replies", replies);
	}
}




