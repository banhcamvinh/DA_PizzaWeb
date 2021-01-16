package main.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.entity.CUSTOMER;
import main.entity.CUSTOMER_SHIP;
import main.entity.EMPLOYEE;
import main.entity.NONPIZZA_PRICE;
import main.entity.ORDERDETAIL;
import main.entity.ORDERR;
import main.entity.ORDER_NONPIZZA_DETAIL;
import main.entity.PIZZA;
import main.entity.PIZZAOPTION_PRICE;
import main.entity.PIZZASIZE_PRICE;
import main.entity.PIZZA_PRICE;

@Transactional
@Controller
public class CartHandling {
	@Autowired
	JavaMailSender mailer;
	@Autowired
	SessionFactory factory;
	Session session;
	String hql;
	Query query;
	org.hibernate.Transaction t;

	@RequestMapping("cart")
	public String returncart(HttpServletRequest request, HttpSession sessionn, ModelMap model) {
//		lấy danh sách các món ăn trong giỏ hàng mà khách đã order
		Session session = factory.getCurrentSession();
		String hql = "FROM ORDERR where IDCustomer=:idcustomer and Orderstatus=:orderstatus";
		Query query = session.createQuery(hql);
		query.setParameter("idcustomer", sessionn.getAttribute("idcustomer"));
		query.setParameter("orderstatus", 1);
		List<ORDERR> orderrs = query.list();

		if (orderrs.size() != 0) {
			float total = 0;
			session = factory.getCurrentSession();
			hql = "FROM ORDER_NONPIZZA_DETAIL where IDOrder=:idorder";
			query = session.createQuery(hql);
			query.setParameter("idorder", orderrs.get(0).getIDOrder());
			List<ORDER_NONPIZZA_DETAIL> ordernonpizzadtrs = query.list();
			request.setAttribute("ordernonpizzalist", ordernonpizzadtrs);
			for (ORDER_NONPIZZA_DETAIL item : ordernonpizzadtrs) {
				NONPIZZA_PRICE max = item.getNONPIZZA().getNONPIZZAPRICES().iterator().next();
				for (NONPIZZA_PRICE itemm : item.getNONPIZZA().getNONPIZZAPRICES()) {
					for (NONPIZZA_PRICE itemmm : item.getNONPIZZA().getNONPIZZAPRICES()) {
						if (itemm.getPriceDate().compareTo(itemmm.getPriceDate()) > 0) {
							max = itemm;
						}
					}
				}
				request.setAttribute("NONPIZZAPRICE" + item.getNONPIZZA().getIDNonPizza(), max.getPrice());
				total += max.getPrice() * item.getQuantity();
			}

			session = factory.getCurrentSession();
			hql = "FROM ORDERDETAIL where IDOrder=:idorder";
			query = session.createQuery(hql);
			query.setParameter("idorder", orderrs.get(0).getIDOrder());
			List<ORDERDETAIL> orderdtrs = query.list();
			request.setAttribute("orderpizzalist", orderdtrs);
			for (ORDERDETAIL item : orderdtrs) {

				PIZZA_PRICE maxpizza = item.getPIZZA().getPIZZAPRICES().iterator().next();
				for (PIZZA_PRICE itemm : item.getPIZZA().getPIZZAPRICES()) {
					for (PIZZA_PRICE itemmm : item.getPIZZA().getPIZZAPRICES()) {
						if (itemm.getPriceDate().compareTo(itemmm.getPriceDate()) > 0) {
							maxpizza = itemm;
						}
					}
				}
				request.setAttribute("PIZZAPRICE" + item.getPIZZA().getIDPizza(), maxpizza.getPrice());
//				System.out.println(maxpizza.getPrice());

				PIZZASIZE_PRICE maxsize = item.getPIZZASIZE().getPIZZASIZEPRICES().iterator().next();
				for (PIZZASIZE_PRICE itemm : item.getPIZZASIZE().getPIZZASIZEPRICES()) {
					for (PIZZASIZE_PRICE itemmm : item.getPIZZASIZE().getPIZZASIZEPRICES()) {
						if (itemm.getPriceDate().compareTo(itemmm.getPriceDate()) > 0) {
							maxsize = itemm;
						}
					}
				}
				request.setAttribute("PIZZASIZEPRICE" + item.getPIZZASIZE().getIDPizzaSize(), maxsize.getPrice());
//				System.out.println(maxsize.getPrice());

				PIZZAOPTION_PRICE maxoption = item.getPIZZAOPTION().getPIZZAOPTIONPRICES().iterator().next();
				for (PIZZAOPTION_PRICE itemm : item.getPIZZAOPTION().getPIZZAOPTIONPRICES()) {
					for (PIZZAOPTION_PRICE itemmm : item.getPIZZAOPTION().getPIZZAOPTIONPRICES()) {
						if (itemm.getPriceDate().compareTo(itemmm.getPriceDate()) > 0) {
							maxoption = itemm;
						}
					}
				}
				request.setAttribute("PIZZAOPTIONPRICE" + item.getPIZZAOPTION().getIDPizzaOption(),
						maxoption.getPrice());

				total += (maxpizza.getPrice() + maxsize.getPrice() + maxoption.getPrice()) * item.getQuantity();
			}
			sessionn.setAttribute("totalprice", total);
		}

//		lấy địa chỉ giao hàng
		model.addAttribute("shipmes", request.getParameter("shipmes"));
		session = factory.getCurrentSession();
		hql = "FROM CUSTOMER_SHIP where IDCustomer= :idcustomer";
		query = session.createQuery(hql);
		query.setParameter("idcustomer", sessionn.getAttribute("idcustomer"));
		List<CUSTOMER_SHIP> customer_ship = query.list();
		request.setAttribute("shiplist", customer_ship);
		return "cart";
	}

