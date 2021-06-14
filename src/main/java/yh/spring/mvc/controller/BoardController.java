package yh.spring.mvc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yh.spring.mvc.dao.BoardDAO;
import yh.spring.mvc.service.BoardReplyService;
import yh.spring.mvc.service.BoardService;
import yh.spring.mvc.vo.Board;
import yh.spring.mvc.vo.Reply;

//MVC패턴이란 화면에 보여줄 view 에 대한 작업, DB에서 조회 혹은 DB에 저장할 내용을 중간에 가공,
//처리하는 비즈니스로직, DB에 연결하는 DAO 작업까지 JSP에서 다 하던걸 패턴화 시켜 분리하여 개발하는것인데 각각의 역할에 따라 파일들을 분리해서 작업하는것이다.
//그렇게 하는 이유는 소스를 분리함으로서 각 소스의 목적이 명확해 지고 유지보수하는데 있어서 용이하기 때문이다.
//MVC 패턴으로 개발하고자하면 자연스레 한 페이지에 5개의 파일이 생성되는데 JSP, Controller, DTO, Service, DAO 이다.
//Controller는 화면에서 넘어오는 매개변수들을 이용해 Service 객체를 호출하는 역할을 한다.
//BoardController를 객체화시키기 위해 Controller쓴것, 객체화함으로써 mapping정보 확인가능
@Controller
public class BoardController {

	 private BoardService bsrv;
	 private BoardReplyService brsrv;

	 @Autowired
	 public BoardController(BoardService bsrv, BoardReplyService brsrv ) {
		 this.bsrv = bsrv;
		 this.brsrv = brsrv;
	}

	// 몽땅출력
	@GetMapping("/board/list")
	public ModelAndView list(ModelAndView mv,String cp) {
		if (cp == null) cp = "1";
		mv.setViewName("board/list.tiles");
		mv.addObject("bds", bsrv.readBoard(cp));
		mv.addObject("bdcnt", bsrv.countBoard());

		return mv;
	}
	
	@GetMapping("/board/view")
	public ModelAndView view(String bdno, ModelAndView mv) {

		bsrv.viewCountBoard(bdno); // 조회수 처리

		mv.setViewName("board/view.tiles");
		mv.addObject("bd", bsrv.readOneBoard(bdno));
		mv.addObject("rps", brsrv.readReply(bdno));

		return mv;
	}

	@GetMapping("/board/write")
	public String write() {
		return "board/write.tiles";
	}

	@PostMapping("/board/write")
	public String writeok(Board bd) {
		String returnPage = "redirect:/board/list";

		if(bsrv.newBoard(bd) )
			System.out.println("입력완료!");

		return returnPage;

	}
	
	// 게시판 검색 기능 구현, findtype, findkey 골라서 출력
	@GetMapping("/board/find")
	public ModelAndView find(ModelAndView mv, String cp,
							 String findtype, String findkey){

		mv.setViewName("board/list.tiles");
		mv.addObject("bds",
				bsrv.readBoard(cp, findtype, findkey));
		mv.addObject("bdcnt",
				bsrv.countBoard(findtype, findkey));

		return mv;

	}

	// 댓글쓰기, 포멧으로 넘길데이터는 post로
	@PostMapping("/reply/write")
	public String replyok(Reply r){
	 	String  returenPage = "redirect:/board/view?bdno="+r.getBdno();

	 	brsrv.newComment(r);


	 	return  returenPage;

	}
	@PostMapping("/rreply/write")
	public String rrplyok(Reply r){
		brsrv.newReply(r);

		return "redirect:/board/view?bdno=" + r.getBdno();

	}

}


