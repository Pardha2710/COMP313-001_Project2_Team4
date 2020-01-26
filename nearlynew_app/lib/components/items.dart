import 'package:flutter/material.dart';
import 'package:nearlynew_app/sources/itemdetails.dart';

class Items extends StatefulWidget {
  @override
  _ItemsState createState() => _ItemsState();
}

class _ItemsState extends State<Items> {
  var items_list=[
    {
      "itemName":"Mens Blazer",
      "picture":"images/products/blazer1.jpeg",
      "oldPrice":120,
      "price":80,
    },
    {
      "itemName":"Womens Blazer",
      "picture":"images/products/blazer2.jpeg",
      "oldPrice":90,
      "price":60,
    },
    {
      "itemName":"Red Dress",
      "picture":"images/products/dress1.jpeg",
      "oldPrice":60,
      "price":35,
    },
    {
      "itemName":"Black Dress",
      "picture":"images/products/dress2.jpeg",
      "oldPrice":50,
      "price":30,
    },
    {
      "itemName":"Party Sandals",
      "picture":"images/products/hills1.jpeg",
      "oldPrice":66,
      "price":32,
    },
    {
      "itemName":"High Hills",
      "picture":"images/products/hills2.jpeg",
      "oldPrice":55,
      "price":40,
    },
    {
      "itemName":"Joggers",
      "picture":"images/products/pants1.jpeg",
      "oldPrice":25,
      "price":15,
    },
    {
      "itemName":"Jeans",
      "picture":"images/products/pants2.jpeg",
      "oldPrice":30,
      "price":18,
    },
    {
      "itemName":"Casual Shoe",
      "picture":"images/products/shoe1.jpeg",
      "oldPrice":35,
      "price":25,
    },
    {
      "itemName":"Designer Skirt",
      "picture":"images/products/skt1.jpeg",
      "oldPrice":49,
      "price":39,
    },
    {
      "itemName": "Pink Skirt",
      "picture": "images/products/skt2.jpeg",
      "oldPrice": 45,
      "price": 37,
    }
  ];

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
        itemCount: items_list.length,
        gridDelegate: new SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2),
        itemBuilder: (BuildContext context,int index){
          return Padding(
            padding: const EdgeInsets.all(4.0),
            child: Products(
              itemName: items_list[index]['itemName'],
              image: items_list[index]['picture'],
              old_price: items_list[index]['oldPrice'],
              new_price: items_list[index]['price'],
            ),
          );
        }
    );
  }
}

class Products extends StatelessWidget {
  final itemName;
  final image;
  final old_price;
  final new_price;

  Products(
  {
    this.itemName,this.image,this.old_price,this.new_price,
    }
      );


  @override
  Widget build(BuildContext context) {
    return Card(
      child: Hero(
        tag:Text("One"),

        child: Material(
          child: InkWell(
            onTap: ()=>Navigator.of(context).push(new MaterialPageRoute(
              //Navigating the Product Details
                builder: (context)=>new ItemDetails(
                  itemName: itemName,itemPicture: image,
                  itemNewPrice: new_price,itemOldPrice: old_price
                ))),
            child: GridTile(
              footer: Container(
                color: Colors.white70,
                child: Row(
                  children: <Widget>[
                    Expanded(
                      child: Text(itemName,style: TextStyle(fontWeight: FontWeight.bold,
                      fontSize: 16))
                    ),
                    Text("\$${new_price}",style: TextStyle(color: Colors.red,
                    fontWeight: FontWeight.bold))
                  ],
                )
              ),
              child:Image.asset(image,
              fit: BoxFit.cover,),

            ),
          ),

      ),),
    );
  }
}
