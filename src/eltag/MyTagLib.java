package eltag;

import com.sun.org.apache.regexp.internal.recompile;

import model.MemberDAO;

public class MyTagLib {
	
/*
	EL에서 Java클래스의 메소드 호출 절차 및 방법
	
	1. 클래스와 메소드를 정의한다. 
		단, 메소드 정의 시 반드시 public static 으로 선언해야 한다.
	2. TLD파일을 생성한다. Tag Library Descriptor의 약자로
		WEB-INF폴더 하위 아무곳이나 생성하면 컨테이너가 찾아서 사용하게 된다.
		우리는 tlds폴더를 생성한 후 해당 파일을 작성한다. 
		파일 작성 시 fn.tld를  참조한다.
	3. web.xml에 내가 정의한 태그라이브러리 파일을 설정한다.
		taglib-uri : JSP의 taglib지시어의 uri속성에 삽입할 값
		taglib-location : 실제 tld 파일의 위치(경로를 삽입함)
	4. jsp파일에서 EL식으로 메소드를 호출한다.
		페이지 상단에 taglib 지시어를 통한 디렉티브를 추가한 후
		${접두어:메소드명(인자)} 형태로 호출한다.
*/
	
	/*
	메소드 설명 : 매개변수로 전달된 문자열에 숫자가 아닌 문자가
		포함되어 있으면 false를 반환하고, 전체가 숫자일 때
		true를 반환한다.
	*/
	public static boolean isNumber(String value) {
		
		char[] chArr = value.toCharArray();
		
		for(int i = 0; i < chArr.length; i++) {
			if( !(chArr[i] >= '0' && chArr[i] <= '9')) {
				return false;
			}
		}
		return true;
	}
	
	/*
	메소드 설명 : 매개변수로 주민번호를 전달받아 성별을 판단하여 반환하는
		메소드를 작성한다. 주민번호는 123456-7890123 형태로 전달된다.
	*/
	public static String getGender(String jumin) {
		
		String returnStr = "";
		
		/*
		//방법 1 : CharAt()
		if(jumin.charAt(7)=='1' || jumin.charAt(7)=='3') {
			returnStr = "남자";
		}
		else if(jumin.charAt(7)=='2' || jumin.charAt(7)=='4') {
			returnStr = "여자";
		}
		else if(jumin.charAt(7)=='5' || jumin.charAt(7)=='6') {
			returnStr = "외국인";
		}
		else {
			returnStr = "주민번호가 잘못되었습니다..";
		}
		return returnStr;
		*/
		
		//방법 2 : indexOf()
		//123456-7890123 에서 -과 7 사이 인덱스 가져옴
		int beginIdx = jumin.indexOf("-") + 1;
		//123456-7890123 에서 7의 앞과 뒤를 자른다.
		String genderStr = jumin.substring(beginIdx, beginIdx + 1);
		//123456-7890123 에서 자른 7을 Integer를 사용해 문자형에서 숫자로 변경한다. 
		if(Integer.parseInt(genderStr) == 1 || 
				Integer.parseInt(genderStr) == 3) {
			returnStr = "남자";
		}
		else if(Integer.parseInt(genderStr) == 2 || 
					Integer.parseInt(genderStr) == 4) {
			returnStr = "여자";
		}
		else if(Integer.parseInt(genderStr) == 5 || 
					Integer.parseInt(genderStr) == 6) {
		returnStr = "외국인";
	}
		else {
			returnStr = "주민번호가 잘못되었습니다..";
		}
		
		return returnStr;
		
	}
	
	
	/*
	아이디, 패스워드, DB연결을 위한 드라이버, URL을 인자로 받아
	회원여부를 판단하여 true/false를 반환해주는 메소드
	*/
	public static boolean memberLogin(String id, String pw, 
			String drv, String url) {
		// 1. DB연결을 위한 객체생성
		MemberDAO dao = new MemberDAO(drv, url);
		
		// 2. 아이디/패스워드를 통한 회원인증 및 결과반환
		boolean isBool = dao.isMember(id, pw);
		
		// 3. 2번에서의 결과를 호출한 지점으로 반환
		return isBool;
	}
	
	
	
}

















