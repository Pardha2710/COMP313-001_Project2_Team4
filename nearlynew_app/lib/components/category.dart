import 'package:flutter/material.dart';
class CategoryList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 80.0,
      child: ListView(
        scrollDirection: Axis.horizontal,
        children: <Widget>[
          ItemsList(location:'images/cats/dress.png',
              caption: 'Dresses'),
          ItemsList(location:'images/cats/formal.png',
              caption: 'Formals'),
          ItemsList(location:'images/cats/informal.png',
              caption: 'Casuals'),
          ItemsList(location:'images/cats/jeans.png',
              caption: 'Jeans'),
          ItemsList(location:'images/cats/shoe.png',
              caption: 'Shoes'),
          ItemsList(location:'images/cats/tshirt.png',
              caption: 'T-Shirts'),
          ItemsList(location:'images/cats/accessories.png',
              caption: 'Others')
        ],
      ),

    );
  }
}
class ItemsList extends StatelessWidget {
  final String location;
  final String caption;
  ItemsList(
  {
    this.location,
    this.caption
});
  @override
  Widget build(BuildContext context) {
    return Padding(padding: const EdgeInsets.all(0.0),
    child: InkWell(
      onTap: (){},
      child: Container(
        width: 90.0,
        child: ListTile(
          title: Image.asset(location,width: 40,height: 40.0),
          subtitle: Container(
            alignment: Alignment.topCenter,
            child: Text(caption,style: TextStyle(fontWeight: FontWeight.bold,
                color: Colors.black87),),
          ),
        ),
      ),
    ),
    );
  }
}

