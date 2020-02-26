import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'login.dart';
import '../database/users.dart';
import 'homepage.dart';

class Register extends StatefulWidget {
  @override
  _RegisterState createState() => _RegisterState();
}

class _RegisterState extends State<Register> {

  final FirebaseAuth firebaseAuth=FirebaseAuth.instance;
  final formKey=GlobalKey<FormState>();
  TextEditingController nameController=TextEditingController();
  TextEditingController emailController=TextEditingController();
  TextEditingController passwordController=TextEditingController();
  TextEditingController confirmPasswordController=TextEditingController();
  UsersRegister usersRegister=UsersRegister();
  String gender;
  String genereValue='Male';
  bool hide=true;
  bool load=false;
  @override
  Widget build(BuildContext context) {
    //double height = MediaQuery.of(context).size.height / 3;
    return Scaffold(
      body: Stack(
        children: <Widget>[
          Image.asset('images/back.jpeg',fit: BoxFit.fill,width: double.infinity,height:double.infinity),
          Container(
            color: Colors.black.withOpacity(.4),
            width: double.infinity,
            height: double.infinity,
          ),
          Container(
            alignment: Alignment.topCenter,
            child: Image.asset('images/nearlyNew.png',width: 140,height: 350,
                color: Color.fromRGBO(255, 255, 255, 0.5),
                colorBlendMode: BlendMode.modulate),
          ),
          Center(
            child: Padding(
              padding: const EdgeInsets.only(top:250),
              child: Center(
                child: Form(
                  key: formKey,
                  child: ListView(
                    children: <Widget>[
                      Padding(
                        padding: const EdgeInsets.fromLTRB(14,8,14,8),
                        child: Material(
                          borderRadius: BorderRadius.circular(10.0),
                          color: Colors.white.withOpacity(.8),
                          elevation: 0,
                          child: Padding(
                            padding: const EdgeInsets.only(left:12.0),
                            child: TextFormField(
                              controller:nameController,
                              decoration: InputDecoration(
                                hintText: "Full Name",
                                icon: Icon(Icons.assignment_ind),
                                  border: InputBorder.none),
                              //Password Validation
                              validator: (value){
                                if(value.isEmpty)
                                {
                                  return 'Name Cannot be Empty!';
                                }
                                return null;
                              },

                            ),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.fromLTRB(14.0, 8.0, 14.0, 8.0),
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
                                icon: Icon(Icons.email),
                                  border: InputBorder.none),
                              //Email Validation
                              validator: (value){
                                if(value.isEmpty)
                                {
                                  Pattern pattern=r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
                                  RegExp rex=RegExp(pattern);
                                  if(!rex.hasMatch(value))
                                    return 'Invalid Email ID!';
                                }
                                return null;
                              },
                            ),
                          ),
                        ),
                      ), Padding(
                        padding: const EdgeInsets.fromLTRB(14,8,14,8),
                        child: Container(
                          color: Colors.white.withOpacity(0.4),
                          child: Row(
                            children: <Widget>[
                              Expanded(child: ListTile(title:Text("Male",style: TextStyle(color: Colors.white),
                              ),trailing:  Radio(value: "Male",groupValue:genereValue,onChanged:(e)=>valueChanged(e)),
                              )),
                              Expanded(child: ListTile(title:Text("Female",
                                textAlign: TextAlign.end,
                                style: TextStyle(color: Colors.white),
                              ),trailing:  Radio(value: "Female",groupValue:genereValue,onChanged:(e)=>valueChanged(e)),
                              ))
                            ],
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.fromLTRB(14,8,14,8),
                        child: Material(
                          borderRadius: BorderRadius.circular(10.0),
                          color: Colors.white.withOpacity(.8),
                          elevation: 0,
                          child: Padding(
                            padding: const EdgeInsets.only(left:12.0),
                            child: ListTile(
                              title: TextFormField(
                                controller:passwordController,
                                obscureText: hide,
                                decoration: InputDecoration(
                                  hintText: "Password",
                                  icon: Icon(Icons.lock),
                                    border: InputBorder.none),
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
                              trailing:IconButton(icon: Icon(Icons.remove_red_eye,),onPressed:(){
                                setState(() {
                                  hide=false;
                                });
                              } ,) ,
                            ),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.fromLTRB(14,8,14,8),
                        child: Material(
                          borderRadius: BorderRadius.circular(10.0),
                          color: Colors.white.withOpacity(.8),
                          elevation: 0,
                          child: Padding(
                            padding: const EdgeInsets.only(left:12.0),
                            child: ListTile(
                              title: TextFormField(
                                controller:confirmPasswordController,
                                obscureText: hide,
                                decoration: InputDecoration(
                                  hintText: "Confirm Password",
                                  icon: Icon(Icons.lock),
                                border: InputBorder.none),

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
                                  else if(passwordController.text !=value)
                                  {
                                    return 'Password miss match';
                                  }
                                  return null;
                                },

                              ),
                              trailing:IconButton(icon: Icon(Icons.remove_red_eye,),onPressed:(){
                                setState(() {
                                  hide=false;
                                });
                              } ,),
                            ),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.fromLTRB(14,8,14,8),
                        child: Material(
                            borderRadius: BorderRadius.circular(20.0),
                            color: Colors.lightGreen.withOpacity(.8),
                            elevation: 0,
                            child:MaterialButton(
                              onPressed: () async{
                                userValidation();
                              },
                              minWidth: MediaQuery.of(context).size.width,
                              child: Text("Register",textAlign: TextAlign.center,
                                style: TextStyle(color: Colors.white,fontWeight: FontWeight.bold,
                                    fontSize: 20),),
                            )
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8),
                        child:
                        InkWell(
                            onTap: (){
                              Navigator.pushReplacement(context, MaterialPageRoute(
                                  builder: (context)=>LoginPage()
                              ));
                            },
                            child: Text("Login",textAlign:TextAlign.center,style: TextStyle(color: Colors.deepOrange),)),
                      ),
                    ],
                  ),
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

  valueChanged(e) {
    setState(() {
      if(e=="Male")
        {
          genereValue=e;
          gender=e;
        }
      else if(e=="Female")
        {
          genereValue=e;
          gender=e;
        }

    });
  }

  Future userValidation() async {

    FormState formState=formKey.currentState;

    if(formState.validate())
      {
        formState.reset();
       FirebaseUser user=await firebaseAuth.currentUser();
       if(user==null)
         {
            firebaseAuth.createUserWithEmailAndPassword(email: emailController.text, password: passwordController.text).
            then((user)=>
            {
              usersRegister.createUsers(
              {
                  "fullname":emailController.text,
                  "emaiId":nameController.text,
                  "userId":user.user.uid,
                  "gender":gender,
              }
            )
            }).catchError((err)=>{print(err.toString())})
            ;
            Navigator.pushReplacement(context, MaterialPageRoute(builder:
                (context)=>HomePage()));
         }
      }
  }
}