	@RequestMapping(value = "cart", params = "addadd")
	public String returnaddadd(HttpServletRequest request, HttpSession sessionn, ModelMap model) {
		String newadd = request.getParameter("shipadd");
		String newphone = request.getParameter("shipphone");
		CUSTOMER_SHIP customer_ship = new CUSTOMER_SHIP();
		customer_ship.setCustomer_Add(newadd);
		customer_ship.setCustomer_Phone(newphone);
		session = factory.getCurrentSession();
		hql = "FROM CUSTOMER where IDCustomer= :idcustomer";
		query = session.createQuery(hql);
		query.setParameter("idcustomer", sessionn.getAttribute("idcustomer"));
		List<CUSTOMER> customerlist = query.list();
		customer_ship.setCUSTOMER(customerlist.get(0));
		if (customer_ship.getCustomer_Add() == "" || customer_ship.getCustomer_Phone() == "") {
			model.addAttribute("shipmes", "Cần nhập đầy đủ thông tin");
			return "redirect:/cart.htm";
		} else {
			session = factory.openSession();
			t = session.beginTransaction();
			try {
				session.save(customer_ship);
				t.commit();
			} catch (Exception e) {
				model.addAttribute("shipmes", "Thêm mới địa chỉ thất bại");
				t.rollback();
			} finally {
				session.close();
			}
			return "redirect:/cart.htm";
		}
	}

