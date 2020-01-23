import 'package:flutter/material.dart';
import 'package:nearlynew_app/components/cartitems.dart';

class ShoppingCart extends StatefulWidget {
  @override
  _ShoppingCartState createState() => _ShoppingCartState();
}

class _ShoppingCartState extends State<ShoppingCart> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: new AppBar(
          elevation: 0.1,
          backgroundColor: Colors.green,
          title: Text('Shopping Basket'),
          actions: <Widget>[
            IconButton(icon: Icon(Icons.search,color: Colors.black),onPressed: ()
            {})
          ],
        ),

      //Add Cart Items to shopping basket
      body: CartItems(

      ),
      bottomNavigationBar:
      Container(
        color: Colors.white,
        child: Row(
          children: <Widget>[
            Expanded(
              child: ListTile(
                title: Text("Amount:"),
                subtitle: Text("\$230"),
              ),
            ),
            Expanded(
              child: MaterialButton(onPressed: ()
              {},
              child: Text("Proceed",style: TextStyle(color: Colors.white),),
              color: Colors.green,),
            )
          ],
        ),
      ),
    );
  }
}
