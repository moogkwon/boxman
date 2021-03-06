package com.spring.bm.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.spring.bm.employee.model.vo.EmpFile;

public interface EmployeeDao {

	/* 사원리스트불러오기 */
	List<Map<String, String>> selectEmpList(SqlSessionTemplate session, int cPage, int numPerPage);
	int selectEmpCount(SqlSessionTemplate session);
	/* 사원리스트불러오기끝 */

	/* 사원등록 */
	int insertEmp(SqlSessionTemplate session, Map<String, String> param);
	/* 사원첨부파일등록 */
	int insertEmpFile(SqlSessionTemplate session, EmpFile e);
	/* 사원등록끝 */
	/* 사원상세보기 */
	Map<String, Object> selectEmpOne(SqlSessionTemplate session, int empNo);
	List<EmpFile> selectEmpFileList(SqlSessionTemplate session, int empNo);
	/* 사원로그인*/
	Map<String, Object> selectLoginEmp(SqlSessionTemplate session, Map<String, Object> map);
	/* 사원검색 */
	List<Map<String, String>> selectEmpSearchList(SqlSessionTemplate session, int cPage, int numPerPage, Map<String, Object> param);
	int selectEmpSearchCount(SqlSessionTemplate session, Map<String, Object> param);
	/* 아이디 중복확인 */
	int checkId(SqlSessionTemplate session, String empId);
	/* 첨부파일삭제 */
	int deleteEmpFile(SqlSessionTemplate session, int efNo);
	/* 사원수정 */
	int updateEmp(SqlSessionTemplate session, Map<String, Object> param);
	/* 비밀번호변경 */
	int updatePassword(SqlSessionTemplate session, Map<String, Object> param);
	/* 출퇴근 위치정보 확인 */
	int checkLocation(SqlSessionTemplate session, Map<String, Object> param);
	/* 출근등록 */
	int insertGotoWork(SqlSessionTemplate session, Map<String, Object> param);
	/* 퇴근등록 */
	int updateOffWork(SqlSessionTemplate session, Map<String, Object> param);
	/* 근태하나보기 */
	Map<String, Object> selectAttenOne(SqlSessionTemplate session, Map<String, Object> param);
	/* 근태현황보기 */
	List<Map<String, String>> selectAttenList(SqlSessionTemplate session, Map<String, Object> param, int cPage,
			int numPerPage);
	int selectAttenCount(SqlSessionTemplate session, Map<String, Object> param);
	/* 휴가리스트 출력 */
	List<Map<String, String>> selectDayOffList(SqlSessionTemplate session, Map<String, Object> param, int cPage,
			int numPerPage);
	int selectDayOffCount(SqlSessionTemplate session, Map<String, Object> param);
	/* 출장리스트 출력 */
	List<Map<String, String>> selectBTList(SqlSessionTemplate session, Map<String, Object> param, int cPage,
			int numPerPage);
	int selectBTCount(SqlSessionTemplate session, Map<String, Object> param);
	/* 근태수정용 한개보기 */
	Map<String, Object> selectAttenNoOne(SqlSessionTemplate session, Map<String, Object> param);
	/* 근태수정신청 */
	int insertUpAttendance(SqlSessionTemplate session, Map<String, Object> param);
	/* 남은휴가일수 보기 */
	int selectDoRemaining(SqlSessionTemplate session, Map<String, Object> map);
	/* 휴가신청 */
	int insertDayOff(SqlSessionTemplate session, Map<String, Object> param);
	/* 출장신청 */
	int insertBT(SqlSessionTemplate session, Map<String, Object> param);
	/* 출장비리스트 */
	List<Map<String, Object>> selectBTPList(SqlSessionTemplate session, Map<String, Object> param);
	/* 출장하나 */
	Map<String, Object> selectBTOne(SqlSessionTemplate session, Map<String, Object> param);
	/* 출장비신청 */
	int insertBTP(SqlSessionTemplate session, Map<String, Object> param);
	/* 사원통계 */
	List<Map<String, Object>> empYearCount(SqlSessionTemplate session);
	List<Map<String, Object>> newEmpYearCount(SqlSessionTemplate session);
	List<Map<String, Object>> entEmpYearCount(SqlSessionTemplate session);
	/* 사원통계끝 */
	/* 근태수정한개보기 */
	Map<String, Object> selectUpAttendanceOne(SqlSessionTemplate session, int result);
	/* 휴가한개보기 */
	Map<String, Object> selectDayoffOne(SqlSessionTemplate session, int doNo);
	/* 출장비한개보기 */
	Map<String, Object> selectBTPOne(SqlSessionTemplate session, int btpNo);

}