package com.cafe24.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.FileVo;
import com.cafe24.mysite.vo.Paging;

@Service
public class BoardService {
	private static final String SAVE_PATH = "/mysite-uploads";
	private static final String URL = "/files";
	
	@Autowired
	private BoardDao boardDao;
	
	public Boolean insertBoard(BoardVo boardVo) {
		Boolean firstCheck = boardDao.getFirstCheck();
		
		if(firstCheck) {
			if(boardVo.getGroupNo() == 0) {
				return boardDao.insertBoard(boardVo);
			}else {
				System.out.println("service vo 확인 " + boardVo);
				boardDao.updateNo(boardVo);
				return boardDao.insertReply(boardVo);
			}
		}else {
			return boardDao.firstInsert(boardVo);
		}
	}
	
	public List<BoardVo> getList(Paging pg) {
		pg.setTotal(boardDao.getListSize());
		
		pg.setMaxPage((int)Math.ceil(pg.getTotal()/pg.getPerPage()));
		System.out.println(pg);
		return boardDao.getList();
	}
	
	public Paging getPageInfo() {
		return boardDao.getPageInfo();
	}
	
	
	public BoardVo getOne(Long no) {
		boardDao.updateHit(no);
		return boardDao.getOne(no);
	}
	
	public void delete(Long no) {
		boardDao.delete(no);
	}

	public Boolean updateBoard(BoardVo boardVo) {
		return boardDao.updateBoard(boardVo);
	}
	
	public Long getLastIndex() {
		return boardDao.getLastIndex();
	}
	
	public Boolean uploadFile(MultipartFile multipartFile, Long boardNo) {
		Boolean check = false;
		try {
			
			FileVo fileVo = new FileVo();
			fileVo.setBoardNo(boardNo);
			fileVo.setOriName(multipartFile.getOriginalFilename());
			String extName = fileVo.getOriName().substring(fileVo.getOriName().lastIndexOf('.') + 1);
			fileVo.setChangeName(generateSaveFileName(extName));
			fileVo.setSize(multipartFile.getSize());
			fileVo.setPath(SAVE_PATH + "/" + fileVo.getChangeName());
			fileVo.setUrl(SAVE_PATH + "/" + fileVo.getChangeName());
			
			check = boardDao.insertFile(fileVo);
			
			if(check) {
				byte[] fileData = multipartFile.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + fileVo.getChangeName());
				os.write(fileData);
				os.close();
			}else {
				Long fileNo = boardDao.getFileLastIndex();
				boardDao.deleteFile(fileNo);
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		
		return check;
	}


	private String generateSaveFileName(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();

		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	public FileVo getFile(Long no) {
		return boardDao.getFile(no);
	}

	

	
	

	
	

	
}
