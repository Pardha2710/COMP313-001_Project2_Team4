import 'package:flutter/material.dart';

class CartItems extends StatefulWidget {

  @override
  _CartItemsState createState() => _CartItemsState();
}

class _CartItemsState extends State<CartItems> {

  var items_cart=[
    {
      "itemName":"Mens Blazer",
      "picture":"images/products/blazer1.jpeg",
      "price":80,
      "size":"S",
      "color":"Navy",
      "count":1
    },
    {
      "itemName":"Jeans",
      "picture":"images/products/pants2.jpeg",
      "price":18,
      "size":"32",
      "color":"Blue",
      "count":1
    },
    {
      "itemName":"Casual Shoe",
      "picture":"images/products/shoe1.jpeg",
      "price":25,
      "size":"9",
      "color":"Black",
      "count":1,
    },
  ];
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: items_cart.length,
      itemBuilder: (context,index){
        return SingleCartItem(
          cartItemName: items_cart[index]["itemName"],
          cartItemColor: items_cart[index]["color"],
          cartItemCount: items_cart[index]["count"],
          cartItemSize: items_cart[index]["size"],
          cartItemPrice: items_cart[index]["price"],
          cartItemPicture:items_cart[index]["picture"] ,
        );
      },

    );
  }
}

class SingleCartItem extends StatelessWidget {

  final cartItemName;
  final cartItemPicture;
  final cartItemPrice;
  final cartItemSize;
  final cartItemColor;
  final cartItemCount;

  SingleCartItem({
    this.cartItemName,this.cartItemPicture,this.cartItemPrice,
    this.cartItemSize,this.cartItemColor,this.cartItemCount
  });
  @override
  Widget build(BuildContext context) {
    return Card(
      child: ListTile(
        onTap: (){},

        //Image on the Cart Page
        leading: Image.asset(cartItemPicture,width: 80,height:80 ),

        //Item Name on the Cart Page
        title: Text(cartItemName),
        subtitle: Column(
          children: <Widget>[
            Row(
              children: <Widget>[
                //Size of the product in cart
                Padding(
                  padding: const EdgeInsets.all(0.0),
                  child: Text("Size:"),
                ),
                Padding(
                  padding: const EdgeInsets.all(4.0),
                  child: Text(cartItemSize,style: TextStyle(fontWeight: FontWeight.bold)),
                ),
                //Color of the selected product
                Padding(
                  padding: const EdgeInsets.fromLTRB(20, 8, 8, 8),
                  child: Text("Color:"),
                ),
                Padding(
                  padding: const EdgeInsets.all(4.0),
                  child: Text(cartItemColor,style: TextStyle(fontWeight: FontWeight.bold)),
                ),
                Padding(
                  padding: const EdgeInsets.fromLTRB(60, 8, 8, 8),
                  child: Column(
                    children: <Widget>[
                      IconButton(icon: Icon(Icons.arrow_drop_up),onPressed: (){}),
                      Text("$cartItemCount"),
                      IconButton(icon: Icon(Icons.arrow_drop_down),onPressed: (){})
                    ],

                  ),
                )
               ],
            ),

            //Price of the Product in the Cart
            Container(
              alignment: Alignment.topLeft,
              child: Text("\$${cartItemPrice}",style: TextStyle(fontSize: 17,fontWeight: FontWeight.bold),),
            ),

          ],
        ),

        //Quantity of items
      ),
    );
  }
}
