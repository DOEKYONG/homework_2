package homework.controller.board;


import homework.domain.board.BoardRepository;
import homework.dto.BoardDto;
import homework.service.board.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    BoardService boardservice;

    @GetMapping("/write")
    public String write(){
        return "/board/write";
    }

    @GetMapping("/list")
    public String list(){
        return "/board/list";
    }

    @GetMapping("/boardview/{bno}")
    public String view(@PathVariable("bno") int bno){
        request.getSession().setAttribute("bno",bno);
        return "/board/view";
    }

    @GetMapping("/update")
    public String update(){
        return "board/update";
    }


    // 메소드 컨트롤///////////////
    // 쓰기
    @PostMapping("/save")
    @ResponseBody
    public boolean save(BoardDto boardDto) {
        return boardservice.save(boardDto);
    }
    // 글목록 출력
    @GetMapping("/blist")
    public void list(  HttpServletResponse response){

        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(boardservice.board_list());

        }catch(Exception e) {System.out.println(e);}

    }
    // 게시글 상세정보 출력
    @GetMapping("/getview")
    @ResponseBody
    public void getview(HttpServletResponse response){
        int bno = (Integer)request.getSession().getAttribute("bno");
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(boardservice.board_view(bno));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // 게시글 삭제
    @DeleteMapping("/delete")
    @ResponseBody
    public boolean delete( @RequestParam("bno") int bno){
        return boardservice.delete( bno);
    }

    // 수정메소드
    @PutMapping("/update")
    @ResponseBody
    public boolean update( BoardDto boardDto){
        int bno = (Integer)request.getSession().getAttribute("bno");
        boardDto.setBno(bno);
        return  boardservice.update( boardDto );
    }


}
