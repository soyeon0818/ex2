package com.soyeon.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.soyeon.util.DBConnect;
import com.soyeon.util.RowMaker;

@Repository
// @Repository는 다음과 같은 의미. NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAO {
	
	@Inject
	private DataSource dataSource;

	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/

	public NoticeDTO noticeView(int num) throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select * from NOTICE where NUM = ?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		rs = st.executeQuery();
		
		if(rs.next()) {
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}
		
		DBConnect.disConnect(rs, st, con);
		
		return noticeDTO;
	}
	
	public int noticeCount() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select nvl(count(NUM), 0) from NOTICE";
		
		st = con.prepareStatement(sql);
		rs = st.executeQuery();
		rs.next();
		
		result = rs.getInt(1);
		
		DBConnect.disConnect(rs, st, con);
		return result;
	}
	
	public List<NoticeDTO> noticeList(RowMaker rowMaker) throws Exception {
		List<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice order by num desc) N) "
				+ "where R between ? and ?";
		
		st = con.prepareStatement(sql);
		
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		
		rs = st.executeQuery();
		
		while(rs.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			
			ar.add(noticeDTO);
		}
		DBConnect.disConnect(rs, st, con);
		
		return ar;
	}
	
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "insert into NOTICE (NUM,WRITER,TITLE,CONTENTS,REG_DATE,HIT) values (NOTICE_SEQ.nextval,?,?,?,SYSDATE,0)";
		
		st = con.prepareStatement(sql);
		
		st.setString(1, noticeDTO.getWriter());
		st.setString(2, noticeDTO.getTitle());
		st.setString(3, noticeDTO.getContents());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}
	
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "update NOTICE set TITLE=?, CONTENTS=?, REG_DATE=SYSDATE where NUM=?";
		
		st = con.prepareStatement(sql);
		
		System.out.println("num " + noticeDTO.getNum());
		
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getContents());
		st.setInt(3, noticeDTO.getNum());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}
	public int noticeDelete(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "delete from NOTICE where NUM=?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}
	
	public int updateHit() throws Exception {
		int result = 0;
		
		return result;
	}
}