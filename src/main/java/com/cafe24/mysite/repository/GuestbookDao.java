package com.cafe24.mysite.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	public Boolean delete(GuestbookVo vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return 1 == count;
	}
	
	public Boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		return count == 1;
	}	
	
	public List<GuestbookVo> getList(){
		return sqlSession.selectList("guestbook.getList");
	}	
	
}
