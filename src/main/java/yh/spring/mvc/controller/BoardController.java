package yh.spring.mvc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yh.spring.mvc.dao.BoardDAO;
import yh.spring.mvc.service.BoardService;

@Controller
public class BoardController {

	@Autowired private BoardService bsrv;

	@RequestMapping("/board/list")
	public ModelAndView list(ModelAndView mv,String cp) {
		if (cp == null) cp = "1";
		mv.setViewName("board/list.tiles");
		mv.addObject("bds", bsrv.readBoard(cp));

		return mv;
	}
	
	@RequestMapping("/board/view")
	public String view() { return "board/view.tiles"; }

	@RequestMapping("/board/write")
	public String write() { return "board/write.tiles"; }

}
