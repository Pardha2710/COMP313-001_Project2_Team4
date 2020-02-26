import 'package:firebase_database/firebase_database.dart';

class UsersRegister{

  FirebaseDatabase database=FirebaseDatabase.instance;
  String reference="Users";

  createUsers(Map user){
    String id=user["userId"];

    database.reference().child("$reference/$id").set(
      user
    ).catchError((e)=>{print(e.toString())});
  }
}