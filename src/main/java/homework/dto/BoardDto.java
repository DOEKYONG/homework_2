package homework.dto;

import homework.domain.board.BoardEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;


    // DTO -> ENTITY
    public BoardEntity toentity(){
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .build();
    }
}