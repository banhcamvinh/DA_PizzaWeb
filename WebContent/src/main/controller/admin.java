package main.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import main.entity.ACCOUNT;
import main.entity.CUSTOMER_SHIP;
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
public class admin {
	@Autowired
	JavaMailSender mailer;
	@Autowired
	SessionFactory factory;
	Session session;
	String hql;
	Query query;
	Transaction t;

	@RequestMapping("admin")
	public String rtadminmenu() {
		return "admin";
	}

	@RequestMapping(value = "adminmenu", params = "pizza")
	public String rtproduct() {
		return "redirect:/adminpizzaview.htm";
	}
	
	@RequestMapping(value = "adminmenu", params = "account")
	public String rtaccount() {
		return "redirect:/adminaccountview.htm";
	}

	@RequestMapping(value = "adminmenu", params = "order")
	public String rtorder() {
		return "redirect:/adminorderview.htm";
	}
	
	@RequestMapping("adminaccountview")
	public String rtadminaccountview(ModelMap model, HttpServletRequest request) {
		session = factory.getCurrentSession();
		hql = "FROM ACCOUNT";
		query = session.createQuery(hql);
		List<ACCOUNT> accountlistrs = query.list();
		model.addAttribute("accountlist", accountlistrs);
		return "adminaccountview";
	}
	
	@RequestMapping("adminorderview")
	public String rtadminorderview(ModelMap model, HttpServletRequest request,HttpSession sessionn) {
		session = factory.getCurrentSession();
		hql = "FROM ORDERR";
		if(sessionn.getAttribute("admin").toString().equals("1")) {
			hql = "FROM ORDERR";
		}else if(sessionn.getAttribute("admin").toString().equals("2")) {
			hql = "FROM ORDERR where Orderstatus= '4' or Orderstatus='3'";
		}
		query = session.createQuery(hql);
		List<ORDERR> orderlistrs = query.list();
		model.addAttribute("orderlist", orderlistrs);
		return "adminorderview";
	}
	
	@RequestMapping("adminpizzaview")
	public String rtadminproductview(ModelMap model, HttpServletRequest request) {
		session = factory.getCurrentSession();
		hql = "FROM PIZZA";
		query = session.createQuery(hql);
		List<PIZZA> pizzalistrs = query.list();
		model.addAttribute("pizzalist", pizzalistrs);
		for (PIZZA item : pizzalistrs) {
			if (item.getPIZZAPRICES().size() == 0) {
				request.setAttribute("productprice" + item.getIDPizza(), 0);
			} else {
				PIZZA_PRICE max = item.getPIZZAPRICES().iterator().next();
				for (PIZZA_PRICE itemm : item.getPIZZAPRICES()) {
					for (PIZZA_PRICE itemmm : item.getPIZZAPRICES()) {
						if (itemm.getPriceDate().compareTo(itemmm.getPriceDate()) > 0) {
							max = itemm;
						}
					}
				}
				request.setAttribute("productprice" + item.getIDPizza(), max.getPrice());
			}
		}
		return "adminpizzaview";
	}

