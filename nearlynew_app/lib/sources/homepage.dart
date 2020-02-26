import 'package:flutter/material.dart';
import 'package:carousel_pro/carousel_pro.dart';
import 'package:nearlynew_app/components/category.dart';
import 'package:nearlynew_app/components/items.dart';
import 'package:nearlynew_app/sources/shoppingcart.dart';
import 'login.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    Widget display=new Container(
        height: 200.0,
        child: Carousel(
          boxFit: BoxFit.cover,
          images: [
            AssetImage('images/c1.jpg'),
            AssetImage('images/m1.jpeg'),
            AssetImage('images/m2.jpg'),
            AssetImage('images/w1.jpeg'),
            AssetImage('images/w3.jpeg'),
            AssetImage('images/w4.jpeg')
          ],
          autoplay: false,
          dotSize: 4.0,
          dotColor: Colors.green,
          indicatorBgPadding: 2.0,
          dotBgColor: Colors.transparent,
          //  animationCurve: Curves.fastOutSlowIn,
          //animationDuration: Duration(microseconds: 1000),
        )
    );
    return Scaffold(
      appBar: new AppBar(
        elevation: 0.1,
        backgroundColor: Colors.green,
        title: Text('nearlyNew'),
        actions: <Widget>[
          IconButton(icon: Icon(Icons.search,color: Colors.black),onPressed: ()
          {}),
          IconButton(icon: Icon(Icons.add_shopping_cart,color: Colors.black),onPressed: ()
          {
            Navigator.push(context, MaterialPageRoute(builder:
                (context)=>new ShoppingCart()));
          })
        ],
      ),
      drawer: Drawer(
        child: ListView(
          children: <Widget>[
            //Hearder
            UserAccountsDrawerHeader(
              accountName: Text('admin'),
              accountEmail: Text('admin@gmail.com'),
              currentAccountPicture: GestureDetector(
                child: CircleAvatar(
                  backgroundColor: Colors.black,
                  child: Icon(Icons.person,color: Colors.white),
                ),
              ),
              decoration: BoxDecoration(
                  color: Colors.green
              ),
            ),

            //body
            InkWell(
              onTap: (){},
              child: ListTile(
                title: Text('Home'),
                leading: Icon(Icons.home,color: Colors.green),
              ),
            ),
            InkWell(
              onTap: (){},
              child: ListTile(
                title: Text('Profile'),
                leading: Icon(Icons.account_box,color: Colors.green),
              ),
            ),
            InkWell(
              onTap: (){},
              child: ListTile(
                title: Text('My Orders'),
                leading: Icon(Icons.shop,color: Colors.green),
              ),
            ),
            InkWell(
              onTap: (){
                Navigator.push(context, MaterialPageRoute(builder:
                    (context)=>ShoppingCart()));
              },
              child: ListTile(
                title: Text('Shopping Basket'),
                leading: Icon(Icons.shopping_cart,color: Colors.green),
              ),
            ),
            InkWell(
              onTap: (){},
              child: ListTile(
                title: Text('Wish List'),
                leading: Icon(Icons.favorite_border,color: Colors.green),
              ),
            ),
            Divider(),
            InkWell(
              onTap: (){},
              child: ListTile(
                title: Text('Settings'),
                leading: Icon(Icons.settings_applications,color: Colors.green),
              ),
            ),
            InkWell(
              onTap: (){},
              child: ListTile(
                title: Text('Contact Us'),
                leading: Icon(Icons.contact_phone,color: Colors.green),
              ),
            ),
            InkWell(
              onTap: (){},
              child: ListTile(
                title: Text('About'),
                leading: Icon(Icons.live_help,color: Colors.green),
              ),
            ),
            InkWell(
              onTap: (){
                Navigator.pushReplacement(context, MaterialPageRoute(builder:
                    (context)=>LoginPage()));
              },
              child: ListTile(
                title: Text('SignOut'),
                leading: Icon(Icons.vpn_key,color: Colors.green),
              ),
            )
          ],
        ),
      ),
      body: Column(
        children: <Widget>[
          //Image carousel
          display,

          //Padding Widget for Category
          new Padding(padding: const EdgeInsets.all(4.0),
            child: Container(
                alignment:Alignment.centerLeft,child: Text('Categories',style: TextStyle(color: Colors.black87,fontWeight: FontWeight.bold),)),
          ),


          CategoryList(),

          //Padding Widget for Recent Products
          new Padding(padding: const EdgeInsets.all(4.0),
            child: Container(
                alignment:Alignment.centerLeft
                ,child: Text('Recent Items',style: TextStyle(color: Colors.black87,fontWeight: FontWeight.bold),)),),


          //Grid View Items
          Flexible(
            child: Items(),
          )

        ],
      ),
    );
  }
}