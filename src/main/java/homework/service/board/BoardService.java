package homework.service.board;

import homework.domain.board.BoardEntity;
import homework.domain.board.BoardRepository;
import homework.dto.BoardDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    HttpServletRequest request;

    public boolean save(BoardDto boardDto) {

        BoardEntity boardEntity = boardDto.toentity();
        boardRepository.save(boardEntity);

        return true;
    }

    public JSONArray board_list() {
        JSONArray jsonArray = new JSONArray();
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        for (BoardEntity boardEntity : boardEntityList) {
            JSONObject object = new JSONObject();
            object.put("bno", boardEntity.getBno());
            object.put("btitle", boardEntity.getBtitle());
            object.put("bcontent", boardEntity.getBcontent());
            jsonArray.put(object);
        }

        return jsonArray;
    }

    // json 글 정보 출력
    public JSONObject board_view(int bno) {
        BoardEntity board = boardRepository.findById(bno).get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bno", board.getBno());
        jsonObject.put("btitle", board.getBtitle());
        jsonObject.put("bcontent", board.getBcontent());
        return jsonObject;

    }
    //  삭제메소드
    @Transactional
    public boolean delete( int bno ){
        BoardEntity boardEntity = boardRepository.findById( bno ).get();
                boardRepository.delete(  boardEntity );
                return true;
    }

    // 수정메소드
    @Transactional
    public boolean update( BoardDto boardDto ){
        Optional<BoardEntity> optionalBoard
                =  boardRepository.findById( boardDto.getBno() );
        BoardEntity boardEntity =  optionalBoard.get();
                boardEntity.setBtitle( boardDto.getBtitle() );
                boardEntity.setBcontent( boardDto.getBcontent() );

                return true;


    }

}
