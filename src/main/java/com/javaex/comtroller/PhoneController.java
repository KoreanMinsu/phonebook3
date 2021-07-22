package com.javaex.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	//field
	//constructor
	//method-g/s
	//method-generic
	
	//LIST
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[PhonebookController.list]");
		
		//Dao사용
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.getPersonList();
		
		//model담기-dispatcherServlet-request.setAttribute
		model.addAttribute("phoneDao", phoneDao);
		
		return "/WEB-INF/views/list.jsp";
		//직접포워드가 아닌 DispatcherServlet 에게 return값 포워딩 요청
	}
	
	
	//WRITEFORM
	@RequestMapping(value="/writeform", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	//writeform?=name="name"&hp="hp"&company="company"
	
	
	//WRITE
		//1.	파라미터유무 무관할때
	
	/*	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
		public String write(@RequestParam("name") String name
				, @RequestParam("hp") String hp
				, @RequestParam(value="company", required = false, defaultValue = "-1") String company) {
			System.out.println("[PhoneController.write]");
				
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			return "WEB-INF/views/write.jsp";
		}
	*/	
		
		//2. 	ModelAttribute로 받을때
	
		@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
		public String write(@ModelAttribute PersonVo personVo) {
				
				//@ModelAttribute -> new PersonVo()
				//				  -> 기본생성자 + setter
			System.out.println(personVo);
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo);
	
			return "redirect:/list";
		}
		
	
		//3. 	파라미터 개별로 받을때 (Vo 에 묶지 않는 경우)
		/*	public String write(@RequestParam("name") String name
								, @RequestParam("hp") String hp
								, @RequestParam("company") String company) {
				System.out.println(name);
				System.out.println(hp);
				System.out.println(company);
				
				return "WEB-INF/views/write.jsp";
			}
		*/
	
	//DELETE
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("personId") int personId) {
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(personId);
		
		return "redirect:/list";
	}
			
			
	//UPDATEFORM
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String uForm(Model model, @RequestParam("personId") int personId) {
		
		//DB서 정보 가져오기
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.getPerson(personId);
		
		//model담기-dispatcherServlet-request.setAttribute
		model.addAttribute("phoneDao", phoneDao);
		
		return "/WEB-INF/views/updateFrom.jsp";
	}
	
	//UPDATE
	@RequestMapping(value ="/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		
		return "redirect:/list";
	}
	
	
	/* PathVariable 예제
		@RequestMapping(value="/board/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})	
			public String read(@PathVariable("no") int boardNo) {
			System.out.println("[Pathvariable.no]");
			
			return "";
		}
	*/
	
}
