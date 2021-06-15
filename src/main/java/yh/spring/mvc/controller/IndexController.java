package yh.spring.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

		@RequestMapping("/")
		public String index() {
			return "index.tiles";
	}


	@RequestMapping("/pds")
	public String pds() {
		return "pds.tiles";
	}




}
