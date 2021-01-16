package main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.entity.CUSTOMER;
import main.entity.EMPLOYEE;
import main.entity.NONPIZZA;
import main.entity.NONPIZZA_PRICE;
import main.entity.ORDERDETAIL;
import main.entity.ORDERR;
import main.entity.ORDER_NONPIZZA_DETAIL;
import main.entity.PIZZA;
import main.entity.PIZZACURT;
import main.entity.PIZZAOPTION;
import main.entity.PIZZAOPTION_PRICE;
import main.entity.PIZZASIZE;
import main.entity.PIZZASIZE_PRICE;
import main.entity.PIZZA_PRICE;

@Transactional
@Controller
public class Food_detailHandling {	
	
	@Autowired
	SessionFactory factory;
	Session session;
	String hql;
	Query query;
    org.hibernate.Transaction t;
	
	@RequestMapping(value="menu-{type}-{id}",  method = RequestMethod.POST)
	public String returnpizzawithdata(HttpSession sessionn,HttpServletRequest request,@PathVariable("id") String id,@PathVariable("type") String type) throws IllegalStateException, SystemException {
		if(sessionn.getAttribute("username")==null)
			return "login";
		else {
//			xem khách hàng có order chưa
			session = factory.getCurrentSession();
			hql="FROM ORDERR where IDCustomer=:idcustomer and Orderstatus=:orderstatus";
			query=session.createQuery(hql);
			query.setParameter("orderstatus", 1);
			query.setParameter("idcustomer", sessionn.getAttribute("idcustomer"));
			List<ORDERR> orderrs= query.list();
			
//			lấy thằng user ra
			session = factory.getCurrentSession();
			hql="FROM CUSTOMER where IDCustomer=:idcustomer";
			query=session.createQuery(hql);
			query.setParameter("idcustomer", sessionn.getAttribute("idcustomer"));
			List<CUSTOMER> customerrs= query.list();
			
//			lấy thằng employee ra tính năng này sau này phát triển thêm giờ để mặc định thằng nào cũng được
			session = factory.getCurrentSession();
			hql="FROM EMPLOYEE where IDEmployee=:idemployee";
			query=session.createQuery(hql);
			query.setParameter("idemployee", 1);
			List<EMPLOYEE> employeerrs= query.list();
			
			ORDERR neworder= new ORDERR();
			if(orderrs.size()==0) {
//				User đã hoàn tất giỏ hàng hoặc giỏ hàng trống --> tạo một cái order mới
				System.out.println("Gio hang trong");
				CUSTOMER ordercustomer= customerrs.get(0);
				EMPLOYEE orderemployee= employeerrs.get(0);
				neworder.setCUSTOMER(ordercustomer);
				neworder.setEMPLOYEE(orderemployee);
				neworder.setOrderTime(null);
				neworder.setPayment("");
				neworder.setDelievery("");
				neworder.setIDVoucher("");
				neworder.setOrderstatus(1);
				
//				add new order vào cơ sở dữ liệu
				session=factory.openSession();
				t= session.beginTransaction();
				try {
					session.save(neworder);
					t.commit();
				} catch (Exception e) {
					System.out.print("Không thể thêm order mới!");
					t.rollback();
				}finally {
					session.close();
				}
			}else {
				System.out.println("Userr đã có đồ trong giỏ hàng");
//				User có sản phẩm nào đó trong giỏ hàng
//				lấy cái order đó ra để add vào trong các chi tiết order
				neworder= orderrs.get(0);
			}
		
//			theem san pham vao chi tiet order (giỏ hàng)
			if(type.compareTo("pizza")==0) {
				System.out.println("Thêm pizza vào giỏ hàng");
				int sizers= Integer.parseInt(request.getParameter("size"));
				int curtrs=Integer.parseInt(request.getParameter("curt"));
				int optionrs=Integer.parseInt(request.getParameter("option"));
				
//				get pizza
				session = factory.getCurrentSession();
				hql="FROM PIZZA where IDPizza=:idpizza";
				query=session.createQuery(hql);
				query.setParameter("idpizza", Integer.parseInt(id));
				List<PIZZA> pizzars= query.list();
//				get pizza option
				session = factory.getCurrentSession();
				hql="FROM PIZZAOPTION where IDPizzaOption=:idpizzaoption";
				query=session.createQuery(hql);
				query.setParameter("idpizzaoption", optionrs);
				List<PIZZAOPTION> pizzaopptionrs= query.list();
//				get pizza size
				session = factory.getCurrentSession();
				hql="FROM PIZZASIZE where IDPizzaSize=:idpizzasize";
				query=session.createQuery(hql);
				query.setParameter("idpizzasize", sizers);
				List<PIZZASIZE> pizzasizers= query.list();
//				get pizza curt
				session = factory.getCurrentSession();
				hql="FROM PIZZACURT where IDPizzaCurt=:idpizzacurt";
				query=session.createQuery(hql);
				query.setParameter("idpizzacurt", curtrs);
				List<PIZZACURT> pizzacurtrs= query.list();
				
				ORDERDETAIL orderdetail=new ORDERDETAIL();
				orderdetail.setORDERR(neworder);
				orderdetail.setPIZZA(pizzars.get(0));
				orderdetail.setPIZZAOPTION(pizzaopptionrs.get(0));
				orderdetail.setPIZZASIZE(pizzasizers.get(0));
				orderdetail.setPIZZACURT(pizzacurtrs.get(0));
				
//				kiểm tra xem nếu cùng món hàng thì cộng số lượng và update vào database ko cùng thì thêm vào
				session = factory.getCurrentSession();
				hql="FROM ORDERDETAIL where IDPizza=:idpizza and IDPizzaSize=:idpizzasize and IDPizzaOption=:idpizzaoption and IDPizzaCurt=:idpizzacurt and IDOrder=:idorder";
				query=session.createQuery(hql);
				query.setParameter("idpizza", orderdetail.getPIZZA().getIDPizza());
				query.setParameter("idpizzasize",orderdetail.getPIZZASIZE().getIDPizzaSize());
				query.setParameter("idpizzaoption",orderdetail.getPIZZAOPTION().getIDPizzaOption() );
				query.setParameter("idpizzacurt", orderdetail.getPIZZACURT().getIDPizzaCurt());
				query.setParameter("idorder", orderdetail.getORDERR().getIDOrder());
				List<ORDERDETAIL> orderdetailrs= query.list();
				if(orderdetailrs.size()==0) {
					System.out.println("Pizza chưa có trong giỏ hàng nên sẽ add mới");
					orderdetail.setQuantity(1);
					System.out.println("So luong cu: 0");
					System.out.println("So luong moi: "+orderdetail.getQuantity());
					session= factory.openSession();
					t= session.beginTransaction();
					try {
						session.save(orderdetail);
						t.commit();
					}catch (Exception e) {
						System.out.println("Không thể cập nhật ");
					}finally {
						session.close();
					}
				}else {
					orderdetail.setQuantity(orderdetailrs.get(0).getQuantity()+1);
					session=factory.openSession();
					t= session.beginTransaction();
					try {
						session.update(orderdetail);
						t.commit();
					} catch (Exception e) {
						System.out.print("Không thể update thêm mới!");
						t.rollback();
					}finally {
						session.close();
					}
				}
				
			}else {
				System.out.println("Thêm nonpizza vào giỏ hàng");
				session = factory.getCurrentSession();
				hql="FROM NONPIZZA where IDNonPizza=:idnonpizza";
				query=session.createQuery(hql);
				query.setParameter("idnonpizza", Integer.parseInt(id));
				List<NONPIZZA> nonpizzars= query.list();
				ORDER_NONPIZZA_DETAIL order_nonpizza_detail=new ORDER_NONPIZZA_DETAIL();
				order_nonpizza_detail.setORDERR(neworder);
				order_nonpizza_detail.setNONPIZZA(nonpizzars.get(0));

				
//				kiểm tra xem nếu cùng món hàng thì cộng số lượng và update vào database ko cùng thì thêm vào
				session = factory.getCurrentSession();
				hql="FROM ORDER_NONPIZZA_DETAIL where IDNONPizza=:idnonpizza and IDOrder=:idorder";
				query=session.createQuery(hql);
				query.setParameter("idnonpizza", order_nonpizza_detail.getNONPIZZA().getIDNonPizza());
				query.setParameter("idorder", order_nonpizza_detail.getORDERR().getIDOrder());
				List<ORDER_NONPIZZA_DETAIL> ordernonpizzadetailrs= query.list();
				if(ordernonpizzadetailrs.size()==0) {
					System.out.println("Pizza chưa có trong giỏ hàng nên sẽ add mới");
					order_nonpizza_detail.setQuantity(1);
					session= factory.openSession();
					t= session.beginTransaction();
					try {
						session.save(order_nonpizza_detail);
						t.commit();
					}catch (Exception e) {
						System.out.println("Không thể cập nhật ");
					}finally {
						session.close();
					}
				}else {
					order_nonpizza_detail.setQuantity(ordernonpizzadetailrs.get(0).getQuantity()+1);
					session=factory.openSession();
					t= session.beginTransaction();
					try {
						session.update(order_nonpizza_detail);
						t.commit();
					} catch (Exception e) {
						System.out.print("Không thể update thêm mới!");
						t.rollback();
					}finally {
						session.close();
					}
				}
				
			}
			
			sessionn.setAttribute("numofcart", (int)sessionn.getAttribute("numofcart")+1);
			return "redirect:/menu.htm";
		}
	}
	@RequestMapping("menu-{type}-{id}")
	public String returnfood_detail(HttpServletRequest request, @PathVariable("id") String id,@PathVariable("type") String type) {
		session = factory.getCurrentSession();

		if(type.compareTo("pizza")==0) {
			session = factory.getCurrentSession();
			hql = "FROM PIZZA WHERE IDPizza= :id";
			query = session.createQuery(hql);
			query.setParameter("id", Integer.parseInt(id));
			List<PIZZA> rs= query.list();
			for(PIZZA item: rs) {
				request.setAttribute("product", item);
				PIZZA_PRICE max= item.getPIZZAPRICES().iterator().next(); 
				for(PIZZA_PRICE itemm: item.getPIZZAPRICES()) { 
					for(PIZZA_PRICE itemmm:item.getPIZZAPRICES()) {
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm; } }
				}
				request.setAttribute("productprice", max.getPrice());				
			}
			session = factory.getCurrentSession();
			hql = "FROM PIZZASIZE";
			query = session.createQuery(hql);
			List<PIZZASIZE> sizers= query.list();
			request.setAttribute("sizelist", sizers);
			for(PIZZASIZE item: sizers) {
				PIZZASIZE_PRICE max= item.getPIZZASIZEPRICES().iterator().next(); 
				for(PIZZASIZE_PRICE itemm: item.getPIZZASIZEPRICES()) { 
					for(PIZZASIZE_PRICE itemmm:item.getPIZZASIZEPRICES()) {
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm; } }
				}
				request.setAttribute("sizeprice"+item.getIDPizzaSize(), max.getPrice());				
			}
			
			
			hql = "FROM PIZZACURT";
			query = session.createQuery(hql);
			List<PIZZACURT> curtrs= query.list();
			request.setAttribute("curtlist", curtrs);
			
