<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역: 로그인 된 상태에서만 보여짐 --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		</c:if>
		<%--// 글쓰기 영역 끝 --%>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<c:forEach items="${cardList}" var="card">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${card.user.loginId}</span>
					
					<%-- 더보기(내가 쓴 글일 때만 노출) --%>
					<c:if test="${card.post.userId eq userId}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
					</c:if>
				</div>
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
				<%-- 좋아요가 눌려져있지 않을 때 (빈 하트) --%>
				<c:if test="${card.filledLike eq false}">
					<a href="#" class="like-btn" data-post-id="${card.post.id}">
						<img src="https://cdn.pixabay.com/photo/2016/01/20/14/22/heart-1151623_960_720.png" width="18px" height="18px" alt="filled heart">
					</a>
					<a href="#" class="likeUser" data-toggle="likeModal" data-target="#likeModal">
					좋아요 ${card.likeCount}개
					</a>
				</c:if>
				<%--좋아요가 눌려졌을 때 (채워진 하트) --%>
					<c:if test="${card.filledLike}">
					<a href="#" class="like-btn" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18px" height="18px" alt="filled heart">
					</a>
					<a href="#" class="likeUser" data-toggle="likeModal" data-target="#likeModal">
					좋아요 ${card.likeCount}개
					</a>
				</c:if>
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.user.loginId}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					
				<c:forEach items="${card.commentList}" var="commentView">
					<%-- 댓글 내용 --%>
					<div class="card-comment m-1">
						<span class="font-weight-bold">${commentView.user.loginId}</span>
						<span>${commentView.comment.content}</span>
						<%-- 댓글 삭제 버튼 --%>
						<c:if test="${userId eq commentView.user.id}">
						<a href="#" class="commentDelBtn" data-comment-id="${commentView.comment.id}">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
						</c:if>
					</div>
						</c:forEach>
					<%-- 댓글 쓰기 --%>
					<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기" id="comment"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
					</div>
					
					</c:if>
				</div>
				<%--// 댓글 목록 끝 --%>
			</div>
			</c:forEach>
			<%--// 카드1 끝 --%>
		
		
		</div>
		<%--// 타임라인 영역 끝  --%>
	
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 -->
	<%--modal-sm 작은 모달. --%>
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
     		<a href="#" id="deletePostBtn">삭제하기</a>
     	</div>
     	<div class="py-3">
     		<%-- data-dismiss="modal" => 모달창 닫힘 --%>
     		<a href="#" data-dismiss="modal">취소하기</a>
     	</div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="likeModal">
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 -->
	<%--modal-sm 작은 모달. --%>
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
     		
     	</div>
     	<div class="py-3">
     		<%-- data-dismiss="modal" => 모달창 닫힘 --%>
     		
     	</div>
    </div>
  </div>
</div>


