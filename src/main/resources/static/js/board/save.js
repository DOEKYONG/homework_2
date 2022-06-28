function save() {

        let form =$("#writeform")[0];
        let formdata = new FormData(form);
        $.ajax({
             url: "/board/save",
                    method: "POST",
                    data :  formdata,
                    contentType : false,
                    processData: false,
                    success: function( re ){
                    alert(re)
                        if(re == true) {
                           alert("게시글작성성공")
                            location.href="/board/list";
                        }
                        else {
                            alert("실패 서비스오류 ")
                        }
                    }

        })
}