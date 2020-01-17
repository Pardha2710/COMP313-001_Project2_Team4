using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TermProject_NearlyNew.Models;
using TermProject_NearlyNew.Models.DAO;
using TermProject_NearlyNew.Models.DTO;
using MongoDB.Bson;

namespace TermProject_NearlyNew.Controllers
{
    public class AdminController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Products()
        {
            return View();
        }

        public IActionResult Accounts()
        {
            return View();
        }

        public IActionResult AddProduct()
        {
            return View();
        }
        [HttpGet]
        public IActionResult ProductDetail()
        {
            return View();
        }

        [HttpPost]

        public IActionResult AddNewProduct(Product product)
        {
            ProductDAO.Create(product);   
            return View("Products",ProductDAO.GetProducts);
        }
        
        public IActionResult RemoveProduct(string? id)
        {
            ProductDAO.Remove(id);
            return View("Products",ProductDAO.GetProducts);
        }

        //public IActionResult ProcessProduct(string id, string detail,string remove)
        //{
        //    if (!string.IsNullOrEmpty(detail))
        //    {
        //        return ProductDetail(id);
        //    }
        //    else
        //    {
        //        return RemoveProduct(id);
        //    }
        //}
        
        //public IActionResult UpdateProduct(Product product,string id)
        //{
        //    product.ID = new ObjectId(id);
        //    ProductDAO.Update(product);
        //    return View("Products", ProductDAO.GetProducts);
        //}
    }
}