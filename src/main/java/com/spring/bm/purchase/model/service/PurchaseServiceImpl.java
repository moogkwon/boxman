package com.spring.bm.purchase.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bm.purchase.model.dao.PurchaseDao;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	PurchaseDao dao;
	
	@Autowired
	SqlSessionTemplate session;

	@Override
	public List<Map<String, String>> selectPurList(int cPage, int numPerPage) {
		return dao.selectPurList(session, cPage, numPerPage);
	}

	@Override
	public int selectPurCount() {
		return dao.selectPurCount(session);
	}

	@Override
	public List<Map<String, String>> selectConnList() {
		return dao.selectConnList(session);
	}

	@Override
	public Map<String, String> addStuffToTemp(String stuffNo) {
		return dao.addStuffToTemp(session,stuffNo);
	}

	@Override
	public int enrollPurInfo(Map<String, Object> param) throws Exception {
		int result = 0;
		
		result = dao.enrollPurInfo(session,param);	//구매 정보 등록
		if(result==0) throw new RuntimeException();

		if(result!=0) {
			result = 0;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			List<Map<String,Object>> stList = new ArrayList<Map<String,Object>>();
			for(int i=0; i<Integer.parseInt(String.valueOf(param.get("cnt"))); i++) {
				Map<String,Object> stMap = new HashMap<String,Object>();
				stMap.put("stNo", param.get("stNo"+i));
				stMap.put("stNum", param.get("stNum"+i));
				stList.add(stMap);
			}
			paramMap.put("stList",stList);
			result = dao.enrollPurItem(session,paramMap);	//물품 리스트 등록
			if(result==0) throw new Exception();
		}
		return result;
	}

	@Override
	public List<Map<String, String>> selectPurSearchList(Map<String, Object> m) {
		return dao.selectPurSearchList(session,m);
	}

	@Override
	public int selectPurSearchCount(Map<String, Object> m) {
		return dao.selectPurSearchCount(session,m);
	}

	@Override
	public Map<String, String> selectPurInfo(int purCode) {
		return dao.selectPurInfo(session,purCode);
	}

	@Override
	public List<Map<String, String>> selectPurItemList(int purCode) {
		return dao.selectPurItemList(session,purCode);
	}
	
	@Override
	public Map<String, Object> selectPurOne(Map<String, Object> param) {
		return dao.selectPurOne(session,param);
	}

}
