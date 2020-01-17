using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using TermProject_NearlyNew.Models;
using TermProject_NearlyNew.Models.DAO;
using TermProject_NearlyNew.Models.DTO;
using System.Web;

namespace TermProject_NearlyNew.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        //public Carts Cart = new Carts();
        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            //Cart = new Carts();
            return View();
        }

        public IActionResult AboutUs()
        {
            return View();
        }

        public IActionResult Create()
        {
            return RedirectToAction("Create", "Registration");
        }

        public IActionResult Login()
        {
            return RedirectToAction("Login", "Registration");
        }

        //created controller
        public IActionResult Contact()
        {
            return View();
        }

        public IActionResult Shipping()
        {
            return View();
        }


        public IActionResult Returns()
        {
            return View();
        }

        public IActionResult Quality()
        {
            return View();
        }

        public IActionResult Checkout()
        {
            return View();
        }

        public IActionResult Confirmation()
        {
            return View();
        }
        public IActionResult All_Product()
        {
            return View();
        }
        public IActionResult Female_Product()
        {
            return View();
        }
        public IActionResult Male_Product()
        {
            return View();
        }
        public IActionResult MyCart()
        {
            return View();
        }
        public IActionResult Tracking()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });

        }
        //public IActionResult AddToCart(string id)
        //{
        //    if (RegistrationController.User == null)
        //    {
        //        //ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Record Inserted Successfully')", true);

        //        //Response.Write("<script>alert('Data inserted successfully')</script>");


        //    }
        //    //Cart.AddToCart(ProductDAO.GetProduct(id));
        //    else
        //    {
        //        Carts.AddToCart(ProductDAO.GetProduct(id));
        //    }
        //    return View("Index",ProductDAO.GetProducts);
        //}

    }
}
