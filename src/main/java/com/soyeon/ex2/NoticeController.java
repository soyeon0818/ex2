package com.soyeon.ex2;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soyeon.notice.NoticeDTO;
import com.soyeon.notice.NoticeService;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	@Inject /* inject는 타입으로 찾는다. */
	private NoticeService noticeService;

	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public void noticeList(Model model, @RequestParam(defaultValue="1") Integer curPage) throws Exception {
		List<NoticeDTO> ar = noticeService.noticeList(curPage);
		model.addAttribute("list", ar);
	}
	
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public void noticeView(Integer num, Model model) throws Exception {
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		model.addAttribute("view", noticeDTO);
	}
	
	@RequestMapping(value="noticeUpdate",method=RequestMethod.GET)
	public void noticeUpdate(Integer num, Model model) throws Exception {
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		model.addAttribute("view", noticeDTO);
	}
	
	@RequestMapping(value="noticeUpdate",method=RequestMethod.POST)
	public String noticeUpdate(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception {
		int result = noticeService.noticeUpdate(noticeDTO);
		
		String message = "update fail";
		
		if(result > 0) {
			message = "update success";
		}
		rd.addFlashAttribute("message",message);
		
		return "redirect:/notice/noticeList";
		
		/*String url = "redirect:noticeList";
		return new ModelAndView(url);*/
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public void noticeWrite() {	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception {
		int result = noticeService.noticeWrite(noticeDTO);
		
		String message = "fail";
		
		if(result > 0) {
			message = "success";
		}
		rd.addFlashAttribute("message", message);		//redirect로 보낼 때 값을 전송하려면
		
		/*return "notice/result";*/
		return "redirect:noticeList";
	}
	
	@RequestMapping(value="noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(Integer num, RedirectAttributes rd) throws Exception {
		int result = noticeService.noticeDelete(num);
		
		String message = "delete fail";
		
		if(result > 0) {
			message = "delete success";
		}
		rd.addFlashAttribute("message",message);
		
		return "redirect:noticeList";	// 상대경로
		/*return "redirect:/notice/noticeList"; // 절대경로로 해도 된다.*/
	}
}