	@RequestMapping(value = "cart", params = "btnpay")
	public String returnpay(HttpSession sessionn, HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
		Session session = factory.getCurrentSession();
		String hql = "FROM ORDERR where IDCustomer=:idcustomer and Orderstatus=:orderstatus";
		Query query = session.createQuery(hql);
		query.setParameter("idcustomer", sessionn.getAttribute("idcustomer"));
		query.setParameter("orderstatus", 1);
		List<ORDERR> orderrs = query.list();
		if (orderrs.size() != 0) {
			ORDERR payorder = orderrs.get(0); //
			payorder.setPayment(request.getParameter("payment"));
			payorder.setDelievery(request.getParameter("add"));
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String today = dateFormat.format(new Date());
			Date orderTime= new SimpleDateFormat("MM/dd/yyyy").parse(today);
			payorder.setOrderTime(orderTime);
			
//			chuyển về status bằng 2 để chờ xác nhận
			payorder.setOrderstatus(2);
			
			sessionn.setAttribute("totalprice", 0);
			sessionn.setAttribute("numofcart", 0);

//			gửi mail thông báo cho khác hàng
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			try {
				helper.setFrom("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setTo("banhcamvinh121@gmail.com");
				helper.setReplyTo("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setSubject("Order status");
				helper.setText("Đơn hàng của bạn đang được xử lý");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			mailer.send(mail);
			request.setAttribute("mes",
					"Đơn hàng của bạn đang được xử lý! Sau khi xác nhận thành công sẽ thông báo đến EMail của bạn");
			return "alert";
		}
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "pizzabtndel-{type}-{idorder}-{id}-{idsize}-{idoption}-{idcurt}")
	public String returndelpizza(HttpSession sessionn, @PathVariable("id") int id, @PathVariable("type") String type,
			@PathVariable("idorder") int idorder, @PathVariable("idsize") int idsize,
			@PathVariable("idoption") int idoption, @PathVariable("idcurt") int idcurt) {
		DeletePizza(sessionn, id, type, idorder, idsize, idoption, idcurt);
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "nonpizzabtndel-{type}-{idorder}-{id}")
	public String returndelnonpizza(HttpSession sessionn, @PathVariable("id") int id, @PathVariable("type") String type,
			@PathVariable("idorder") int idorder) {
		DeleteNonPizza(sessionn, id, type, idorder);
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "pizzabtn-{change}-{type}-{idorder}-{id}-{idsize}-{idoption}-{idcurt}")
	public String changepizza(HttpSession sessionn, @PathVariable("change") String change, @PathVariable("id") int id,
			@PathVariable("type") String type, @PathVariable("idorder") int idorder, @PathVariable("idsize") int idsize,
			@PathVariable("idoption") int idoption, @PathVariable("idcurt") int idcurt) {
		session = factory.openSession();
		t = session.beginTransaction();
		hql = "FROM ORDERDETAIL where IDPizza=:idpizza and IDPizzaSize=:idpizzasize and IDPizzaOption=:idpizzaoption and IDPizzaCurt=:idpizzacurt and IDOrder=:idorder";
		query = session.createQuery(hql);
		query.setParameter("idpizza", id);
		query.setParameter("idorder", idorder);
		query.setParameter("idpizzasize", idsize);
		query.setParameter("idpizzaoption", idoption);
		query.setParameter("idpizzacurt", idcurt);
		List<ORDERDETAIL> orderdtrs = query.list();
		if (orderdtrs.get(0).getQuantity() == 1 && change.equals("dec")) {
			DeletePizza(sessionn, id, type, idorder, idsize, idoption, idcurt);
		} else {
			try {
				if (change.equals("dec")) {
					orderdtrs.get(0).setQuantity(orderdtrs.get(0).getQuantity() - 1);
					sessionn.setAttribute("numofcart", (int) sessionn.getAttribute("numofcart") - 1);
				} else {
					orderdtrs.get(0).setQuantity(orderdtrs.get(0).getQuantity() + 1);
					sessionn.setAttribute("numofcart", (int) sessionn.getAttribute("numofcart") + 1);
				}
				t.commit();
			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		}
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "nonpizzabtn-{change}-{type}-{idorder}-{id}")
	public String changenonpizza(HttpSession sessionn, @PathVariable("change") String change,
			@PathVariable("id") int id, @PathVariable("type") String type, @PathVariable("idorder") int idorder) {
		session = factory.openSession();
		t = session.beginTransaction();
		hql = "FROM ORDER_NONPIZZA_DETAIL where IDNonPizza=:idnonpizza and IDOrder=:idorder";
		query = session.createQuery(hql);
		query.setParameter("idnonpizza", id);
		query.setParameter("idorder", idorder);
		List<ORDER_NONPIZZA_DETAIL> ordernonpizzadtrs = query.list();
		if (ordernonpizzadtrs.get(0).getQuantity() == 1 && change.equals("dec")) {
			DeleteNonPizza(sessionn, id, type, idorder);
		} else {
			try {
				if (change.equals("dec")) {
					ordernonpizzadtrs.get(0).setQuantity(ordernonpizzadtrs.get(0).getQuantity() - 1);
					sessionn.setAttribute("numofcart", (int) sessionn.getAttribute("numofcart") - 1);
				} else {
					ordernonpizzadtrs.get(0).setQuantity(ordernonpizzadtrs.get(0).getQuantity() + 1);
					sessionn.setAttribute("numofcart", (int) sessionn.getAttribute("numofcart") + 1);
				}
				t.commit();
			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		}
		return "redirect:/cart.htm";
	}

	private void DeletePizza(HttpSession sessionn, int id, String type, int idorder, int idsize, int idoption,
			int idcurt) {
		session = factory.openSession();
		t = session.beginTransaction();
		hql = "FROM ORDERDETAIL where IDPizza=:idpizza and IDPizzaSize=:idpizzasize and IDPizzaOption=:idpizzaoption and IDPizzaCurt=:idpizzacurt and IDOrder=:idorder";
		query = session.createQuery(hql);
		query.setParameter("idpizza", id);
		query.setParameter("idorder", idorder);
		query.setParameter("idpizzasize", idsize);
		query.setParameter("idpizzaoption", idoption);
		query.setParameter("idpizzacurt", idcurt);
		List<ORDERDETAIL> orderdtrs = query.list();
		try {
			int quantity = orderdtrs.get(0).getQuantity();
			session.delete(orderdtrs.get(0));
			sessionn.setAttribute("numofcart", (int) sessionn.getAttribute("numofcart") - quantity);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}

	private void DeleteNonPizza(HttpSession sessionn, int id, String type, int idorder) {
		session = factory.openSession();
		t = session.beginTransaction();
		hql = "FROM ORDER_NONPIZZA_DETAIL where IDNonPizza=:idnonpizza and IDOrder=:idorder";
		query = session.createQuery(hql);
		query.setParameter("idnonpizza", id);
		query.setParameter("idorder", idorder);
		List<ORDER_NONPIZZA_DETAIL> ordernonpizzadtrs = query.list();
		try {
			session.delete(ordernonpizzadtrs.get(0));
			sessionn.setAttribute("numofcart", (int) sessionn.getAttribute("numofcart") - 1);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}
}
