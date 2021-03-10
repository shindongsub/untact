<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>
<script>
ArticleAdd__submited = false;
function ArticleAdd__checkAndSubmit(form){
	if (ArticleAdd__submited){
		alert('처리중입니다.');
		return;
	}
	
	form.title.value = form.title.value.trim();
	if (form.title.value.length == 0){
		alert('제목을 입력해 주세요.');
		form.title.focus();
		return false;
	}
	
	form.body.value = form.body.value.trim();
	if (form.body.value.length == 0){
		alert('내용을 입력해 주세요.');
		form.body.focus();
		return false;
	}
	var maxSizeMb = 50; // 50MB 사이즈제한
	var maxSize = maxSizeMb * 1024 * 1024;
	if (form.file__article__0__common__attachment__1.value) {
		if (form.file__article__0__common__attachment__1.files[0].size > maxSize) {
			alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요.");
			form.file__article__0__common__attachment__1.focus();
			return;
		}
	}
	
	if (form.file__article__0__common__attachment__2.value) {
		if (form.file__article__0__common__attachment__2.files[0].size > maxSize) {
			alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요.");
			form.file__article__0__common__attachment__2.focus();
			return;
		}
	}
	form.submit();
	ArticleAdd__submited = true;
}
</script>


<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">
		<form onsubmit="ArticleAdd__checkAndSubmit(this); return false;" action="doAdd" method="POST" enctype="multipart/form-data">
			<!-- /enctype="multipart/form-data" 첨부파일 form안에 파일을 입력하려면 데이타가 엄청나게 다른형식으로 나간다. jsp에선 엄청나게 복잡하지만 스트링 부트에선 그럴필요없이 이거면 된다. -->
			<input type="hidden" name="boardId" value="${param.boardId}" />
			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>제목</span>
				</div>
				<div class="lg:flex-grow">
					<input type="text" name="title" autofocus="autofocus"
						class="form-row-input w-full rounded-sm" placeholder="제목을 입력해주세요." />
				</div>
			</div>
			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>내용</span>
				</div>
				<div class="lg:flex-grow">
					<textarea name="body" class="form-row-input w-full rounded-sm"
						placeholder="내용을 입력해주세요."></textarea>
				</div>
			</div>
			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>첨부파일 1</span>
				</div>
				<div class="lg:flex-grow">
					<input type="file" name="file__article__0__common__attachment__1"
						class="form-row-input w-full rounded-sm" />
				</div>
			</div>
			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>첨부파일 2</span>
				</div>
				<div class="lg:flex-grow">
					<input type="file" name="file__article__0__common__attachment__2"
						class="form-row-input w-full rounded-sm" />
				</div>
			</div>
			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>작성</span>
				</div>
				<div class="lg:flex-grow">
					<div class="btns">
						<input type="submit"
							class="btn-primary bg-blue-500 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded"
							value="작성"> <input onclick="history.back();"
							type="button"
							class="btn-info bg-red-500 hover:bg-red-dark text-white font-bold py-2 px-4 rounded"
							value="취소">
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<%@ include file="../part/mainLayoutFoot.jspf"%>