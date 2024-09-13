package com.bank.constrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.entities.AccountInfo;
import com.bank.entities.Transaction;
import com.bank.services.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank/activity")
public class AccountController 
{
	@Autowired private AccountService accountService;
	
	@RequestMapping("view-balance")
	public String getBalanceAmount(HttpSession session,Model model)
	{
		int amount=getToalAmount(session);
		model.addAttribute("amount",amount);
		return "transaction/show-balance";
	}
	@RequestMapping("deposit-form")
	public String getDepositFormView()
	{
		return "transaction/deposit/deposit-money";
	}
	@RequestMapping("deposit-money")
	public String depositMoney(int amount,Model model,HttpSession session)
	{
		int accountno=(Integer)session.getAttribute("accountno");
		accountService.updateAmount(amount,accountno);
		Transaction transaction=accountService.updateWithdrawAmount(amount,accountno);
		model.addAttribute("transaction",transaction);
		return "transaction/deposit/deposit-success";
	}
	@RequestMapping("transaction-summary")
	public String getTransactionList(Model model,HttpSession session)
	{
		int accountno=(Integer)session.getAttribute("accountno");
		List<Transaction> tlist=accountService.getTransactionList(accountno);
		model.addAttribute("tlist", tlist);
		return "transaction/deposit/summary";
	}
	@RequestMapping("withdraw-form")
	public String getWithdrawFormView()
	{
		return "transaction/withdraw/withdraw-money";
	}
	@RequestMapping("withdraw-money")
	public String withdrawMoney(int amount,Model model,HttpSession session)
	{
		int accountno=(Integer)session.getAttribute("accountno");
		int tamount=getToalAmount(session);
		if(amount>tamount)
		{
			model.addAttribute("msg","You do not have sufficient amount");
			model.addAttribute("amount",amount);
			return "transaction/withdraw/withdraw-money";
		}
		Transaction transaction=accountService.updateWithdrawAmount(amount,accountno);
		model.addAttribute("transaction",transaction);
		return "transaction/withdraw/withdraw-success";
	}
	private int getToalAmount(HttpSession session)
	{
		int an=(Integer)session.getAttribute("accountno");
		AccountInfo account=accountService.getAccount(an);
		int amount=account.getAmount();
		return amount;
	}
	@RequestMapping("accountno-form")
	public String getAccountNoFormView()
	{
		return "transaction/transfer/account-number";
	}
	@RequestMapping("verify-accountno")
	public String veryfyAccountNo(Model model,int accountno,HttpSession session)
	{
		int an=(Integer)session.getAttribute("accountno");
		if(accountno==an)
		{
			model.addAttribute("msg","Entered account number is your own");
			model.addAttribute("an",accountno);
			return "transaction/transfer/account-number";
		}
		return "transaction/transfer/account-number";
	}
}
