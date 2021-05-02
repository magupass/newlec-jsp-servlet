package Part01.ch00.service;

import java.util.List;

import Part01.ch00.entity.Notice;

public class NoticeService {

	public List<Notice> getNoticeList() {
		
		return getNoticeList("title", "", 1);
	}
	
	public List<Notice> getNoticeList(int page) {
		
		return getNoticeList("title", "", page);
	}
	
	public List<Notice> getNoticeList(String field, String query, int page) {
		String sql = "SELECT * FROM(" + 
					" SELECT ROWNUM, N,* " +
					" FROM (SELECT * FROM NEWLEC_NOTICE ORDER BY REGDATE DESC) N " +
					" ) " +
					" WHERE NUM BETWEEN 6 AND 10 " ;
		
		return null;
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		String sql = "SELECT * FROM(" + 
				" SELECT ROWNUM, N,* " +
				" FROM (SELECT * FROM NEWLEC_NOTICE ORDER BY REGDATE DESC) N " +
				" ) " +
				" WHERE NUM BETWEEN 6 AND 10 " ;
	
		return 0;
	}
	
	public Notice getNotice(int id) {
		
		String sql = "SELECT * FROM NEWLEC_NOTICE ";

		return null;
	}
	
	public Notice getNextNotice(int id) {
		
		String sql = "SELECT * FROM NEWLEC_NOTICE " + 
				 " WHERE ID = ( " +
				 "    SELECT ID FROM NEWLEC_NOTICE " +
				 " WHERE REGDATE > (SELECT REGDATE FROM NEWLEC_NOTICE WHERE ID = 3) " + 
				 " AND ROWNUM = 1 " +
				 " )" ;
		
		return null;
	}
	
	public Notice getPrevNotice(int id) {
		
		String sql = "SELECT ID FROM (SELECT * FROM NEWLEC_NOTICE ORDER BY REGDATE DESC ) " +
				 " WHERE REGDATE < (SELECT REGDATE FROM NEWLEC_NOTICE WHERE ID = 3) " + 
				 " AND ROWNUM = 1" ;
		
		return null;
	}
	
}
