using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;
using TermProject_NearlyNew.Models.DTO;

namespace TermProject_NearlyNew.Models.DAO
{
    public class AccountDAO
    {
        static IMongoCollection<Account> collections = DataProvider.db.GetCollection<Account>("accounts");
        public static void Create(Account account)
        {
            collections.InsertOneAsync(account);
        }
        public static Account GetAccount(Account account)
        {
            var builder = Builders<Account>.Filter;
            var filter = builder.And(builder.Eq("email", account.Email), builder.Eq("password",account.Password));
            //var acc = new Account(collections.FindSync(filter).FirstAsync<BsonDocument>());
            try
            {
                var acc = collections.FindSync(filter).First<Account>();
                return acc;
            }
            catch //return null if email or password is incorrect
            {
                return null;
            }
        }
        public static IEnumerable<Account> GetAccounts => collections.AsQueryable();

        public static string RandomCode()
        {
            Random random = new Random();
            string letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            string code = string.Empty;
            for(int i=0;i<6;i++)
            {
                code += letter[random.Next(27)];
            }

            return code;
        }
    }
}
