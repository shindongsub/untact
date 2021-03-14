package com.sbs.untact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.Reply;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.ArticleService;
import com.sbs.untact.service.ReplyService;
import com.sbs.untact.util.Util;

@Controller
public class AdmRepliesController {
	@Autowired
	private ReplyService replisService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/adm/reply/list")
	@ResponseBody
	public ResultData showList(String relTypeCode, Integer relId) {		
		if (relTypeCode == null) {
			return new ResultData("F-1", "relTypeCode를 입력해 주세요.");
		}
		if (relId == null) {
			return new ResultData("F-1", "relId를 입력해 주세요.");
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
	
	
	@RequestMapping("/adm/reply/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer relId, HttpServletRequest req) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");

		if (relId == null) {
			return new ResultData("F-1", "relId를 입력해주세요.");
		}

		Reply reply = replisService.getReply(relId);

		if (reply == null) {
			return new ResultData("F-1", "해당 댓글은 존재하지 않습니다.");
		}
		ResultData getActorCanDeleteRd= replisService.getActorCanDeleteRd(reply, loginedMemberId);
		if(getActorCanDeleteRd.isFail()) {
			return getActorCanDeleteRd;
		}

		return replisService.deleteReply(relId);
	}
	@RequestMapping("/adm/reply/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String body, HttpServletRequest req) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");

		
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		if (body == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}

		Reply reply = replisService.getReply(id);

		if (reply == null) {
			return new ResultData("F-1", "해당 댓글은 존재하지 않습니다.");
		}
		ResultData getActorCanModifyRd = replisService.getActorCanModifyRd(reply, loginedMemberId);
		if(getActorCanModifyRd.isFail()) {
			return getActorCanModifyRd;
		}

		return replisService.modifyReply(id, body);
	}
}




