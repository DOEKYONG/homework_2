

boardlist()

function boardlist() {
$.ajax({
    url : "/board/blist" ,
    method : "GET",
    success : function(re) {

    //console.log(re)
        let html ="";
        for(let i=0; i<re.length; i++) {

        html +=
               '<tr>'+
                     '<td><a href="/board/boardview/'+re[i].bno+'">'+re[i].bno+'</a></td>'+
                     '<td onclick="boardview('+re[i].bno+')">'+re[i].btitle+'</td>'+
                 '</tr>'
        }
        $("#bt").html(html);

    }
})
}