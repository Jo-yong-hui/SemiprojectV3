
// new board = 새글쓰기버튼
$('#newbdbtn').on('click', function () {
    location.href = '/board/write';

});

//goto list(게시판 화면)
$('#listbdbtn').on('click', function () {
    location.href = '/board/list';

});

//save board = 새글쓰기안에 입력완료 버튼, const는 폼(form)에 객체 접근할때 쓰는 데이터 타입
$('#savebdbtn').on('click', function () {
    if ($('#title').val() =='') alert('제목을 작성하세요!');
   else if ($('#contents').val() =='') alert('본문내용을 작성하세요!');
   else if (grecaptcha.getResponse() =='') alert('자동가입방지를 확인하세요!');
   else {
        const frm = $('#boardfrm');
        frm.attr('method','post');
        frm.attr('action','/board/write');
        frm.submit();

        }
 });

// search board : 화면상에 보이는 파란색 검색(find)버튼
// 예시 : http://localhost:8080/board/find?findtype=title&findkey=석촌호수&cp=1
$('#findbtn').on('click',function (){
    if( $('#findkey').val() == '') alert('검색할 내용을 작성하세요!');
    else {
        let qry = '?findtype=' + $('#findtype').val();
        qry += "&findkey=" + $('#findkey').val()+"&cp=1";
        let url = '/board/find' + qry;
        location.href = url;
    }
});

//  findtype tag setting
//$('#findtype').val('${param.findtype}')
//    .prop('selected','true');

// new board reply = 댓글쓰기 버큰
$('#newbrbtn').on('click',function () {
    if($('#reply').val() == '') alert('댓글을 작성하세요!');
    else {
        const frm = $('#replyfrm');
        frm.attr('method', 'post');
        frm.attr('action', '/reply/write');
        frm.submit();
    }
});

// show reply = 댓글의 댓글단거 보기
function addReply(rno){
    $('#replyModal').modal('show');
    $('#rpno').val(rno);   // 대댓글 작성시 부모댓글번호를 넘겨줌
}

// new reply = 댓글있는거 추가버튼눌러서 대댓글 작성하기
$('#newrrpbtn').on('click',function (){
    if ($('#rreply').val() == '') alert('대댓글을 작성하세요!!');
    else {
        const frm = $('#rpfrm');
        frm.attr('method', 'post');
        frm.attr('action', '/rreply/write');
        frm.submit();
    }
})