package com.spring.bm.acct.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public interface AcctDao {

	List<Map<String, String>> selectICList(SqlSessionTemplate session);

	// 월급 리스트 가져오기
	List<Map<String, String>> selectEmpList(int cPage, int numPerPage, SqlSessionTemplate session);
	int selectEmpCount(SqlSessionTemplate session);
	
	int updateWagePayment(SqlSessionTemplate session, int salno);

	/* severace */
	List<Map<String, String>> selectSevList(SqlSessionTemplate session, int cPage, int numPerPage);

	int updateSeveranceStatus(SqlSessionTemplate session, Map<String, String> m);

	int updateEmployeeStatus(SqlSessionTemplate session, Map<String, String> m);

	/* biztrip */
	List<Map<String, String>> selectBizTripList(int cPage, int numPerPage, SqlSessionTemplate session);
	int selecBizTripCount(SqlSessionTemplate session);

	int selectSevCount(SqlSessionTemplate session);

	
	/* salary search */
	List<Map<String, String>> selectsSalarySearchList(SqlSessionTemplate session, int cPage, int numPerPage, Map<String, Object> param);
	int salarySearchCount(SqlSessionTemplate session, Map<String, Object> param);

	
	/* biztrip search */
	List<Map<String, String>> selectBiztripSearchList(SqlSessionTemplate session, int cPage, int numPerPage, Map<String, Object> param);
	int biztripSearchCount(SqlSessionTemplate session, Map<String, Object> param);

	
	/* severance search */
	List<Map<String, String>> selectSevSearchList(SqlSessionTemplate session, int cPage, int numPerPage, Map<String, Object> param);
	int sevSearchCount(SqlSessionTemplate session, Map<String, Object> param);

	/* 퇴직금한개보기 */
	Map<String, String> selectSevOne(SqlSessionTemplate session, String empno);

	/* biztrip payment */
	int updateBizTripPayment(SqlSessionTemplate session, int data);

	/* severance payment */
	int updateSevPayment(SqlSessionTemplate session, int empno);
	

}