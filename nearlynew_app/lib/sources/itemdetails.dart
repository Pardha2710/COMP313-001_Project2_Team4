import 'package:flutter/material.dart';
import 'package:nearlynew_app/sources/homepage.dart';


//Item details
class ItemDetails extends StatefulWidget {
  final itemName;
  final itemPicture;
  final itemNewPrice;
  final itemOldPrice;

  ItemDetails({
    this.itemName,this.itemNewPrice,this.itemOldPrice,this.itemPicture,

});
  @override
  _ItemDetailsState createState() => _ItemDetailsState();
}

class _ItemDetailsState extends State<ItemDetails> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: new AppBar(
        elevation: 0.1,
        backgroundColor: Colors.green,
        title: InkWell(
            onTap:(){
              Navigator.push(context, MaterialPageRoute(builder:
                  (context)=>new HomePage()));
            },
            child: Text('nearlyNew')),
        actions: <Widget>[
          IconButton(icon: Icon(Icons.search,color: Colors.black),onPressed: ()
          {}),
        ],
      ),
      body: new ListView(
        children: <Widget>[
          Container(
            height: 300,
            child: GridTile(
              child: Container(
                color: Colors.white,
                child: Image.asset(widget.itemPicture),
              ),
              footer: Container(
                color: Colors.white,
                child: ListTile(
                  leading: Text(widget.itemName,
                  style: TextStyle(fontWeight: FontWeight.bold,fontSize:16.0),),
                  title: Row(
                    children: <Widget>[
                      Expanded(
                        child:new Text("\$${widget.itemOldPrice}",
                        style: TextStyle(color: Colors.grey,decoration: TextDecoration.lineThrough,fontWeight: FontWeight.bold),),
                      ),
                      Expanded(
                        child:new Text("\$${widget.itemNewPrice}",
                        style: TextStyle(fontWeight: FontWeight.bold,color: Colors.red),),
                      )
                    ],
                  ),
                ),
              ),
            ),
          ),
          Row(
            children: <Widget>[
              //Size drop down
              Expanded(
                child: MaterialButton(
                  onPressed: (){
                    showDialog(context: context,
                    builder: (context)
                    {
                        return AlertDialog(
                          title: Text("Size"),
                          content: Text("Choose Size:"),
                          actions: <Widget>[
                            MaterialButton(onPressed: (){
                              Navigator.of(context).pop(context);
                            },
                            child: Text('Done'),)
                          ],

                        );
                    });
                  },
                  color: Colors.white,
                  textColor: Colors.grey,
                  elevation: 0.2,
                  child: Row(
                    children: <Widget>[
                      Expanded(
                        child: Text("Size",style: TextStyle(fontWeight: FontWeight.bold),)
                      ),
                      Expanded(
                        child: Icon(Icons.keyboard_arrow_down),
                      ),
                    ],
                  ),
                ),
              ),
              //Color Drop Down
              Expanded(
                child: MaterialButton(
                  onPressed: (){
                    showDialog(context: context,
                        builder: (context)
                        {
                          return AlertDialog(
                            title: Text("Color"),
                            content: Text("Choose Color:"),
                            actions: <Widget>[
                              MaterialButton(onPressed: (){
                                Navigator.of(context).pop(context);
                              },
                                child: Text('Done'),)
                            ],

                          );
                        });
                  },
                  color: Colors.white,
                  textColor: Colors.grey,
                  elevation: 0.2,
                  child: Row(
                    children: <Widget>[
                      Expanded(
                          child: Text("Colors",style: TextStyle(fontWeight: FontWeight.bold),)
                      ),
                      Expanded(
                        child: Icon(Icons.keyboard_arrow_down),
                      ),

                    ],
                  ),
                ),
              ),
              //Quantity Drop down
              Expanded(
                child: MaterialButton(
                  onPressed: (){
                    showDialog(context: context,
                        builder: (context)
                        {
                          return AlertDialog(
                            title: Text("Quantity"),
                            content: Text("No of Items:"),
                            actions: <Widget>[
                              MaterialButton(onPressed: (){
                                Navigator.of(context).pop(context);
                              },
                                child: Text('Done'),)
                            ],

                          );
                        });
                  },
                  color: Colors.white,
                  textColor: Colors.grey,
                  elevation: 0.2,
                  child: Row(
                    children: <Widget>[
                      Expanded(
                          child: Text("Count",style: TextStyle(fontWeight: FontWeight.bold),)
                      ),
                      Expanded(
                        child: Icon(Icons.keyboard_arrow_down),
                      ),

                    ],
                  ),
                ),
              )
            ],
          ),
          Row(
            children: <Widget>[
              //Size drop down
              Expanded(
                child: MaterialButton(
                  onPressed: (){},
                  color: Colors.green,
                  textColor: Colors.white,
                  elevation: 0.2,
                  child: new Text("Buy")
                ),
              ),
              IconButton(icon: Icon(Icons.favorite_border,color: Colors.green),onPressed: (){}),

              IconButton(icon: Icon(Icons.shopping_cart,color: Colors.green),onPressed: (){})
            ],
          ),
          Divider(color: Colors.green,),
          //Product Description
          new ListTile(
            title: Text('Product Details',style: TextStyle(fontWeight: FontWeight.bold)),
            subtitle:
            Text("The men's blazer is by definition an odd jacket, or a jacket that has been cut separately without a matching pair of trousers, clearly differentiating it from a suit.You can wear a blazer with gray trousers and a tie to work, and then remove the tie and throw on some jeans for a dinner date in the city"),
          ),
          Divider(),

          //Product Details
          Row(
            children: <Widget>[
              Padding(padding: const EdgeInsets.fromLTRB(12, 5, 5, 5),
              child: Text("Item Name:",style: TextStyle(color: Colors.black87))),
              Padding(padding: EdgeInsets.all(5),
              child: Text(widget.itemName),)
            ],
          ),
          Row(
            children: <Widget>[
              Padding(padding: const EdgeInsets.fromLTRB(12, 5, 5, 5),
                  child: Text("Brand:",style: TextStyle(color: Colors.black87))),
              Padding(padding: EdgeInsets.all(5),
                child: Text("Brand X"),)
            ],
          ),
          Row(
            children: <Widget>[
              Padding(padding: const EdgeInsets.fromLTRB(12, 5, 5, 5),
                  child: Text("Rating:",style: TextStyle(color: Colors.black87))),
              Padding(padding: EdgeInsets.all(5),
                child: Text("70%"),)
            ],
          ),

          Padding(
            padding: const EdgeInsets.all(6),
          ),
          Text("Similar Products",style: TextStyle(fontWeight: FontWeight.bold),),
          Divider(),
          //Matching Products
          Container(
            height: 360,
            child: Matching(),

          )
        ],
      ),
    );
  }
}

class Matching extends StatefulWidget {
  @override
  _MatchingState createState() => _MatchingState();
}

class _MatchingState extends State<Matching> {
  var items_list=[
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
      "itemName":"Joggers",
      "picture":"images/products/pants1.jpeg",
      "oldPrice":25,
      "price":15,
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
          return SimilarProducts(
            itemName: items_list[index]['itemName'],
            image: items_list[index]['picture'],
            old_price: items_list[index]['oldPrice'],
            new_price: items_list[index]['price'],
          );
        }
    );
  }
}

class SimilarProducts extends StatelessWidget {
  final itemName;
  final image;
  final old_price;
  final new_price;

  SimilarProducts(
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
