package com.cafe24.mysite.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.FileVo;
import com.cafe24.mysite.vo.Paging;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean firstInsert(BoardVo boardVo) {
		int count = sqlSession.insert("board.firstInsert", boardVo);
		return 1 == count;
	}
	
	public Boolean insert(BoardVo boardVo) {
		int count = sqlSession.insert("board.insert", boardVo);
		return 1 == count;
	}

	public Boolean getFirstCheck() {
		String check = sqlSession.selectOne("board.firstCheck");
		return check != null;
	}

	public Boolean insertBoard(BoardVo boardVo) {
		int count = sqlSession.insert("board.insertBoard", boardVo);
		return 1 == count;
	}

	public int updateNo(BoardVo boardVo) {
		return sqlSession.update("board.updateNo", boardVo);
	}

	public Boolean insertReply(BoardVo boardVo) {
		int count = sqlSession.insert("board.insertReply", boardVo);
		return 1 == count;
	}

	public List<BoardVo> getList() {
		
		return (ArrayList)sqlSession.selectList("board.selectList");
	}

	public BoardVo getOne(Long no) {
		
		return sqlSession.selectOne("board.selectOne", no);
	}

	public Object delete(Long no) {
		
		return sqlSession.update("board.delete", no);
	}

	public Boolean updateBoard(BoardVo boardVo) {
		int check = sqlSession.update("board.updateBoard", boardVo);
		return check == 1;
	}

	public Long getLastIndex() {
		return sqlSession.selectOne("board.getLastIndex");
	}

	public Boolean insertFile(FileVo fileVo) {
		int check = sqlSession.insert("board.insertFile", fileVo);
		return check == 1;
	}

	public Long getFileLastIndex() {
		return sqlSession.selectOne("board.getFileLastIndex");
	}

	public void deleteFile(Long fileNo) {
		sqlSession.delete("board.deleteErrorFile", fileNo);
	}

	public FileVo getFile(Long no) {
		return sqlSession.selectOne("board.getFileVo", no);
	}

	public void updateHit(Long no) {
		sqlSession.update("board.updateHit", no);
	}

	public Paging getPageInfo() {
		return sqlSession.selectOne("board.getPageInfo");
	}

	public int getListSize() {
		return sqlSession.selectOne("board.getListSize");
	}

	
	
}
