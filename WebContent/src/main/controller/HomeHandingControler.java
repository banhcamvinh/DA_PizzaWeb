package main.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.entity.SENDNEW;

@Transactional
@Controller
public class HomeHandingControler {
	
	@Autowired
	SessionFactory factory;
	
//	cái này test để lấy dữ liệu
	@RequestMapping("test")
	public String test(ModelMap model) {
		Session session= factory.getCurrentSession();
		String hql="FROM SENDNEW";
		Query query= session.createQuery(hql);
		return "home";
	}
	
	
	@RequestMapping(value="home",method= RequestMethod.POST)
	public String sendnew(ModelMap model,HttpServletRequest request) {
		Session session= factory.openSession();
		Transaction t= session.beginTransaction();
		try {
			String email=request.getParameter("email");
			String hql="FROM SENDNEW WHERE Email = :email";
			Query query= session.createQuery(hql);
			query.setParameter("email", email);
			List<String> rs= query.list();
			if(rs.size()==0) {
				SENDNEW sn=new SENDNEW();
				sn.setEmail(email);
				session.save(sn);
				model.addAttribute("Mes","Đăng kí thành công");
			}
			else model.addAttribute("Mes","Email của bạn đã đăng kí");
			t.commit();
		}catch(Exception e) {
			model.addAttribute("Mes","Error");
			System.out.print(e.getStackTrace());
			t.rollback();
		}finally {
			session.close();
		}
		return "home";
	}
}
