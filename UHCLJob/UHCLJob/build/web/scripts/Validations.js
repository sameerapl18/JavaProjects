/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateResume(){
    
    var name = document.getElementById('myForm:name').value;
    var contactNo = document.getElementById('myForm:contactNo').value;
    var email = document.getElementById('myForm:emailID').value;
    var skills = document.getElementById('myForm:skills').value;
    var experience = document.getElementById('myForm:experience').value;
    
    var phoneno = /^\d{10}$/; 
    var re = /\S+@\S+\.\S+/;
    var nameJS = /^[A-Za-z\s]+$/;
   
 
    if(!(name.match(nameJS))){
        
        alert("Name is not valid");
      
    }
     else if(!(contactNo.match(phoneno))) {
          alert("phone number is not valid"); 
          
         
     } 
    else if(!(email.match(re))){
        
         alert("email id  is not valid");  
         
    } 
    else if(skills == null || skills == ""){
        
        alert("skills Cannot be Empty");
    }
    else if(isNaN(experience)){
        
        alert("experience is not valid");
    }
    else{
        
        return true;
    }
    
    return false;
}


function validateRegister(){
    var name = document.getElementById('registerForm:userName').value;
    var userID = document.getElementById('registerForm:userID').value;
    var pwd = document.getElementById('registerForm:psw').value;
    
    var password = /^[A-Za-z0-9-]/;
    var nameJS = /^[A-Za-z\s]+$/;
    
    if(!(name.match(nameJS))){
        
        alert("Name is not valid");
           }
    
     else if(userID == null || userID == ""){
        
        alert("UserID is not valid");
            }
    
   
     else if(!(pwd.match(password))) {
          alert("Password is not valid");
          
         
     } 
     else{
         return true;
         
     }
     
     return false;
    
    
    
}

function validateLogin(){ 
    var userID = document.getElementById('loginForm:userID').value;
    var pwd = document.getElementById('loginForm:psw').value;
    
    var password = /^[A-Za-z0-9-]/;
    var nameJS = /^[A-Za-z\s]+$/;
    
    
     if(userID == null || userID == ""){
        
        alert("UserID can not be empty");
            }
    
     else if(pwd == null || pwd == ""){
        
        alert("Password can not be empty");
            }
   
     else if(!(pwd.match(password))) {
          alert("Password is not valid");
          
         
     } 
     else{
         return true;
         
     }
     
     return false;
    
    

    
    
}

function validateNewJob()
{
    
    
    var jobTitle = document.getElementById('newJobForm:jobTitle').value;
    var jobDesc = document.getElementById('newJobForm:jobDesc').value;
    var vacancies = document.getElementById('newJobForm:vacancies').value;
    var jobStatus = document.getElementById('newJobForm:jobStatus').value;
    
    if(jobTitle == null || jobTitle == "")
    {
        alert("Job Title can not be empty");
    }
    else if(jobDesc == null || jobDesc == "")
    {
        alert("Job Description can not be empty");
    }
    else if(vacancies == null || vacancies == "")
    {
        alert("Number of vacancies can not be empty");
    }
    else if(isNaN(vacancies))
    {
        alert("Number of vacancies entered is not valid");
    }
//    else if(jobStatus == null || jobStatus == "")
//    {
//        alert("Job Status can not be empty");
//    }
    else
    {
        return true;
    }
    return false;
}


function validateModifyJob()
{
    
    
    var jobTitle = document.getElementById('modifyJobForm:jobTile').value;
    var jobDesc = document.getElementById('modifyJobForm:jobbDescription').value;
    var vacancies = document.getElementById('modifyJobForm:vacancies').value;
    var jobStatus = document.getElementById('modifyJobForm:jobStatus').value;
    
    if(jobTitle == null || jobTitle == "")
    {
        alert("Job Title can not be empty");
    }
    else if(jobDesc == null || jobDesc == "")
    {
        alert("Job Description can not be empty");
    }
    else if(vacancies == null || vacancies == "")
    {
        alert("Number of vacancies can not be empty");
    }
    else if(isNaN(vacancies))
    {
        alert("Number of vacancies entered is not valid");
    }
//    else if(jobStatus == null || jobStatus == "")
//    {
//        alert("Job Status can not be empty");
//    }
    else
    {
        return true;
    }
    return false;
}
