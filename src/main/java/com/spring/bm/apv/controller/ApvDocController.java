package com.spring.bm.apv.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bm.apv.model.service.ApvService;
import com.spring.bm.common.PageBarFactory;
import com.spring.bm.common.PageUrlFactory;


@Controller
public class ApvDocController {
	private Logger logger=LoggerFactory.getLogger(ApvDocController.class);
	private String path=new PageUrlFactory().getUrl();

	@Autowired
	ApvService service;
	
	/* 결재 양식 관리 리스트 뷰 호출 */
	@RequestMapping("/apv/apvDoc.do")
	public ModelAndView apvDoc(@RequestParam(value="cPage", 
			required=false, defaultValue="1") int cPage) {
		int numPerPage = 10;
		List<Map<String,Object>> list=service.selectDocForm(cPage,numPerPage);
		int totalCount = service.selectDfCount();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageBar",PageBarFactory.getPageBar(totalCount, cPage, numPerPage, path+"/apv/apvDoc.do"));
		mv.addObject("count", totalCount);
		mv.addObject("list",list);
		mv.setViewName("apv/apvDocList");
		return mv;
	}
	
	/* 결재 양식 등록 팝업창 */
	@RequestMapping("/apv/apvDocEnroll.do")
	public ModelAndView apvDocEnroll() {
		ModelAndView mv = new ModelAndView();
		List<Map<String,Object>> docCate=service.selectDocCate();
		List<Map<String,Object>> docHCate=service.selectDocHCate();
		List<Map<String,Object>> docCCate=service.selectDocCCate();
		mv.addObject("docCate",docCate);
		mv.addObject("docHCate",docHCate);
		mv.addObject("docCCate",docCCate);
		mv.setViewName("apv/apvDocEnroll");
		return mv;
	}
	
