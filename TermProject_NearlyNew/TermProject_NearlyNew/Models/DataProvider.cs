using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;

namespace TermProject_NearlyNew.Models
{
    public class DataProvider
    {
        public static string ConnectionString = "mongodb+srv://nearlynew:nnpassword@daonguyendb-rytyz.mongodb.net/test?retryWrites=true&w=majority";

        //Connect to MongoDb
        public static MongoClient client = new MongoClient(ConnectionString);

        //Get Project's Database
        public static IMongoDatabase db = client.GetDatabase("nearly_new");

    }
}
