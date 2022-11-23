package com.spring.learn.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.learn.board.NoticeVO;
import com.spring.learn.common.Criteria;
	
	@Repository
	public class NoticeDAO {
		
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public NoticeDAO() {
		System.out.println("=========== noticeDAOMybatis() 객체 생성");
	}
	  
	//글 1개 조회
	public NoticeVO getNotice(NoticeVO vo) {
		System.out.println("===> MyBatis 사용 getNotice() 실행");
	return mybatis.selectOne("noticeDAO.getNotice", vo);
	}
	  
	//글 리스트 조회
//	public List<NoticeVO> getNoticeList(NoticeVO vo) {
//		System.out.println("===> Mybatis 사용 getNoticeList() 실행");
//	return mybatis.selectList("noticeDAO.getNoticeList");
//	}
	  
	//조회수 추가
	public int updateNoticeHit(NoticeVO vo) {
		System.out.println("===> Mybatis 사용 updateNoticeHit() 실행");
	return mybatis.update("noticeDAO.updateNoticeHit");
	}
	  
	//게시글 목록 조회
	public List<NoticeVO> list(Criteria cri) {
		return mybatis.selectList("noticeDAO.listPage", cri);
	}
	  
	//게시글 총 갯수
	public int listCount() {
		return mybatis.selectOne("noticeDAO.listCount");
	}
  
	//검색조건 적용해서 검색
	public List<NoticeVO> getnoticeList(NoticeVO vo) {
		System.out.println("===> Mybatis 사용 getNoticeList(vo) 실행");
		List<NoticeVO> list = null;
		if ("TITLE".equals(vo.getSearchCondition())) {
			list = mybatis.selectList("noticeDAO.getBoardList_T", vo.getSearchKeyword());
		} else {
			list = mybatis.selectList("noticeDAO.getBoardList_C", vo.getSearchKeyword());
		}
		
		for (int i = 0; i < list.size(); i++ ) {
			System.out.println("DAO list 제목( " + i + ") : " + list.get(i).getBoardTitle());	
		}		
		return list;
	}
	
	
	//검색어에 해당하는 게시글 총 갯수
	public int searchListCount(NoticeVO vo) {
		System.out.println("===> Mybatis 사용 searchListCount(vo) 실행");
		int cnt;
		if ("TITLE".equals(vo.getSearchCondition())) {
			cnt = mybatis.selectOne("noticeDAO.getBoardCnt_T", vo.getSearchKeyword());
		} else {
			cnt = mybatis.selectOne("noticeDAO.getBoardCnt_C", vo.getSearchKeyword());
		}
		System.out.println("cnt : " + cnt);
		return cnt;
	}

}




