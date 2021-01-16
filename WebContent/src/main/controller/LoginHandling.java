package main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.entity.ORDERDETAIL;
import main.entity.ORDERR;
import main.entity.ORDER_NONPIZZA_DETAIL;

@Transactional
@Controller
public class LoginHandling {
	@Autowired
	SessionFactory factory;
	

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		session.removeAttribute("idcustomer");
		return "redirect:/home.htm";
	}
	@RequestMapping(value="login",method =RequestMethod.POST)
	public String login(ModelMap model, HttpServletRequest request, HttpSession session) {
		String email= request.getParameter("username");
		String pass= request.getParameter("userpass");
		if(!IsvalidEmail(email)) {
			model.addAttribute("Mes","Tài khoản không tồn tại");
			return "login";
		}
		if(!IsvalidPassword(email, pass)) {
			model.addAttribute("Mes","Mật khẩu không đúng");
			return "login";
		}else {
//			role =1 la usser
			if(GetUserRole(email)==1) {
				session.setAttribute("admin", 0);
				session.setAttribute("username", email);
				session.setAttribute("idcustomer", GetUserID(email) );
				session.setAttribute("numofcart", GetNumofCart(session.getAttribute("idcustomer")));
				session.setAttribute("totalprice", 0);
				return "redirect:/home.htm" ;
			}else if(GetUserRole(email)==0) {
//				ddaay la admin
				session.setAttribute("admin", 1);
				session.setAttribute("username", email);
				return "redirect:/admin.htm";
			}else {
//				daay la shhipe
				session.setAttribute("admin", 2);
				session.setAttribute("username", email);
				return "redirect:/admin.htm";
			}
		}
	}
	private boolean IsvalidEmail(String email) {
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
	private boolean IsvalidPassword(String email,String pass) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT Userpass FROM ACCOUNT WHERE Username= :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", email);
		List<String> rs = query.list();
		if (rs.get(0).equals(pass))
			return true;
		else
			return false;
	}
	private int GetUserRole(String email) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT Userrole FROM ACCOUNT WHERE Username= :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", email);
		List<Integer> rs = query.list();
		return rs.get(0);
	}
	private int GetUserID(String email) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT IDCustomer FROM CUSTOMER WHERE Email= :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		List<Integer> rs = query.list();
		return rs.get(0);
	}
	private int GetNumofCart(Object object) {
		Session session = factory.getCurrentSession();
		String hql="FROM ORDERR where IDCustomer=:idcustomer and Orderstatus=:orderstatus";
		Query query=session.createQuery(hql);
		query.setParameter("idcustomer",object);
		query.setParameter("orderstatus",1);
		List<ORDERR> orderrs= query.list();
		if(orderrs.size()==0)return 0;
		else {
			int quantity=0;
			session= factory.getCurrentSession();
			hql="FROM ORDER_NONPIZZA_DETAIL where IDOrder=:idorder";
			query=session.createQuery(hql);
			query.setParameter("idorder", orderrs.get(0).getIDOrder());
			List<ORDER_NONPIZZA_DETAIL> ordernonpizzadtrs= query.list();
			for(ORDER_NONPIZZA_DETAIL item:ordernonpizzadtrs) {
				quantity+=item.getQuantity();
			}
			session= factory.getCurrentSession();
			hql="FROM ORDERDETAIL where IDOrder=:idorder";
			query=session.createQuery(hql);
			query.setParameter("idorder", orderrs.get(0).getIDOrder());
			List<ORDERDETAIL> orderdtrs= query.list();
			for(ORDERDETAIL item:orderdtrs) {
				quantity+=item.getQuantity();
			}
			return quantity;
		}
	}
}