	/* 결재 양식 등록 로직*/
	@RequestMapping("/apv/apvDocEnrollEnd.do")
	@ResponseBody
	public int apvDocEnrollEnd(@RequestParam Map<String,Object> param){
		int result=0;
		try {
			result=service.insertApvDoc(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	/* 결재 양식 수정 팝업창 */
	@RequestMapping("/apv/apvDocModify.do")
	public ModelAndView apvDocModify(@RequestParam(value="dfNo", 
			required=false, defaultValue="1") int dfNo) {
		ModelAndView mv = new ModelAndView();
		List<Map<String,Object>> docCate=service.selectDocCate();
		Map<String,Object> dfOne=service.selectDfModi(dfNo);
		
		mv.addObject("dfOne",dfOne);
		mv.addObject("docCate",docCate);
		mv.setViewName("apv/apvDocModi");
		return mv;
	}
	
	/* 결재 양식 수정 로직*/
	@RequestMapping("/apv/apvDocModiEnd.do")
	@ResponseBody
	public int apvDocModiEnd(@RequestParam Map<String,Object> param){
		int result=0;
		try {
			result=service.updateApvDoc(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/* 결재 양식 삭제 로직*/
	@RequestMapping("/apv/apvDocDelete.do")
	public ModelAndView apvDocDelete(@RequestParam(value="dfNo", 
			required=false, defaultValue="1") int dfNo){
		ModelAndView mv = new ModelAndView();
		
		int result=0;
		String msg="";
		String loc="/apv/apvDoc.do";
		try {
			result=service.deleteApvDoc(dfNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result>0) {
			msg="결재양식 삭제 성공";
		}else {
			msg="결재 양식 삭제 실패";
		}
		
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		return mv;
	}
	
	
	/* 결재폼 양식 등록 팝업창 */
	@RequestMapping("/apv/apvDocHeadEnroll.do")
	public String apvDocHeadEnroll() {
		return "apv/apvDocHeadEnroll";
	}
	
	/* 결재폼 양식 등록 로직*/
	@RequestMapping("/apv/apvDocHeadEnrollEnd.do")
	@ResponseBody
	public int apvDocHeadEnrollEnd(@RequestParam Map<String,Object> param){
		int result=0;
		try {
			result=service.insertApvDocHead(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/* 결재 본문 양식 등록 팝업창 */
	@RequestMapping("/apv/apvDocContentEnroll.do")
	public String apvDocContentEnroll() {
		return "apv/apvDocContentEnroll";
	}
	
	/* 결재본문 양식 등록 로직*/
	@RequestMapping("/apv/apvDocContentEnrollEnd.do")
	@ResponseBody
	public int apvDocContentEnrollEnd(@RequestParam Map<String,Object> param){
		int result=0;
		try {
			result=service.insertApvDocContent(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/* 결재양식 등록->결재폼옵션 변경 로직  */
	@RequestMapping(value="/apv/apvDocHChange.do",produces ="application/text; charset=utf8")
	@ResponseBody
	public String apvDocHChange(@RequestParam Map<String,Object> param){
		int no=Integer.parseInt((String) param.get("no"));
		String code=service.selectDfhContent(no);
		return code;
	}
	
	/* 결재양식 등록->본문 양식 변경 로직  */
	@RequestMapping(value="/apv/apvDocCChange.do",produces ="application/text; charset=utf8")
	@ResponseBody
	public String apvDocCChange(@RequestParam Map<String,Object> param){
		int no=Integer.parseInt((String) param.get("no"));
		String code=service.selectDfcContent(no);
		return code;
	}
	
//	기안하기------------
	/* 기안하기 리스트 뷰 */
	@RequestMapping("/apv/requestApv.do")
	public ModelAndView requestApvMain(@RequestParam(value="cPage", 
			required=false, defaultValue="1") int cPage) {
		int numPerPage = 10;
		List<Map<String,Object>> list=service.selectDocForm(cPage,numPerPage);
		int totalCount = service.selectDfCount();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageBar",PageBarFactory.getPageBar(totalCount, cPage, numPerPage, path+"/apv/requestApv.do"));
		mv.addObject("count", totalCount);
		mv.addObject("list",list);
		mv.setViewName("apv/requestApvMain");
		return mv;
	}
	
	/* 기안 등록 뷰 */
	@RequestMapping("/apv/requestApvEnroll.do")
	public ModelAndView requestApvEnroll(@RequestParam(value="dfNo", 
			required=false, defaultValue="1") int dfNo,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		/* List<Map<String,Object>> docCate=service.selectDocCate(); */
		Map<String,Object> dfOne=service.selectDfModi(dfNo);
		
		//테이블에 값 넣기
		//먼저 index로 변수들이 있는지 파악
		//-1이면 테이블에 해당 변수 없음.
		//해당 변수가 있을 때 replace 시켜줌.
		//replace 시켜줄 때는, 태그로 감싸서 넣기!!나중에 뽑아 쓸 수 있도록 id값을 설정해서 넣기!!
		//그대로 출력
		Map<String,Object> loginEmp=(Map<String, Object>) session.getAttribute("loginEmp");
		Map<String,Object> empInfo=service.selectEmpInfoAll(loginEmp);
		
		String head=((String)dfOne.get("DFHEADFORM"));
		
		///head에서 index 다 돌려~~!!
		if(head.indexOf("{{title}}")>-1) {
			String content=(String)dfOne.get("DFTITLE");
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{title}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{empName}}")>-1) {
			String content=(String)empInfo.get("EMPNAME");
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{empName}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{empEmail}}")>-1) {
			String content=(String)empInfo.get("EMPEMAIL");
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{empEmail}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{deptName}}")>-1) {
			String content=(String)empInfo.get("DEPTNAME");
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{deptName}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{jobName}}")>-1) {
			String content=(String)empInfo.get("JOBNAME");
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{jobName}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{text_box_1}}")>-1) {
			String content="<input type='text' id='textBox1'/>";
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{text_box_1}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{approval_line_html}}")>-1) {
			String content="<table id=\"approval_line_html\" border=\"1px;\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" valign=\"middle\" width=\"100%\" height=\"100%\"><tr><td id=\"prior1\">1</td><td id=\"prior2\"></td><td id=\"prior3\"></td><td id=\"prior4\"></td><td id=\"prior5\"></td></tr><tr height=\"100px;\"><td id=\"stamp1\"></td><td id=\"stamp2\"></td><td id=\"stamp3\"></td><td id=\"stamp4\"></td><td id=\"stamp5\"></td></tr></table>";
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{approval_line_html}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{request_name}}")>-1) {
			String content="<p id=\"requestName\">시행자명</p>";
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{request_name}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{app_sdate}}")>-1) {
			String content="<input type='date' id=\"sDate\" />";
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{app_sdate}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{app_edate}}")>-1) {
			String content="<input type='date' id=\"eDate\" />";
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{app_edate}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		if(head.indexOf("{{pay}}")>-1) {
			String content="<input type='number' id=\"pay\" />";
			String form=((String)dfOne.get("DFHEADFORM")).replace("{{pay}}", content);
			dfOne.put("DFHEADFORM", form);
		}
		 
		mv.addObject("dfOne",dfOne);
		/* mv.addObject("docCate",docCate); */
		mv.setViewName("apv/requestApvEnroll");
		return mv;
	}
	
	/* 기안 상신하기 로직 */
	@RequestMapping(value="/apv/requestApvEnrollEnd.do",method=RequestMethod.POST)
	@ResponseBody
	public int requestApvEnrollEnd(@RequestBody Map<String,Object> param){
		int result=0;
		try { 
			result=service.insertRequestApv(param);
		}
		catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	
	/*상신함*/
	@RequestMapping("/apv/sendApv.do")
	public ModelAndView sendApvList(@RequestParam(value="cPage", 
		required=false, defaultValue="1") int cPage,int loginNo) {
		int numPerPage = 10;
		
		List<Map<String,Object>> list=service.selectSendApvList(cPage,numPerPage,loginNo);
		int totalCount = service.selectSendApvCount(loginNo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageBar",PageBarFactory.getPageBar(totalCount, cPage, numPerPage,  path+"/apv/sendApv.do", loginNo));
		mv.addObject("count", totalCount);
		mv.addObject("list",list);
		mv.setViewName("apv/sendApv");
		return mv;
	}
	
	/*상신함 -> 조회하기*/
	@RequestMapping("/apv/lookupApvOne.do")
	public ModelAndView lookupApvOne(@RequestParam(value="no", 
	required=true) int apvNo) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> apvOne=service.selectLookupApv(apvNo);
		
		mv.addObject("apvOne",apvOne);
		mv.setViewName("apv/lookupApvOne");
		return mv;
	}
	
	/*수신함*/
	@RequestMapping("/apv/receiveApvList.do")
	public ModelAndView receiveApvList(@RequestParam(value="cPage", 
		required=false, defaultValue="1") int cPage,int loginNo) {
		int numPerPage = 10;
		
		List<Map<String,Object>> list=service.selectReceiveApvList(cPage,numPerPage,loginNo);
		int totalCount = service.selectReceiveApvCount(loginNo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageBar",PageBarFactory.getPageBar(totalCount, cPage, numPerPage, path+"/apv/receiveApvList.do", loginNo));
		mv.addObject("count", totalCount);
		mv.addObject("list",list);
		mv.setViewName("apv/receiveApvList");
		return mv;
	}
	
	/*시행함*/
	@RequestMapping("/apv/enforceApvList.do")
	public ModelAndView enforceApvList(@RequestParam(value="cPage", 
		required=false, defaultValue="1") int cPage,int loginNo) {
		System.out.println(loginNo);
		int numPerPage = 10;
		
		List<Map<String,Object>> list=service.selectEnforceApvList(cPage,numPerPage,loginNo);
		int totalCount = service.selectEnforceApvCount(loginNo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageBar",PageBarFactory.getPageBar(totalCount, cPage, numPerPage, path+"/apv/enforceApvList.do", loginNo));
		mv.addObject("count", totalCount);
		mv.addObject("list",list);
		mv.setViewName("apv/enforceApvList");
		return mv;
	}
	
	/*참조함*/
	@RequestMapping("/apv/referApvList.do")
	public ModelAndView referApvList(@RequestParam(value="cPage", 
		required=false, defaultValue="1") int cPage,int loginNo) {
		int numPerPage = 10;
		
		List<Map<String,Object>> list=service.selectReferApvList(cPage,numPerPage,loginNo);
		int totalCount = service.selectReferApvCount(loginNo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageBar",PageBarFactory.getPageBar(totalCount, cPage, numPerPage, path+"/apv/referApvList.do", loginNo));
		mv.addObject("count", totalCount);
		mv.addObject("list",list);
		mv.setViewName("apv/referApvList");
		return mv;
	}
	
	/*참조함 -> 조회하기*/
	@RequestMapping("/apv/lookupApvROne.do")
	public ModelAndView lookupApvROne(@RequestParam(value="apvNo", 
	required=true) int apvNo,int empNo) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("apvNo", apvNo);
		param.put("empNo", empNo);
		Map<String,Object> apvOne=service.selectLookupApvR(param);
		if(String.valueOf(apvOne.get("OPENYN")).trim().equals("N")) {
			int result=0;
			try { 
				result=service.updateReferYN(param);
			}
			catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		
		mv.addObject("apvOne",apvOne);
		mv.setViewName("apv/lookupApvOne");
		return mv;
	}
	
	/*결재함 -> 결재하기 뷰*/
	@RequestMapping("/apv/lookupApvAOne.do")
	public ModelAndView lookupApvAOne(@RequestParam(value="apvNo", 
	required=true) int apvNo,int empNo) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("apvNo", apvNo);
		param.put("empNo", empNo);
		Map<String,Object> apvOne=service.selectLookupApvA(param);
		
		mv.addObject("apvOne",apvOne);
		mv.setViewName("apv/lookupApvOne");
		return mv;
	}
	
	/*결재하기뷰->결재처리*/
	@RequestMapping(value="/apv/apvPermit.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> apvPermit(@RequestParam Map<String,Object> param) {
		int result=0;
		String msg="";
		try {
			result=service.apvPermit(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String,Object> map=new HashMap<String, Object>();
		if(result>0) {
			msg="결재처리 완료";
			map=service.selectStamp(param);
			map.put("msg", msg);
		}else {
			msg="결재처리 실패";
			map.put("msg", msg);
		}
		return map;
	}
	
	/*결재하기뷰->반려처리*/
	@RequestMapping("/apv/apvReturn.do")
	public ModelAndView apvReturn(@RequestParam(value="apvNo", 
	required=true) int apvNo,int empNo,String moreInfo) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("apvNo", apvNo);
		param.put("empNo", empNo);
		param.put("moreInfo", moreInfo);
		
		int result=0;
		String msg="";
		String loc="/apv/lookupApvAOne.do?apvNo="+apvNo+"&empNo="+empNo;
		try {
			result=service.apvReturn(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result>0) {
			msg="반려처리 완료";
		}else {
			msg="반려처리 실패";
		}
		
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		return mv;
	}
	
	/*시행함 -> 시행관리 뷰*/
	@RequestMapping("/apv/lookupApvEOne.do")
	public ModelAndView lookupApvEOne(@RequestParam(value="apvNo", 
	required=true) int apvNo,int empNo) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("apvNo", apvNo);
		param.put("empNo", empNo);
		Map<String,Object> apvOne=service.selectLookupApvEOne(param);
		
		mv.addObject("apvOne",apvOne);
		mv.setViewName("apv/lookupApvOne");
		return mv;
	}
	
	/*시행관리뷰->시행처리*/
	@RequestMapping("/apv/apvEnforce.do")
	public ModelAndView apvEnforce(@RequestParam(value="apvNo", 
	required=true) int apvNo,int empNo) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("apvNo", apvNo);
		param.put("empNo", empNo);
		
		int result=0;
		String msg="";
		String loc="/apv/lookupApvEOne.do?apvNo="+apvNo+"&empNo="+empNo;
		try {
			result=service.apvEnforce(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result>0) {
			msg="시행처리 완료";
		}else {
			msg="시행처리 실패";
		}
		
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		return mv;
	}
	
	/*결재하기뷰->반려처리*/
	@RequestMapping("/apv/apvEReturn.do")
	public ModelAndView apvEReturn(@RequestParam(value="apvNo", 
	required=true) int apvNo,int empNo,String moreInfo) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("apvNo", apvNo);
		param.put("empNo", empNo);
		param.put("moreInfo", moreInfo);
		
		int result=0;
		String msg="";
		String loc="/apv/lookupApvEOne.do?apvNo="+apvNo+"&empNo="+empNo;
		try {
			result=service.apvEReturn(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result>0) {
			msg="반송처리 완료";
		}else {
			msg="반송처리 실패";
		}
		
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/apv/addReqApvEnroll.do")
	public ModelAndView addReqApvEnroll(@RequestParam Map<String, Object> param,HttpSession session) {
	ModelAndView mv = new ModelAndView();
	
	int dfNo=0;
	String cate=String.valueOf(param.get("temp")).trim();
	String ckCol=String.valueOf(param.get("checkCol")).trim();
	String pkey=String.valueOf(param.get("pkey")).trim();
	int totalPay=0;
	String sDate="";
	String eDate="";
	int cateNo=0;
	String ckCol2="";
	String ckColDate="";
	String ckColDate2="";
	switch(cate) {
	case "businessTripPay":
		cateNo=Integer.parseInt(String.valueOf(param.get("BTPNO")));
		dfNo=102;
		totalPay=Integer.parseInt(String.valueOf(param.get("BTPROOM")))+Integer.parseInt(String.valueOf(param.get("BTPTRANSPORTATION")))+Integer.parseInt(String.valueOf(param.get("BTPENTERTAIN")));
		sDate=String.valueOf(param.get("BTSTART"));
		eDate=String.valueOf(param.get("BTEND"));
		break;
	case "upAttendance":
		cateNo=Integer.parseInt(String.valueOf(param.get("UANO")));
		dfNo=121;
		sDate=String.valueOf(param.get("UADATE"));
		break;
	case "dayoff":
		cateNo=Integer.parseInt(String.valueOf(param.get("DONO")));
		dfNo=122;
		sDate=String.valueOf(param.get("DOSTART"));
		eDate=String.valueOf(param.get("DOEND"));
		break;
	case "businessTrip":
		cateNo=Integer.parseInt(String.valueOf(param.get("BTNO")));
		dfNo=120;
		sDate=String.valueOf(param.get("BTSTART"));
		eDate=String.valueOf(param.get("BTEND"));
		break;
	case "purchaseTab":
		cateNo=Integer.parseInt(String.valueOf(param.get("PURCODE")));
		dfNo=123;//여기 수정
		totalPay=Integer.parseInt(String.valueOf(param.get("PURTOTAMT")));
		ckCol2=String.valueOf(param.get("checkCol2")).trim();
		ckColDate=String.valueOf(param.get("checkColDate")).trim();
		ckColDate2=String.valueOf(param.get("checkColDate2")).trim();
		break;
	case "saleTab":
		cateNo=Integer.parseInt(String.valueOf(param.get("SALCODE")));
		dfNo=124;//여기 수정
		totalPay=Integer.parseInt(String.valueOf(param.get("SALTOTAMT")));
		ckCol2=String.valueOf(param.get("checkCol2")).trim();
		ckColDate=String.valueOf(param.get("checkColDate")).trim();
		ckColDate2=String.valueOf(param.get("checkColDate2")).trim();
		break;
	}
	
	
	/* List<Map<String,Object>> docCate=service.selectDocCate(); */
	Map<String,Object> dfOne=service.selectDfModi(dfNo);
	
	//테이블에 값 넣기
	//먼저 index로 변수들이 있는지 파악
	//-1이면 테이블에 해당 변수 없음.
	//해당 변수가 있을 때 replace 시켜줌.
	//replace 시켜줄 때는, 태그로 감싸서 넣기!!나중에 뽑아 쓸 수 있도록 id값을 설정해서 넣기!!
	//그대로 출력
	Map<String,Object> loginEmp=(Map<String, Object>) session.getAttribute("loginEmp");
	Map<String,Object> empInfo=service.selectEmpInfoAll(loginEmp);
	
	String head=((String)dfOne.get("DFHEADFORM"));
	
	///head에서 index 다 돌려~~!!
	if(head.indexOf("{{pkey}}")>-1) {
		String content="<input type='hidden' id=\"pkey\" value='"+pkey+"' />";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{pkey}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{ckCol}}")>-1) {
		String content="<input type='hidden' id=\"ckCol\" value='"+ckCol+"' />";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{ckCol}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{ckCol2}}")>-1) {
		String content="<input type='hidden' id=\"ckCol2\" value='"+ckCol2+"' />";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{ckCol2}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{ckColDate}}")>-1) {
		String content="<input type='hidden' id=\"ckColDate\" value='"+ckColDate+"' />";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{ckColDate}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{ckColDate2}}")>-1) {
		String content="<input type='hidden' id=\"ckColDate2\" value='"+ckColDate2+"' />";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{ckColDate2}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{cateName}}")>-1) {
		String content="<input type='hidden' id=\"cateName\" value='"+cate+"' />";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{cateName}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{cateNo}}")>-1) {
		String content="<input type='hidden' id=\"cateNo\" value='"+cateNo+"' />";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{cateNo}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{title}}")>-1) {
		String content=(String)dfOne.get("DFTITLE");
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{title}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{empName}}")>-1) {
		String content=(String)empInfo.get("EMPNAME");
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{empName}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{empEmail}}")>-1) {
		String content=(String)empInfo.get("EMPEMAIL");
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{empEmail}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{deptName}}")>-1) {
		String content=(String)empInfo.get("DEPTNAME");
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{deptName}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{jobName}}")>-1) {
		String content=(String)empInfo.get("JOBNAME");
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{jobName}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{text_box_1}}")>-1) {
		String content="<input type='text' id='textBox1'/>";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{text_box_1}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{approval_line_html}}")>-1) {
		String content="<table id=\"approval_line_html\" border=\"1px;\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" valign=\"middle\" width=\"100%\" height=\"100%\"><tr><td id=\"prior1\">1</td><td id=\"prior2\"></td><td id=\"prior3\"></td><td id=\"prior4\"></td><td id=\"prior5\"></td></tr><tr height=\"100px;\"><td id=\"stamp1\"></td><td id=\"stamp2\"></td><td id=\"stamp3\"></td><td id=\"stamp4\"></td><td id=\"stamp5\"></td></tr></table>";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{approval_line_html}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{request_name}}")>-1) {
		String content="<p id=\"requestName\">시행자명</p>";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{request_name}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{app_sdate}}")>-1) {
		String content="<p id=\"sDate\" >"+sDate+"</p>";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{app_sdate}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{app_edate}}")>-1) {
		String content="<p id=\"eDate\" >"+eDate+"</p>";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{app_edate}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	if(head.indexOf("{{pay}}")>-1) {
		String content="<p id=\"pay\" >"+totalPay+"</p>";
		String form=((String)dfOne.get("DFHEADFORM")).replace("{{pay}}", content);
		dfOne.put("DFHEADFORM", form);
	}
	
	 
	mv.addObject("dfOne",dfOne);
	/* mv.addObject("docCate",docCate); */
	mv.setViewName("apv/requestApvEnrollAdd");
	return mv;
	}
	
	
	/*결재하기뷰->추가결재 결재처리*/
	/*상태YN만 바꾸는 애!!*/
	@RequestMapping(value="/apv/apvAddPermit.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> apvAddPermit(@RequestParam Map<String,Object> m) {
		
		//똑같이 처리하는데, serviceImpl에서 종결처리 될 경우에, 가진정보로 테이블 상태 'Y'로 바꿈
		//가져온 정보가...
		//테이블이름,프라이머리키번호,시작일,끝일,청구비용,
		//상태 yn으로 바꿀 컬럼명
		  
		  int result=0;
		  String msg="";
		  try {
			  if(String.valueOf(m.get("cateName")).equals("purchaseTab") || String.valueOf(m.get("cateName")).equals("saleTab")) {
				  result=service.apvAddPermit2(m);
			  }else {
				  result=service.apvAddPermit1(m);
			  }
		  }
		  catch (Exception e) { 
			 e.printStackTrace(); 
		  }

		  Map<String,Object> map=new HashMap<String, Object>();
			if(result>0) {
				msg="결재처리 완료";
				map=service.selectStamp(m);
				map.put("msg", msg);
			}else {
				msg="결재처리 실패";
				map.put("msg", msg);
			}
			return map;
	}
	@RequestMapping(value="/apv/apvSaveUpdate.do",method=RequestMethod.POST)
	@ResponseBody
	public int apvSaveUpdate(@RequestParam Map<String,Object> param) {
		  
		  int result=0;
		  try {
		  result=service.apvSaveUpdate(param); 
		  }
		  catch (Exception e) { 
			 e.printStackTrace(); 
		  }
			
		return result;
	}
	
	/*결재양식 검색*/
	 @RequestMapping("/apv/searchDocForm.do")
	   public ModelAndView searchDocForm(@RequestParam(value="cPage",required=false, defaultValue="0") int cPage,
	         @RequestParam Map<String, Object> param) {

	      int numPerPage = 10;
	      List<Map<String, String>> list = service.selectDfSearchList(cPage, numPerPage, param);
	      int totalCount = service.selectDfSearchCount(param);
	      ModelAndView mv=new ModelAndView();
	      mv.addObject("pageBar", PageBarFactory.getPageBar(totalCount, cPage, numPerPage, path+"/apv/searchDocForm.do",""+param.get("type"), ""+param.get("data")));
	      mv.addObject("count", totalCount);
	      mv.addObject("list", list);
	      mv.setViewName("apv/apvDocList");
	      return mv;
	   }
	 @RequestMapping("/apv/searchDocFormReq.do")
	   public ModelAndView searchDocFormReq(@RequestParam(value="cPage",required=false, defaultValue="0") int cPage,
	         @RequestParam Map<String, Object> param) {

	      int numPerPage = 10;
	      List<Map<String, String>> list = service.selectDfSearchList(cPage, numPerPage, param);
	      int totalCount = service.selectDfSearchCount(param);
	      ModelAndView mv=new ModelAndView();
	      mv.addObject("pageBar", PageBarFactory.getPageBar(totalCount, cPage, numPerPage, path+"/apv/searchDocFormReq.do",""+param.get("type"), ""+param.get("data")));
	      mv.addObject("count", totalCount);
	      mv.addObject("list", list);
	      mv.setViewName("apv/requestApvMain");
	      return mv;
	   }
	 
	 
}
