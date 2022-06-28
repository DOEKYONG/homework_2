
getboard()

function getboard() {
    $.ajax({
    url : "/board/getview",

    success: function(re) {
    let html =
        '<div> 글번호 :'+re.bno+'</div>'+
         '<div> 제목 :'+re.btitle+'</div>'+
         '<div> 내용 :'+re.bcontent+'</div>'+
          '<button onclick="bdelete('+re.bno+')">삭제하기</button>'

         $("#boardview").html(html)
    }
    })
}
function bdelete(bno) {

    $.ajax({
        url : "/board/delete",
                 method : "DELETE",
                  data : {"bno" : bno } ,
                 success : function( re ){
                    if(re) {
                        alert("삭제성공")
                        location.href="/board/list";
                    }
                    else {
                        alert("실패")
                    }
                 }
             });
        }