using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;
using TermProject_NearlyNew.Models.DTO;

namespace TermProject_NearlyNew.Models.DAO
{
    public class ProductDAO
    {
        static IMongoCollection<Product> collections = DataProvider.db.GetCollection<Product>("products");

        public static void Create(Product product)
        {
            collections.InsertOneAsync(product);
        }

        public static void Remove(string id)
        {
            var builder = Builders<Product>.Filter;
            var filter = builder.And(builder.Eq("_id",ObjectId.Parse(id)));

            var deleteOne = collections.DeleteOneAsync(filter);
            
        }

        public static Product GetProduct(string id)
        {
            var builder = Builders<Product>.Filter;
            var filter = builder.Eq("_id", ObjectId.Parse(id));
            try
            {
                var product = collections.FindSync(filter).First<Product>();
                return product;
            }
            catch
            {
                return new Product();
            }
        }

        public static void Update(Product product)
        {
            var builder = Builders<Product>.Filter;
            var filter = builder.Eq("_id", product.ID);
            collections.ReplaceOne(filter, product);

        }
        public static IEnumerable<Product> GetProducts => collections.AsQueryable();


        

    }
}