			hql = "FROM PIZZAOPTION";
			query = session.createQuery(hql);
			List<PIZZAOPTION> optionrs= query.list();
			request.setAttribute("optionlist", optionrs);
			for(PIZZAOPTION item: optionrs) {
				PIZZAOPTION_PRICE max= item.getPIZZAOPTIONPRICES().iterator().next(); 
				for(PIZZAOPTION_PRICE itemm: item.getPIZZAOPTIONPRICES()) { 
					for(PIZZAOPTION_PRICE itemmm:item.getPIZZAOPTIONPRICES()) {
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm; } }
				}
				request.setAttribute("optionprice"+item.getIDPizzaOption(), max.getPrice());				
			}
			
		
		}else {
			hql = "FROM NONPIZZA WHERE IDNonPizza= :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", Integer.parseInt(id));
			List<NONPIZZA> rs= query.list();
			for(NONPIZZA item: rs) {
				request.setAttribute("product", item);
				NONPIZZA_PRICE max= item.getNONPIZZAPRICES().iterator().next(); 
				for(NONPIZZA_PRICE itemm: item.getNONPIZZAPRICES()) { 
					for(NONPIZZA_PRICE itemmm:item.getNONPIZZAPRICES()) {
						if(itemm.getPriceDate().compareTo(itemmm.getPriceDate())>0) {
							max=itemm; } }
				}
				request.setAttribute("productprice", max.getPrice());					
			}
		}
		
		return "food_detail";
	}
	
	
}
