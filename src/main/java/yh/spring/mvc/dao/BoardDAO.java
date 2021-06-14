package yh.spring.mvc.dao;

import yh.spring.mvc.vo.Board;

import java.util.List;
import java.util.Map;

//DAO는 데이터베이스와 연결해주는 객체
public interface BoardDAO {

    int insertBoard(Board b);
    int updateBoard(Board b);
    int deleteBoard(String bdno);

    //목록출력
    List<Board> selectBoard(int snum);
    List<Board> findSelectBoard(Map<String, Object> param);
    Board selectOneBoard(String bdno);

    //게시글 수
    int selectCountBoard();
    int selectCountBoard(Map<String, Object> param);
    int viewCountBoard(String bdno);

}
//      map 객체 생성법
//      Map<String, Object> map = new HashMap<String, Object>();
//
//		// 데이터 저장하기!
//		map.put("이름", "siri");
//		map.put("나이", 13);
//		map.put("직업", "학생");
//
//		// 저장한 데이터 꺼내오기
//		System.out.println("key 출력>>>" + map.keySet()); // [이름, 나이, 직업]
//		System.out.println("value 출력>>>" + map.values()); // [siri, 13, 학생]
//		System.out.println("키벨류 출력>>>" + map.toString()); // {이름=siri, 나이=13, 직업=학생}
//		System.out.println("해당키의 값을 출력>>>" + map.get("나이")); // 13