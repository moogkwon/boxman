package com.spring.bm.apv.model.service;

import java.util.List;
import java.util.Map;

import com.spring.bm.apv.model.dao.ApvDao;
import com.spring.bm.calendar.model.vo.Calendar;


public interface ApvService {
	/*결재양식*/
	int insertApvDoc(Map<String, Object> param) throws Exception;
	
	List<Map<String, Object>> selectDocCate();

	List<Map<String, Object>> selectDocForm(int cPage, int numPerPage);

	int selectDfCount();

	Map<String, Object> selectDfModi(int dfNo);

	int updateApvDoc(Map<String, Object> param) throws Exception;

	int deleteApvDoc(int dfNo) throws Exception;

	int insertApvDocHead(Map<String, Object> param) throws Exception;

	int insertApvDocContent(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectDocHCate();

	List<Map<String, Object>> selectDocCCate();

	String selectDfhContent(int no);

	String selectDfcContent(int no);
	
	/*결재라인*/
	List<Map<String, Object>> selectDeptList();

	List<Map<String, Object>> selectMyApvLineList(int cPage, int numPerPage, int loginNo);

	int selectMyALCount(int loginNo);

	List<Map<String, Object>> selectDeptToEmp(int deptNo);

	int insertApvLine(Map<String, Object> param) throws Exception;

	int deleteApvLine(int alNo) throws Exception;

	Map<String, Object> selectALModi(int alno);

	List selectALApplicants(int alno);

	int updateApvLine(Map<String, Object> param) throws Exception;

	Map<String, Object> selectEmpInfoAll(Map<String, Object> loginEmp);

	int insertRequestApv(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectSendApvList(int cPage, int numPerPage, int loginNo);

	int selectSendApvCount(int loginNo);

	List<Map<String, Object>> selectReceiveApvList(int cPage, int numPerPage, int loginNo);

	int selectReceiveApvCount(int loginNo);

	List<Map<String, Object>> selectEnforceApvList(int cPage, int numPerPage, int loginNo);

	int selectEnforceApvCount(int loginNo);

	List<Map<String, Object>> selectReferApvList(int cPage, int numPerPage, int loginNo);

	int selectReferApvCount(int loginNo);

	Map<String, Object> selectLookupApv(int apvNo);

	Map<String, Object> selectLookupApvR(Map<String, Object> param);

	int updateReferYN(Map<String, Object> param) throws Exception;

	Map<String, Object> selectLookupApvA(Map<String, Object> param);

	int apvPermit(Map<String, Object> param) throws Exception;

	int apvReturn(Map<String, Object> param) throws Exception;

	Map<String, Object> selectLookupApvEOne(Map<String, Object> param);

	int apvEnforce(Map<String, Object> param) throws Exception;

	int apvEReturn(Map<String, Object> param) throws Exception;

	int apvAddPermit1(Map<String, Object> param) throws Exception;

	List<Map<String, String>> selectDfSearchList(int cPage, int numPerPage, Map<String, Object> param);

	int selectDfSearchCount(Map<String, Object> param);

	List<Map<String, String>> selectApvlSearchList(int cPage, int numPerPage, Map<String, Object> param);

	int selectApvlSearchCount(Map<String, Object> param);

	Map<String, Object> selectStamp(Map<String, Object> param);

	int apvSaveUpdate(Map<String, Object> param) throws Exception;

	int apvAddPermit2(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectApvList2(int empNo);


}
