package com.soyeon.notice;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.soyeon.util.PageMaker;
import com.soyeon.util.RowMaker;

@Service
// NoticeService noticeService = new NoticeService();
public class NoticeService {
	
	@Inject
	private NoticeDAO noticeDAO;
	
	public void test() {
		System.out.println(noticeDAO);
	}
	
	/*public NoticeService(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}*/
	
	public NoticeDTO noticeView(int num) throws Exception {
		return noticeDAO.noticeView(num);
	}
	
	public List<NoticeDTO> noticeList(int curPage) throws Exception {
		// int result = noticeDAO.noticeCount();
		PageMaker pageMaker = new PageMaker(10, curPage);
		RowMaker rowMaker = pageMaker.getRowMaker("", ""); 
		
		return noticeDAO.noticeList(rowMaker);
	}
	
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.noticeUpdate(noticeDTO);
	}
	
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.noticeWrite(noticeDTO);
	}
	
	public int noticeDelete(int num) throws Exception {
		return noticeDAO.noticeDelete(num);
	}
}