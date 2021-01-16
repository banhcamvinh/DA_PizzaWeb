package main.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.entity.NONPIZZA;
import main.entity.NONPIZZA_PRICE;
import main.entity.PIZZA;
import main.entity.PIZZA_PRICE;

@Transactional
@Controller
public class MenuHandling {

	@Autowired
	SessionFactory factory;
	
	
	
	@RequestMapping("menu-pizza-page-{index}-{key}")
	public String returnpizza(HttpSession session, HttpServletRequest request,@PathVariable("index") int index,@PathVariable("key") String key) {
		load_menu(0,request,key,index);
		request.setAttribute("type", "menu-pizza");
		return "menu";
	}

	@RequestMapping("menu-nonpizza-page-{index}-{key}")
	public String returnnonpizza(HttpSession session,HttpServletRequest request,@PathVariable("index") int index,@PathVariable("key") String key) {
		load_menu(1,request,key,index);
		request.setAttribute("type", "menu-nonpizza");
		return "menu";
	}

	@RequestMapping("menu-drink-page-{index}-{key}")
	public String returndrink(HttpSession session,HttpServletRequest request,@PathVariable("index") int index,@PathVariable("key") String key) {
		load_menu(2,request,key,index);
		request.setAttribute("type", "menu-drink");
		return "menu";
	}

	@RequestMapping("menu-dessert-page-{index}-{key}")
	public String returndessert(HttpSession session,HttpServletRequest request,@PathVariable("index") int index,@PathVariable("key") String key) {
		load_menu(3,request,key,index);
		request.setAttribute("type", "menu-dessert");
		return "menu";
	}

	public void load_menu(Integer type,HttpServletRequest request,int index) {
		Session session = factory.getCurrentSession();
		String hql;
		Query query;
		if (type == 0) {
			hql = "FROM PIZZA";
			query = session.createQuery(hql);
			List<PIZZA> rs= query.list();
			request.setAttribute("numofproduct", rs.size());
			request.setAttribute("pageindex",String.valueOf(index) );
			request.setAttribute("pagemax", (rs.size()-1)/8+1);
			request.setAttribute("pagepre", index-1);
			request.setAttribute("pagenext", index+1);
			request.setAttribute("pagemin", 1);
			query.setFirstResult((index-1)*8);
			query.setMaxResults((index-1)*8+8);
			rs=query.list();
			request.setAttribute("MENULIST", rs);
			for(PIZZA item: rs) {
				PIZZA_PRICE max= item.getPIZZAPRICES().iterator().next();
				for(PIZZA_PRICE itemm: item.getPIZZAPRICES()) {		
					for(PIZZA_PRICE itemmm: item.getPIZZAPRICES()) {		
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm;
						}
					}
				}
				request.setAttribute("productprice"+item.getIDPizza(), max.getPrice());
			}
		} else {
			hql = "FROM NONPIZZA WHERE NonPizzaType= :type";
			query = session.createQuery(hql);
			query.setParameter("type",type);
			List<NONPIZZA> rs = query.list();	
			request.setAttribute("numofproduct", rs.size());
			request.setAttribute("pageindex",String.valueOf(index) );
			request.setAttribute("pagemax", rs.size()/8+1);
			request.setAttribute("pagepre", index-1);
			request.setAttribute("pagenext", index+1);
			request.setAttribute("pagemin", 1);
			query.setFirstResult((index-1)*8);
			query.setMaxResults((index-1)*8+8);
			rs = query.list();	
			request.setAttribute("MENULIST", rs);
			for(NONPIZZA item: rs) {
				NONPIZZA_PRICE max= item.getNONPIZZAPRICES().iterator().next();
				for(NONPIZZA_PRICE itemm: item.getNONPIZZAPRICES()) {		
					for(NONPIZZA_PRICE itemmm: item.getNONPIZZAPRICES()) {		
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm;
						}
					}
				}
				request.setAttribute("productprice"+item.getIDNonPizza(), max.getPrice());
			}
		}
	}
	
	public void load_menu(Integer type,HttpServletRequest request,String key,int index) {
		Session session = factory.getCurrentSession();
		String hql;
		Query query;
		if (type == 0) {
			hql = "FROM PIZZA where Name like '%"+key+"%'";
			query = session.createQuery(hql);
			List<PIZZA> rs= query.list();
			request.setAttribute("numofproduct", rs.size());
			request.setAttribute("pageindex",String.valueOf(index) );
			request.setAttribute("pagemax", (rs.size()+1)/8+1);
			request.setAttribute("pagepre", index-1);
			request.setAttribute("pagenext", index+1);
			request.setAttribute("pagemin", 1);
			query = session.createQuery(hql);
			query.setFirstResult((index-1)*8);
			query.setMaxResults(8);
			rs=query.list();
			System.out.println("size"+rs.size());
			request.setAttribute("MENULIST", rs);
			for(PIZZA item: rs) {
				PIZZA_PRICE max= item.getPIZZAPRICES().iterator().next();
				for(PIZZA_PRICE itemm: item.getPIZZAPRICES()) {		
					for(PIZZA_PRICE itemmm: item.getPIZZAPRICES()) {		
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm;
						}
					}
				}
				request.setAttribute("productprice"+item.getIDPizza(), max.getPrice());
			}
		} else {
			hql = "FROM NONPIZZA WHERE NonPizzaType= :type and Name like '%"+key+"%'";
			query = session.createQuery(hql);
			query.setParameter("type",type);
			List<NONPIZZA> rs = query.list();	
			request.setAttribute("numofproduct", rs.size());
			request.setAttribute("pageindex",String.valueOf(index) );
			request.setAttribute("pagemax", rs.size()/8+1);
			request.setAttribute("pagepre", index-1);
			request.setAttribute("pagenext", index+1);
			request.setAttribute("pagemin", 1);
			query.setFirstResult((index-1)*8);
			query.setMaxResults(8);
			rs = query.list();	
			request.setAttribute("MENULIST", rs);
			for(NONPIZZA item: rs) {
				NONPIZZA_PRICE max= item.getNONPIZZAPRICES().iterator().next();
				for(NONPIZZA_PRICE itemm: item.getNONPIZZAPRICES()) {		
					for(NONPIZZA_PRICE itemmm: item.getNONPIZZAPRICES()) {		
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm;
						}
					}
				}
				request.setAttribute("productprice"+item.getIDNonPizza(), max.getPrice());
			}
		}
	}
	
	@RequestMapping(value="search-{type}")
	public String search(ModelMap model, HttpServletRequest request, HttpSession session,@PathVariable("type") String type) {
		String searchkey= request.getParameter("search");
		session.setAttribute("key", searchkey);
		return "redirect:/"+type+"-page-1-"+searchkey+".htm";
	}
}
