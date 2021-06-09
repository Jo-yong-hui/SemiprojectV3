// agree , 이용약관쓰기
$('#okagree').on('click', function () {
    if (!$('#agree1').is(':checked'))
        alert('이용약관에 동의하세요');
    else if(!$('#agree2').is(':checked'))
        alert('개인정보 수집에 동의하세요!');
    else
        location.href = '/join/checkme';
}); // 모두 동의함


$('#noagree').on('click', function () {
    location.href = '/';
}); //동의하지 않음


//checkme
$('#chk2btn').on('click',function () {
    if ($('#name2').val() == '') alert('이름을 입력하세요!');
    else if ($('#jumin1').val() == '' || $('#jumin2').val() == '')
        alert('주민번호를 입력하세요!');
    else if (!$('#chkjumin').is(':checked'))
        alert('주민번호처리에 동의하세요!');
    else {
        const frm = $('#checkfrm2');
        frm.attr('action','/join/joinme');
        frm.attr('method','post');
        frm.submit();
    }
});


$('#cancel2btn').on('click',function () {
    location.href = '/';
});

// userid check
$('#userid').on('blur',function () { checkuserid() });
$('#userid').on('focus',function (){
    $('#uidmsg').text(' 8~16자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다.');
    $('#uidmsg').attr('style','color:black');
});
$('#userid').on('focus',function () {
    $('#uidmsg').text(' 8~16자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다.');
    $('#uidmsg').attr('style','color:black');
});

// ajax check userid
function checkuserid(){
    let uid = $('#userid').val();
    if (uid ==''){ //아이디를 입력하지 않고 탭을 누른 경우
        $('#uidmsg').text(' 8~16자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다.');
        $('#uidmsg').attr('style','color:black');
        return;
    }
    $.ajax({ url: '/join/checkuid',
            type: 'GET', data: { 'uid': uid } })
        .done(function(data){
            let msg = '사용불가능한 아이디입니다!!';
            $('#uidmsg').attr('style','color:red');

            if (data.trim() == '0'){
                msg = '사용가능한 아이디입니다!!';
                $('#uidmsg').attr('style','color:blue');
            }
            $('#uidmsg').text(msg);
        })
        .fail(function(xhr, status, error){
            alert(xhr.status + '/' + error);
        });
}

// check equal passwd
$('#repasswd').on('blur', function (){
    if ($('#passwd').val() != $('#repasswd').val()) {
        $('#pwdmsg').text('비밀번호가 일치하지 않아요');
        $('#pwdmsg').attr('style', 'color:red');
        // 'color:red !important ' !important 넣으면 색깔이 무조건 바뀌게됨
        // 비밀번호 일치하지않아요 글씨 색깔잘 나오면 굳이 안넣어도됨
    }else{
        $('#pwdmsg').text('비밀번호가 일치합니다!');
        $('#pwdmsg').attr('style', 'color:blue');
    }
});



// joinme
$('#joinbtn').on('click',function() {
    if($('#userid').val() =='') alert('아이디를 입력하세요')
    else if ($('#passwd').val() == '') alert('비밀번호 재확인 하세요!');
    else if ($('#passwd').val() != $('#repasswd').val())
    alert('비밀번호가 서로일치하지 않아요!');
   // else if ($('#zip1').val() == '' || $('#zip2').val() == '')
   // alert('우편번호를 조회하세요!');
    else if ($('#addr1').val() == '' ||$('#addr2').val() == '')
        alert('기본주소 또는 나머지 주소를 입력하세요!')
   else if ($('#email1').val() == '' || $('#email2').val() == '')
        alert('이메일을 입력하세요!');
   else if ($('#tel1').val() == '' || $('#tel2').val() == '' ||
        $('#tel3').val() == '')  alert('전화번호를 입력하세요!');
    else if (grecaptcha.getResponse() == '')
        alert('자동가입 방지 확인 필요!!');
    else{
        $('#jumin').val($('#jumin1').val() + '-' + $('#jumin2').val() );
        $('#zipcode').val($('#zip1').val() + '-' + $('#zip2').val() );
        $('#email').val($('#email1').val() + '@' + $('#email2').val() );
        $('#phone').val(
            $('#tel1').val() + '-' + $('#tel2').val() + '-' + $('#tel3').val());

        const frm = $('#joinfrm');
        frm.attr('action','/join/joinok');
        frm.attr('method','post');
        frm.submit();
    }

});
$('#cancelbtn').on('click',function() { location.href = '/'; });

// show zipcode
//http://localhost:8080/join/zipcode?dong=%EC%9E%90%EC%96%911
//이 주소랑 밑에 쓴거랑 같음, 이주소를 보내는방식이 type: GET방식
// 주소 성공했으면 function(data)로넘어옴
// 그 뒤중괄호는 data넘어왓을떄 처리할것
$('#findzipbtn').on('click', function (){
    $.ajax({
        url: '/join/zipcode',
        type: 'GET',
        data: { dong: $('#dong').val() }
    })
        .done(function(data){
            // 서버에서 넘어온 데이터는 JSON형식임
            //alert(data); // 주소창 검색하기 누르면 object로 출력됨
           //여기서 k는  key(키), v는 value(키에 대한 값)
            let opts = "";
            $.each(data, function() { //행단위 반복처리
                let zip = '';
                $.each(this, function (k,v) { //열단위 반복처리
                    if (v !== null) zip += v + ' ';
                });
                opts += '<option>' + zip + '</option>';
            });
            $('#addrlist').find('option').remove(); // 기존 option태그 삭제
            $('#addrlist').append(opts); // 새로만든 option태그를 추가함
        })
        .fail(function(xhr, status, error) {
            alert(xhr.status + '/' + error);
        });
});

// zipcode dong prevent enter key
$('input[type="text"]').keydown(function (){
    if (event.keyCode === 13){
        event.preventDefault();
    }
});


// send zipcode
$('#sendzip').on('click',function (){
    let addr = $('#addrlist option:selected').val();

    if (addr == undefined) {
        alert('주소를 선택하세요!!');
        return;
    }

    let addrs = addr.split(' '); // 선택한 주소를 공백으로 나눔

    // 잘라낸 첫번째 뭉치를 -로 다시 나눔
    //첫번쨰, 두번쨰 우편번호 숫자 잘라서 각각 입력됨. 주소누르면 자동 숫자가 들어감
    $('#zip1').val( addrs[0].split('-')[0] );
    $('#zip2').val( addrs[0].split('-')[1] );

    $('#addr1').val( addrs[1] + ' ' + addrs[2] + ' ' + addrs[3] );

    // 검색창 닫음
    $('#zipmodal').modal('hide');
});


// send email2
// option:selected => select 요소들 중 선택한 요소의 값 알아냄
$('#email3').on('change', function () {
    let val = $('#email3 option:selected').text();
    if (val == '직접입력하기'){
        $('#email2').attr('readonly', false); //readonly 속성 해제
        $('#email2').val('');
    } else {
        $('#email2').attr('readonly', true); //readonly 속성 잠금
        $('#email2').val(val);
    }
});
