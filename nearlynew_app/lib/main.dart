import 'package:flutter/material.dart';
import 'package:nearlynew_app/sources/login.dart';

void main()
{
  runApp(
    MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
          primaryColor: Colors.green.shade900,
        ),
        home:LoginPage(),
    )

  );
}
