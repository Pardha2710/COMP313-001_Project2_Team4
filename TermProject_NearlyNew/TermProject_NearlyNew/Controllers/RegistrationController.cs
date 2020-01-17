using System;
using System.Net;
using System.Net.Mail;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TermProject_NearlyNew.Models;
using TermProject_NearlyNew.Models.DAO;
using TermProject_NearlyNew.Models.DTO;


namespace TermProject_NearlyNew.Controllers
{
    public class RegistrationController : Controller
    {


        public static string LoginCode;

        public static Account User { get; set; }

        public static void SendValidationCode(string Code)
        {
            try
            {
                SmtpClient SmtpServer = new SmtpClient();
                SmtpServer.Host = "smtp.gmail.com";
                SmtpServer.Port = 587;
                SmtpServer.Credentials = new NetworkCredential("nearlynewassist@gmail.com", "Password0011");
                SmtpServer.EnableSsl = true;
                SmtpServer.DeliveryMethod = SmtpDeliveryMethod.Network;
                SmtpServer.Timeout = 20000;

                MailMessage mail = new MailMessage();
                mail.From = new MailAddress("nearlynewassist@gmail.com");
                mail.To.Add(User.Email);
                mail.Subject = "2-step Authentication";
                mail.Body = "Your validation code: " + Code;

                SmtpServer.Send(mail);
                Console.WriteLine("Successed");
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }
        public IActionResult Create()
        {
            return View();
        }

        public IActionResult Login()
        {
            return View();
        }

        public IActionResult AuthenticateOne(Account account)
        {
            if (AccountDAO.GetAccount(account) != null)
            {
                User = AccountDAO.GetAccount(account);
                if (User.Role == "Admin")
                {
                    return RedirectToAction("Index", "Admin");
                }
                else
                {
                    LoginCode = AccountDAO.RandomCode();
                    SendValidationCode(LoginCode);
                    return View("SecondAuthentication");
                }
            }
            else
            {
                return View("Login");
            }
        }

        public IActionResult AuthenticateTwo(string code)
        {
            if (code == LoginCode)
            {
                return View("Manage", User);
            }
            else
            {
                User = null;
                LoginCode = null;
                return View("Login");
            }
        }

        public IActionResult Manage()
        {
            return View();
        }
        [HttpPost]
        public IActionResult CreateNewAccount(Account account)
        {
            AccountDAO.Create(account);
            return RedirectToAction("Index","Home",AccountDAO.GetAccounts);
        }
    }
}