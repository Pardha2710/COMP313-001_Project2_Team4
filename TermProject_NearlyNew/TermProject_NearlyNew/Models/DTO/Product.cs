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
    public class Product
    {
        [BsonId]
        public ObjectId ID { get; set; }

        [BsonElement("Title")]
        public string Title { get; set; }
        
        [BsonElement("Category")]
        public string Category { get; set; }

        [BsonElement("Description")]
        public string Description { get; set; }

        [BsonElement("Gender")]
        public string Gender { get; set; }

        [BsonElement("Size")]
        public int Size { get; set; }

        [BsonElement("Age")]
        public int Age { get; set; }

        [BsonElement("Price")]
        public double Price { get; set; }

        public Product()
        {

        }

        public Product(BsonDocument document)
        {
            ID = document.GetElement("_id").Value.AsObjectId;
            Title = document.GetElement("title").Value.AsString;
            Description = document.GetElement("description").Value.AsString;
            Gender = document.GetElement("gender").Value.AsString;
            Size = document.GetElement("size").Value.AsInt32;
            Age = document.GetElement("age").Value.AsInt32;
            Price = document.GetElement("price").Value.AsDouble;
        }

        



    }
}
