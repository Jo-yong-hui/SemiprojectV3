// show img
function showimg(gno){
    location.href = '/gallery/view?gno=' + gno;
}

// new gallery
$('#newgalbtn').on('click', function (){
   location.href = '/gallery/write';

});

// write gallery
$('#newgal').on('click', function (){
    if($('#title').val() == '') alert('제목을 작성하세요!');
    else if($('#contents').val() == '') alert('본문을 작성하세요!');
    else if(grecaptcha.getResponse() == '') alert('자동가입방지를 체크하세요!!');
    else {
        const frm = $('#galfrm');
        frm.attr('method', 'post');
        frm.attr('action', '/gallery/write');
        frm.attr('enctype', 'multipart/form-data');
        frm.submit();

    }
});

// show attach filename
// next(다음요소)들중 의미
// c:~ 에 있는 맨 마지막 역슬래시 다음, + 1은 fname중 f부터 짜른다는의미
$('#file1').on('change',function (){
    var fname = $(this).val();
    fname = fname.substring(fname.lastIndexOf("\\") + 1);
    $(this).next('.custom-file-label').html(fname);
});

$('#file2').on('change',function (){
    var fname = $(this).val();
    fname = fname.substring(fname.lastIndexOf("\\") + 1);
    $(this).next('.custom-file-label').html(fname);
});

$('#file3').on('change',function (){
    var fname = $(this).val();
    fname = fname.substring(fname.lastIndexOf("\\") + 1);
    $(this).next('.custom-file-label').html(fname);
});