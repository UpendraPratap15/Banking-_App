package com.bank.constrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.entities.AccountInfo;
import com.bank.entities.User;
import com.bank.services.AccountService;
import com.bank.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("mybank")
public class HomeController 
{
	@Autowired private UserService userService;
	@Autowired private AccountService accountService;
	
	@RequestMapping("")
	public String getHomeView(HttpSession session)
	{
		if(session.getAttribute("name")==null)
		{
			return "login/user-login";
		}
		return "home";
	}
	/*@RequestMapping("login")
	public String getLoginView()
	{
		return "login/user-login";
	}*/
	@RequestMapping("registration")
	public String getRegistrationView()
	{
		return "login/user-registration";
	}
	@RequestMapping("registerme")
	public String registerUser(User user,Model model)
	{
		int accountno=userService.createAccount(user);
		model.addAttribute("accountno",accountno);
		model.addAttribute("name",user.getName());
		return "login/registration-success";
	}
	@RequestMapping("authenticate-user")
	public String authenticateUser(String userid,String password,Model model,HttpSession session)
	{
		User user=userService.getUser(userid);
		if(user==null)
		{
			model.addAttribute("uid",userid);
			model.addAttribute("msg","Entered userid does not exist");
			return "login/user-login";
		}
		String dpassword=user.getPassword();
		if(!password.equals(dpassword))
		{
			model.addAttribute("uid",userid);
			model.addAttribute("msg","Entered password is wrong");
			return "login/user-login";
		}
		AccountInfo account=accountService.getAccount(userid);
		session.setAttribute("accountno",account.getAccountno());
		session.setAttribute("name",user.getName());
		return "redirect:/mybank";
	}
	@RequestMapping("logout")
	public String getLogoutView(HttpSession session,Model model)
	{
		model.addAttribute("username",session.getAttribute("name"));
		session.invalidate();
		return "login/logout";
	}
}