<script>
	$(document).ready(function() {
		// 파일업로드 이미지 클릭 => 숨겨져있는 file 태그를 동작시킨다.
		$("#fileUploadBtn").on("click", function(e) {
			e.preventDefault(); //  a태그의 올라가는 현상 방지
			$("#file").click(); // input file을 클릭한 것과 같다.
		});

		// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드된 파일명 노출
		$("#file").on('change', function(e) {
			let fileName = e.target.files[0].name; //예)	secured.jpg
			console.log(fileName);

			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "gif") {
				alert("이미지 파일만 업로드할 수 있습니다.");
				$("#file").val(""); // 파일태그의 파일 제거.
				$("#fileName").text(""); // 파일 이름 비우기
				return;
			}

			// 유효성 통과한 이미지는 상자에 업로드 된 파이 이름 노출
			$("#fileName").text(fileName);
		});
		// 글쓰기
		$("#writeBtn").on("click", function() {
			// validation
			let content = $("#writeTextArea").val().trim()
			let file = $("#file").val();

			if (!content) {
				alert("내용을 입력해주세요");
				return;
			}

			if (!file) {
				alert("파일을 첨부해주세요");
				return;
			}

			// 글내용, 이미지, 확장자 체크

			let ext = file.split(".").pop().toLowerCase();
			if (ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "gif") {
				alert("이미지 파일만 업로드할 수 있습니다.");
				$("#file").val(""); // 파일태그의 파일 제거.
				return;
			}

			// ajax 전송
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $("#file")[0].files[0]);

			$.ajax({
				// response
				type : "POST",
				url : "/post/create",
				data : formData,
				enctype : "multipart/form-data" // file 업로드를 위한 필수 설정
				,
				processData : false // file 업로드를 위한 필수 설정
				,
				contentType : false // file 업로드를 위한 필수 설정

				// response
				,
				success : function(data) {
					if (data.code == 1) {
						alert("게시글 작성이 완료되었습니다");
						location.href = "/timeline/timeline_view";
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(request, status, error) {
					alert("글을 저장하는데 실패했습니다.");
				}
			});
		});
		
		$(".comment-btn").on("click", function(e) {
			e.preventDefault();
			let postId = $(this).data("post-id");
			// 1) 댓글 내용 가져오기
			//	let comment = $(this).prev().val();
			//	alert(comment);

			// 2) 댓글 내용 가져오기
			let content = $(this).siblings("#comment").val();
			if (!comment) {
				alert("내용을 입력해주세요");
				return;
			}

			$.ajax({
				type : "post",
				url : "/comment/create",
				data : {
					"postId" : postId,
					"content" : content
				},
				success : function(data) {
					if (data.code == 1) {
						alert("댓글 작성이 완료되었습니다.");
						location.reload();
					} else {
						alert(data.errormessage);
					}
				},
				error : function(reqeust, status, error) {
					alert("글을 저장하는데 실패했습니다.");
				}

			});

		});
		
		$(".like-btn").on("click", function(e){
			e.preventDefault();
			let postId = $(this).data("post-id");
			
			$.ajax({
				url:"/like/" + postId
				
				// res
				, success:function(data){
					if(data.code == 1){
						location.reload();
					}else{
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error){
					alert("다시 시도하십시오");
				}
			});
			
		});
		
		$(".commentDelBtn").on("click", function(e){
			e.preventDefault();
			let commentId = $(this).data("comment-id");
			// alert(commentId);
			$.ajax({
				type :"post"
				, url : "/comment/delete"
				, data : {
					"commentId" : commentId
				}
				
				, success : function(data){
					if(data.code == 1){
						alert("댓글이 삭제되었습니다.");
						location.reload();
					}else{
						alert(errorMessage);
					}
				}
					, error: function(request, status, error){
						alert("댓글을 삭제하는데 실패했습니다.");
					}
			});
		});
		
		// 글 삭제(... 더보기 버튼 클릭)  글 삭제를 위해서
		$(".more-btn").on("click", function(e){
			e.preventDefault;	// a 태그 누를 때 위로 올라가는 현상 방지
			// alert("ㅇㅇ");
			let postId = $(this).data("post-id");
			//alert(postId);
			// 모달 태그에(재활용 되고 있는) data-post-id를 심어줌
			$("#modal").data('post-id', postId); // setting
			
		});
		// 모달 안에 있는 삭제하기 버튼 클릭
		$("#modal #deletePostBtn").on("click", function(e){
			e.preventDefault();
			
			let postId = $("#modal").data("post-id");
			//alert(postId);
			
			// ajax 글 삭제
			$.ajax({
				type:"delete"
				, url:"/post/delete"
				, data:{
					"postId" : postId
				}
				
				, success: function(data){
					if(data.code == 1){
						alert("글을 삭제하였습니다.");
						location.reload(true);
					} else{
						alert(errorMessage);
					}
				}
					, error: function(request, status, error){
						alert("게시글 삭제에 실패했습니다.");
					}
			});
		});
	});
</script>