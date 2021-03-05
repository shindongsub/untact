<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>
<script>
	const LoginForm__checkAndSubmitDone = flase;
	function LoginForm__checkAndSubmit(form)(){
		if(LoginForm__checkAndSubmitDone){
			return;
		}
		
		from.loginId.value = form.loginId.value.trim();
		
		if(form.loginId.value.length == 0){
			alert('로그인아이디를 입력해주세요.'){
				from.loginId.focus();
				return;
			}
		}
		if(form.loginPw.value.length == 0){
			alert('로그인비번을 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		form.submit();
		LoginForm__checkAndSubmitDone = true;
	}
	</script>
<section class="section-login">
	<div
		class="container mx-auto min-h-screen flex items-center justify-center">
		<div class="w-full bg-white shadow-md rounded px-8 pt-6 pb-8 mt-4">
			<div class="logo-bar flex justify-center mt-5">
				<a href="#" class="logo"> <span><i
						class="fas fa-fingerprint"></i></span> <span>UNTACT</span>
				</a>
			</div>
			<form
				action="doLogin" method="POST"
				onsubmit="LoginForm__checkAndSubmit(this); return false;">
				<div class="flex flex-col mb-4 md:flex-row mt-4">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>로그인아이디</span>
					</div>
					<div class="p-1 md:flex-grow">
						<input
							class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker"
							autofocus="autofocus" type="text" placeholder="로그인 아이디를 입력해 주세요."
							name="loginId" maxlength="20" />
					</div>
				</div>
				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>로그인비번</span>
					</div>
					<div class="p-1 md:flex-grow ">
						<input
							class="shadow appearance-none border border-red rounded w-full py-2 px-3 text-grey-darker mb-3"
							" autofocus="autofocus" type="password"
							placeholder="로그인 비밀번호를 입력해 주세요." name="loginPw" maxlength="20" />
					</div>
				</div>
				<div class="flex flex-col mb-4 md:flex-row">
					<div class="p-1 md:w-36 md:flex md:items-center">
						<span>로그인</span>
					</div>
					<div class="p-1">
						<input
							class="bg-blue-400 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded"
							type="submit" value="로그인" />
					</div>
				</div>
			</form>
		</div>

	</div>
</section>
</body>
</html>