import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'homepage.dart';


//Login Stateful page
class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final GoogleSignIn googleSignIn=new GoogleSignIn();
  final FirebaseAuth firebaseAuth=FirebaseAuth.instance;
  final formKey=GlobalKey<FormState>();
  TextEditingController emailController=TextEditingController();
  TextEditingController passwordController=TextEditingController();
  SharedPreferences preferences;

  bool load=false;
  bool checkLoged=false;

 @override
 void initState(){
   super.initState();
   isRegistered();
 }
  void isRegistered() async{
      setState(() {
        load=true;
      });
      preferences=await SharedPreferences.getInstance();
      checkLoged=await googleSignIn.isSignedIn();
      if(checkLoged)
        {
          Navigator.pushReplacement(context, MaterialPageRoute(builder:
              (context)=>HomePage()));
        }
      setState(() {
        load=false;
      });
  }
  Future checkSigning() async{
   preferences= await SharedPreferences.getInstance();

   setState(() {
     load=true;
   });
   GoogleSignInAccount googleSignInAccount=await googleSignIn.signIn();
   GoogleSignInAuthentication googleSignInAuthentication= await googleSignInAccount.authentication;

   final AuthCredential credential = GoogleAuthProvider.getCredential(
     accessToken: googleSignInAuthentication.accessToken,
     idToken: googleSignInAuthentication.idToken,
   );

   final AuthResult authResult = await firebaseAuth.signInWithCredential(credential);
   FirebaseUser firebaseUser= authResult.user;

   if(firebaseUser !=null)
     {
        final QuerySnapshot result=await Firestore.instance.collection("users").where(
          "id",isEqualTo: firebaseUser.uid).getDocuments();

        final List<DocumentSnapshot> userRecords=result.documents;

        if(userRecords.length==0)
          {
            //User not found
            Firestore.instance
                .collection("users")
                .document(firebaseUser.uid)
                .setData({
                  "id" : firebaseUser.uid,
              "userName":firebaseUser.displayName,
              "profile":firebaseUser.photoUrl
              }
            );
            await preferences.setString("id", firebaseUser.uid);
            await preferences.setString("userName", firebaseUser.displayName);
            await preferences.setString("profile", firebaseUser.photoUrl);
          }
        else
          {
            await preferences.setString("id", userRecords[0]['id']);
            await preferences.setString("userName", userRecords[0]['userName']);
            await preferences.setString("profile", userRecords[0]['profile']);
          }
        Fluttertoast.showToast(msg: "Login Success!");
        setState(() {
          load=false;
        });
        Navigator.pushReplacement(context, MaterialPageRoute(
          builder: (context)=>HomePage()
        ));
     }
   else
     {
        Fluttertoast.showToast(msg: "Login Failed!");
     }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: <Widget>[
          Image.asset('images/back.jpeg',fit: BoxFit.fill,width: double.infinity),
          Container(
            color: Colors.black.withOpacity(.4),
            width: double.infinity,
            height: double.infinity,
          ),
      Padding(
        padding: const EdgeInsets.only(top:250),
        child: Center(
               child: Form(
                 key: formKey,
                 child: Column(
                   children: <Widget>[
                     Padding(
                       padding: const EdgeInsets.all(8.0),
                       child: Material(
                         borderRadius: BorderRadius.circular(10.0),
                         color: Colors.white.withOpacity(.8),
                         elevation: 0,
                         child: Padding(
                           padding: const EdgeInsets.only(left:12.0),
                           child: TextFormField(
                             controller:emailController,
                             decoration: InputDecoration(
                               hintText: "Email ID",
                             icon: Icon(Icons.email),),
                             //Email Validation
                             validator: (value){
                               if(value.isEmpty)
                               {
                                 Pattern pattern=r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
                                 RegExp rex=RegExp(pattern);
                                 if(!rex.hasMatch(value))
                                   return 'Invalid Email ID!';
                                 else
                                   return null;
                               }
                              },
                           ),
                         ),
                       ),
                     ),
                     Padding(
                       padding: const EdgeInsets.all(8.0),
                       child: Material(
                         borderRadius: BorderRadius.circular(10.0),
                         color: Colors.white.withOpacity(.8),
                         elevation: 0,
                         child: Padding(
                           padding: const EdgeInsets.only(left:12.0),
                           child: TextFormField(
                             controller:passwordController,
                             decoration: InputDecoration(
                               hintText: "Password",
                               icon: Icon(Icons.lock),),
                             //Password Validation
                             validator: (value){
                               if(value.isEmpty)
                                 {
                                   return 'Password Cannot be Empty!';
                                 }
                               else if(value.length<6)
                                 {
                                   return 'Password too short!';
                                 }
                               else
                                 {
                                   return null;
                                 }
                             },

                           ),
                         ),
                       ),
                     ),
                     Padding(
                       padding: const EdgeInsets.all(8.0),
                       child: Material(
                         borderRadius: BorderRadius.circular(20.0),
                         color: Colors.lightGreen.withOpacity(.8),
                         elevation: 0,
                         child:MaterialButton(
                           onPressed: (){},
                           minWidth: MediaQuery.of(context).size.width,
                           child: Text("Login",textAlign: TextAlign.center,
                           style: TextStyle(color: Colors.white,fontWeight: FontWeight.bold,
                           fontSize: 20),),
                         )
                       ),
                     ),
                     Container(),
                     Divider(color: Colors.white,),
                     Padding(
                       padding: const EdgeInsets.all(8.0),
                       child: Material(
                           borderRadius: BorderRadius.circular(20.0),
                           color: Colors.red.withOpacity(.8),
                           elevation: 0,
                           child:MaterialButton(
                             onPressed: (){
                               checkSigning();
                             },
                             minWidth: MediaQuery.of(context).size.width,
                             child: Text("Google SingUp!",textAlign: TextAlign.center,
                               style: TextStyle(color: Colors.white,fontWeight: FontWeight.bold,
                                   fontSize: 20),),
                           )
                       ),
                     )
                   ],
                 ),
               ),
              ),
      ),
          Visibility(
            visible: load ?? true,
            child: Center(
              child: Container(
                alignment: Alignment.center,
                color: Colors.white.withOpacity(0.9),
                child: CircularProgressIndicator(
                  valueColor: AlwaysStoppedAnimation<Color>(Colors.green),
                ),
              ),
            ),
          )
        ],
      ),
    );
  }

}

