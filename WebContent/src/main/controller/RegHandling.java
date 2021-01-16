package main.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import main.entity.ACCOUNT;
import main.entity.CUSTOMER;
import main.entity.SENDNEW;

@Transactional
@Controller
public class RegHandling {

	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public String reg(ModelMap model, HttpServletRequest request) {
		String email = request.getParameter("email");
		if (IsExitEmail(email)) {
			model.addAttribute("Mes", "Email đã tồn tại vui lòng nhập email khác");
			return "reg";
		} else {
			String pass = request.getParameter("pass");
			String repass = request.getParameter("repass");
			if (!pass.equals(repass)) {
				model.addAttribute("Mes", "Mật khẩu chưa đúng");
				return "reg";
			} else {
				Session session = factory.openSession();
				Transaction t = session.beginTransaction();
				try {
//					tạo account và pass trước
					ACCOUNT newaccout=new ACCOUNT();
					newaccout.setUsername(email);
					newaccout.setUserpass(request.getParameter("pass"));
					newaccout.setUserrole(1);
					session.save(newaccout);
					
					CUSTOMER newcustomer=new CUSTOMER();
					newcustomer.setName(request.getParameter("name"));
					newcustomer.setEmail(email);
					newcustomer.setGender(Integer.parseInt(request.getParameter("gender")));
					newcustomer.setPhone(request.getParameter("phone"));
	
					Date birthday= new SimpleDateFormat("yyyy-dd-MM").parse(request.getParameter("birthday"));
					String dayrs=new SimpleDateFormat("MM/dd/yyyy").format(birthday);
					Date birthdaycv= new SimpleDateFormat("MM/dd/yyyy").parse(dayrs);
					newcustomer.setBirthday(birthdaycv);
					newcustomer.setAccount(newaccout);
					session.save(newcustomer);
				
					
					t.commit();
					model.addAttribute("Mes","Tạo tài khoản thành công");
				} catch (Exception e) {
					model.addAttribute("Mes", "Error lỗi hệ thống");
					t.rollback();
				} finally {
					session.close();
				}
			}
		}
		return "reg";
	}

	private boolean IsExitEmail(String email) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT Username FROM ACCOUNT WHERE Username= :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", email);
		List<String> rs = query.list();
		if (rs.size() == 0)
			return false;
		else
			return true;
	}
}
