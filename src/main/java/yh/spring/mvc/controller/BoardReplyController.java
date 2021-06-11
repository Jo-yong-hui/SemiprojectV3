package yh.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardReplyController {

    @Autowired private BoardReplyController bdrp;

    @RequestMapping("/BoardReply/list")
    public String list() {
        return "BoardReply/list.tiles";

    }

    @RequestMapping("/BoardReply/view")
    public String view() {
        return "BoardReply/view.tiles";

    }
    @RequestMapping("/BoardReply/write")
    public String write() {
        return "BoardReply/write.tiles";

    }

}
