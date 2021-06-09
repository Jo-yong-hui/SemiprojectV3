package yh.spring.mvc.service;

import yh.spring.mvc.vo.Board;

import java.util.List;

public interface BoardService {

    boolean newBoard(Board b);
    boolean modifyBoard(Board b);
    boolean removeBoard(String bdno);

    //목록출력
    List<Board> readBoard(String cp);
    List<Board> readBoard(String cp, String ftype, String fkey);
    Board readOneBoard(String bdno);

    //게시글 수
    int countBoard();
    int countBoard(String ftype, String fkey);
    boolean viewCountBoard(String bdno);

}
