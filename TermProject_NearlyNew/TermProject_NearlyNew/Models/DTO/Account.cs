using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.Data;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Driver;

namespace TermProject_NearlyNew.Models.DTO
{
    public class Account
    {
        [BsonId]
        public ObjectId ID { get; set; }

        [BsonElement("email")]
        public string Email { get; set; }

        [BsonElement("password")]
        public string Password { get; set; }
        [BsonElement("role")]
        public string Role { get; set; }
        public Account()
        {

        }
        //public Account(IAsyncCursor<Account> cursor)
        //{
            
        //    ID = cursor.First<Account>().ID;
        //    Email = cursor.First<Account>().Email;
        //    Password = cursor.First<Account>().Password;
        //}

        public Account(BsonDocument document)
        {
            ID = document.GetElement("_id").Value.AsObjectId;
            Email = document.GetElement("email").Value.AsString;
            Password = document.GetElement("password").Value.AsString;
            Role = document.GetElement("role").Value.AsString;
        }
    }
}
