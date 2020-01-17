using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TermProject_NearlyNew.Models.DTO
{
    public class Carts
    {
        static public List<Product> Items = new List<Product>();

        public Carts()
        {
            //Items = new List<Product>();
        }
        static public void AddToCart(Product product)
        {
            Items.Add(product);
        }

        public void RemoveFromCart(Product product)
        {
            
        }

    }
}