	@RequestMapping(value = "/delete-pizza{id}", method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") int id) {
		session = factory.openSession();
		hql = "FROM PIZZA where IDPizza= :id";
		query = session.createQuery(hql);
		query.setParameter("id", id);
		List<PIZZA> pizzars = query.list();
		t = session.beginTransaction();
		try {
			session.delete(pizzars.get(0));
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		return "redirect:/adminpizzaview.htm";
	}
	
	@RequestMapping(value = "/change-pizza{id}", method = RequestMethod.GET)
	public String rtadd(ModelMap model,HttpServletRequest request, @PathVariable("id") int id) {
		if (String.valueOf(id).equals("0")) {
			model.addAttribute("update", 0);
			model.addAttribute("Pizza", new PIZZA());
			return "adminpizzaform";
		} else {
			model.addAttribute("update", 1);
			session = factory.openSession();
			hql = "FROM PIZZA where IDPizza= :id";
			query = session.createQuery(hql);
			query.setParameter("id", id);
			List<PIZZA> pizzars = query.list();
			model.addAttribute("Pizza", pizzars.get(0));
			model.addAttribute("id", id);
			
			hql = "FROM PIZZA where IDPizza= :id";
			query = session.createQuery(hql);
			query.setParameter("id", id);
			List<PIZZA> pizzalistrs = query.list();
			PIZZA_PRICE max = pizzalistrs.get(0).getPIZZAPRICES().iterator().next();
			for(PIZZA_PRICE item: pizzalistrs.get(0).getPIZZAPRICES()) {
				for(PIZZA_PRICE itemm: pizzalistrs.get(0).getPIZZAPRICES()) {
					if (item.getPriceDate().compareTo(itemm.getPriceDate()) > 0) {
						max = item;
					}
				}
			}
			request.setAttribute("pizzaprice", max.getPrice());
			return "adminpizzaform";
		}
	}
	
	@RequestMapping(value = "/change-account{id}", method = RequestMethod.GET)
	public String rtaccadd(ModelMap model,HttpServletRequest request, @PathVariable("id") int id) {
//		if (String.valueOf(id).equals("0")) {
//			model.addAttribute("update", 0);
//			model.addAttribute("Account", new ACCOUNT());
//			return "adminaccountform";
//		} else {
			model.addAttribute("update", 1);
			session = factory.openSession();
			hql = "FROM ACCOUNT where IDAccount= :id";
			query = session.createQuery(hql);
			query.setParameter("id", id);
			List<ACCOUNT> accountrs = query.list();
			model.addAttribute("Account", accountrs.get(0));
			model.addAttribute("id", id);		
			return "adminaccountform";
	}
	
	@RequestMapping(value = "change-pizza{id}", params = "refresh", method = RequestMethod.POST)
	public String refresh(ModelMap model,HttpServletRequest request, @ModelAttribute("Pizza") PIZZA pizza, @PathVariable("id") String id) {	
		PIZZA newpizza= new PIZZA();	
		if(id.equals("0")== false) {
//			updating...
			model.addAttribute("update",1);
			newpizza.setIDPizza(Integer.parseInt(id));
		}else {
//			adding...
			model.addAttribute("update",0);
		}
		model.addAttribute("Pizza", newpizza);
		return "adminpizzaform";
	}
	
	@RequestMapping(value = "change-account{id}", params = "refresh", method = RequestMethod.POST)
	public String refreshacc(ModelMap model,HttpServletRequest request, @ModelAttribute("Account") PIZZA pizza, @PathVariable("id") String id) {	
		ACCOUNT newacc= new ACCOUNT();
		if(id.equals("0")== false) {
//			updating...
			model.addAttribute("update",1);
			newacc.setIDAccount(Integer.parseInt(id));
			session = factory.openSession();
			hql = "FROM ACCOUNT where IDAccount= :id";
			query = session.createQuery(hql);
			query.setParameter("id", Integer.parseInt(id));
			List<ACCOUNT> accountrs = query.list();
			newacc.setUsername(accountrs.get(0).getUsername());
		}else {
//			adding...
			model.addAttribute("update",0);
		}
		model.addAttribute("Account", newacc);
		return "adminaccountform";
	}

	@RequestMapping(value = "change-pizza{id}", params = "exit", method = RequestMethod.POST)
	public String exit(ModelMap model, @ModelAttribute("Pizza") PIZZA pizza) {
		return "redirect:/adminpizzaview.htm";
	}
	
	@RequestMapping(value = "change-account{id}", params = "exit", method = RequestMethod.POST)
	public String exitacc(ModelMap model, @ModelAttribute("Account") PIZZA pizza) {
		return "redirect:/adminaccountview.htm";
	}
	
	@RequestMapping(value = "/change-pizza{id}", params = "update", method = RequestMethod.POST)
	public String update(ModelMap model,HttpServletRequest request, @ModelAttribute("Pizza") PIZZA pizza, @PathVariable String id,BindingResult errors,@PathVariable("id") String idurl,@RequestParam("image")MultipartFile image) {		
		
//		if(image.isEmpty()) {
//			model.addAttribute("mess", "Bạn chưa upload ảnh!");
//		}
////		tạo một đường dẫ mới
//		String path= "C:/Users/ROG/eclipse-workspace/PizzaProject/WebContent/resources/img/"+image.getOriginalFilename();
//		try {
//			image.transferTo(new File(path));
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "adminpizzaform";
		
		
		String price= request.getParameter("pizzaprice");
		request.setAttribute("pizzaprice", price);
		if(pizza.getName().trim().length() == 0){
			errors.rejectValue("Name", "pizza",  "Vui lòng nhập tên sản phẩm!");
		}
		if(pizza.getDescript().trim().length() == 0){
			errors.rejectValue("Descript", "pizza",  "Vui lòng nhập mô tả!");
		}
		if(errors.hasErrors()){
			model.addAttribute("messerr", "Vui lòng sửa lỗi!");
		}
		else{
			model.addAttribute("messerr", "Chúc mừng, bạn đã nhập đúng !");
			PIZZA_PRICE newprice=new PIZZA_PRICE();
			newprice.setIDPizza(pizza.getIDPizza());
			newprice.setPIZZA(pizza);
			newprice.setPrice(Float.valueOf(price));
		    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    String today= dateFormat.format(new Date());
		   
		    
		    if(idurl.equals("0")== false) {
		    	model.addAttribute("update", 1);
		    	System.out.println("Updating......");
		    	
		    	 try {			
						session = factory.openSession();
						t = session.beginTransaction();
								    			    
						String hql = "UPDATE PIZZA SET Name=:n,Descript=:d,Img=:i where IDPizza=:id";
						Query query = session.createQuery(hql);
						query.setParameter("n", pizza.getName());
						query.setParameter("d", pizza.getDescript());
						if(image.isEmpty()) {
							query.setParameter("i", pizza.getImg());
						}else {
							String path= "C:/Users/ROG/eclipse-workspace/PizzaProject/WebContent/resources/img/"+image.getOriginalFilename();
				    		try {
				    			image.transferTo(new File(path));
				    		} catch (IllegalStateException e) {
				    			e.printStackTrace();
				    		} catch (IOException e) {
				    			e.printStackTrace();
				    		}
				    		query.setParameter("i", "resources/img/"+image.getOriginalFilename());
						}
						query.setParameter("id", pizza.getIDPizza());			
						query.executeUpdate();
						
						hql = "INSERT INTO PIZZA_PRICE (IDPizza,PriceDate,Price) VALUES('"+pizza.getIDPizza()+"','"+ today.toString() +"','"+Float.valueOf(price)+"')";
						query = session.createSQLQuery(hql);
						query.executeUpdate();
						t.commit();
						model.addAttribute("mess","Cập nhật thành công");
					}catch(Exception e) {
						System.out.println(e.getMessage());
						model.addAttribute("mess", "thất bại! Lỗi "+e.getCause());
						t.rollback();
					}finally {
						session.close();		
					}
		    }else {
		    	model.addAttribute("update", 0);
		    	System.out.println("Adding......");
		    	
		    	if(image.isEmpty()) {
		    		model.addAttribute("mess", "Bạn chưa upload hình ảnh!");
		    	}else {
		    		String path= "C:/Users/ROG/eclipse-workspace/PizzaProject/WebContent/resources/img/"+image.getOriginalFilename();
		    		try {
		    			image.transferTo(new File(path));
		    		} catch (IllegalStateException e) {
		    			e.printStackTrace();
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
		    		try {
//			    		thêm bánh
				    	session = factory.openSession();
						t = session.beginTransaction();							    			    			
						PIZZA p=new PIZZA();
						p.setName(pizza.getName());
						p.setDescript(pizza.getDescript());
						p.setImg("resources/img/"+image.getOriginalFilename());
						session.save(p);
						System.out.println(p.getIDPizza());
//				    	thêm giá bánh					
						hql = "INSERT INTO PIZZA_PRICE (IDPizza,PriceDate,Price) VALUES('"+p.getIDPizza()+"','"+ today.toString() +"','"+Float.valueOf(price)+"')";
						query = session.createSQLQuery(hql);
						query.executeUpdate();
						t.commit();
						model.addAttribute("mess","Cập nhật thành công");
			    	}catch (Exception e) {
			    		System.out.println(e.getMessage());
						model.addAttribute("mess", "thất bại !");
						t.rollback();
					}finally {
						session.close();
					}    	
		    	}
		    }
		}		  
		if(idurl.equals("0")== false)
			model.addAttribute("update",1);
		else model.addAttribute("update",0);
		return "adminpizzaform";
	}
	
	@RequestMapping(value = "/change-account{id}", params = "update", method = RequestMethod.POST)
	public String updateacc(ModelMap model,HttpServletRequest request, @ModelAttribute("Account") ACCOUNT acc, @PathVariable String id,BindingResult errors,@PathVariable("id") String idurl) {		
		if(acc.getUserrole()==null){
			errors.rejectValue("Userrole", "acc",  "Vui lòng nhập quyền truy cập!");
		}else if(acc.getUserrole()!= 0 && acc.getUserrole() !=1) {
			errors.rejectValue("Userrole", "acc",  "Chỉ có 2 quyền truy cập: 0:admin -1:user!");
		}
		if(errors.hasErrors()){
			model.addAttribute("messerr", "Vui lòng sửa lỗi!");
		}
		else{
			model.addAttribute("messerr", "Chúc mừng, bạn đã nhập đúng !");
		    if(idurl.equals("0")== false) {
		    	model.addAttribute("update", 1);
		    	System.out.println("Updating......");
		    	 try {			
						session = factory.openSession();
						t = session.beginTransaction();
								    			    
						String hql = "UPDATE ACCOUNT SET Userrole=:ur where IDAccount=:id";
						Query query = session.createQuery(hql);
						query.setParameter("ur", acc.getUserrole());
						query.setParameter("id", acc.getIDAccount());		
						query.executeUpdate();
						t.commit();
						model.addAttribute("mess","Cập nhật thành công");
					}catch(Exception e) {
						System.out.println(e.getMessage());
						model.addAttribute("mess", "thất bại !");
						t.rollback();
					}finally {
						session.close();		
					}
		    }
		}		
		return "adminaccountform";
	}
	
	@RequestMapping(value = "/accept-order{id}", method = RequestMethod.GET)
	public String acceptorder(ModelMap model,HttpServletRequest request,@PathVariable("id") String idurl) {
		int status=0;
		Session session = factory.getCurrentSession();
		String hql = "FROM ORDERR where IDOrder=:ido";
		Query query = session.createQuery(hql);
		query.setParameter("ido", Integer.parseInt(idurl));
		List<ORDERR> orderrs = query.list();
		if(orderrs.get(0).getPayment().equals("shipcod") ) {
			status=4;
		}else if(orderrs.get(0).getPayment().equals("card") ) {
			status=3;
		}
		
		try {			
			session = factory.openSession();
			t = session.beginTransaction();
					    			    
			hql = "UPDATE ORDERR SET Orderstatus=:status  where IDOrder=:id";
			query = session.createQuery(hql);
			query.setParameter("status",status);
			query.setParameter("id", Integer.parseInt(idurl));		
			query.executeUpdate();
			t.commit();
			request.setAttribute("mes", "Xác nhận đơn hàng thành công!");
//			gửi mail thông báo cho khác hàng
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			try {
				helper.setFrom("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setTo("banhcamvinh121@gmail.com");
				helper.setReplyTo("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setSubject("Order Status");
				helper.setText("Đơn hàng của bạn đã được xác nhận!");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			mailer.send(mail);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("mes", "Lỗi!! Không thể xác nhận");
			t.rollback();
		}finally {
			session.close();		
		}
		return "alert";
	}
	
	@RequestMapping(value = "/view-order{id}", method = RequestMethod.GET)
	public String vieworder(ModelMap model,HttpServletRequest request,HttpSession sessionn,@PathVariable("id") String idurl) {	
		Session session = factory.getCurrentSession();
		String hql = "FROM ORDERR where IDOrder=:ido";
		Query query = session.createQuery(hql);
		query.setParameter("ido", Integer.parseInt(idurl));
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
		return "adminorderdetailview";
	}
	
	@RequestMapping(value = "/cancel-order{id}", method = RequestMethod.GET)
	public String cancelorder(ModelMap model,HttpServletRequest request,@PathVariable("id") String idurl) {	
		try {			
			session = factory.openSession();
			t = session.beginTransaction();
					    			    
			String hql = "UPDATE ORDERR SET Orderstatus=:status  where IDOrder=:id";
			Query query = session.createQuery(hql);
			query.setParameter("status",5);
			query.setParameter("id", Integer.parseInt(idurl));		
			query.executeUpdate();
			t.commit();
			request.setAttribute("mes", "Hủy đơn thành công");
//			gửi mail thông báo cho khác hàng

			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			try {
				helper.setFrom("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setTo("banhcamvinh121@gmail.com");
				helper.setReplyTo("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setSubject("Order Status");
				helper.setText("Đơn hàng của bạn đã bị hủy! Hãy thử lại hoặc liên lạc với chúng tôi!");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			mailer.send(mail);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("mes", "Lỗi!! Không thể hủy đơn");
			t.rollback();
		}finally {
			session.close();		
		}
		return "alert";
	}
	
	@RequestMapping(value = "/finish-order{id}", method = RequestMethod.GET)
	public String finishorder(ModelMap model,HttpServletRequest request,@PathVariable("id") String idurl) {	
		try {			
			session = factory.openSession();
			t = session.beginTransaction();
					    			    
			String hql = "UPDATE ORDERR SET Orderstatus=:status  where IDOrder=:id";
			Query query = session.createQuery(hql);
			query.setParameter("status",0);
			query.setParameter("id", Integer.parseInt(idurl));		
			query.executeUpdate();
			t.commit();
			request.setAttribute("mes", "Cập nhật thành công! đã gửi thông báo đến khách hàng");
//			gửi mail thông báo cho khác hàng

			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			try {
				helper.setFrom("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setTo("banhcamvinh121@gmail.com");
				helper.setReplyTo("DeliciousPizzaADMIN", "DeliciousPizzaADMIN");
				helper.setSubject("Order Status");
				helper.setText("Đơn hàng của bạn đã giao hàng thành công");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			mailer.send(mail);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("mes", "Lỗi!! Không thể xác nhận trạng thái");
			t.rollback();
		}finally {
			session.close();		
		}
		return "alert";
	}
}
