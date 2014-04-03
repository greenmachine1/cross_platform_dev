//
//  ViewController.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "ViewController.h"

#import "New_Account_viewViewController.h"

#import "UserInfo.h"

#import <Parse/Parse.h>

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
    
    // **** setting the textFieldDelegate to self **** //
    [userName setDelegate:self];
    [passWord setDelegate:self];
    
    
    // **** telling whether or not the user is currently **** //
    // **** still logged in or not **** //
    currentUser = [PFUser currentUser];
    
    // **** setting user defaults **** //
    defaults = [NSUserDefaults standardUserDefaults];
    
    // **** setting the current user to be saved **** //
    [defaults setObject:currentUser forKey:@"user"];
    
}



// **** if the user is still logged in when the **** //
// **** app is launched again, this screen will change **** //
// **** to the users info **** //
-(void)viewDidAppear:(BOOL)animated{
    
    if(currentUser){
        
        UserInfo *newUserInfo = [[UserInfo alloc] initWithNibName:@"UserInfo" bundle:nil];
    
        [self presentViewController:newUserInfo animated:true completion:nil];
    }
}







// **** called when the user hits 'new account' **** //
-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    
    // **** the 'Log in' button **** //
    if(button.tag == 0){
        
        
        // **** making sure the user has entered info **** //
        if(([userName.text isEqual:@""]) || ([passWord.text isEqual:@""])){
            
            
            NSLog(@"Please enter a username and password");
            
        }else{
            
            [PFUser logInWithUsernameInBackground:userName.text password:passWord.text block:^(PFUser *user, NSError *error) {
                
                // **** if the user name and password is good ****
                if(user){
                    
                    NSLog(@"succesful login");
                    
                    UserInfo *newUserInfo = [[UserInfo alloc] initWithNibName:@"UserInfo" bundle:nil];
                    [self presentViewController:newUserInfo animated:TRUE completion:nil];
                    
                    
                
                // **** if it is not **** //
                }else{
                    
                    NSLog(@"Please try again");
                    
                }
                
                
            }];
        }
        
        
        
    // **** create a new account button **** //
    }else if(button.tag == 1){
        
        // **** presenting the new account view **** //
        New_Account_viewViewController *newAccountViewController = [[New_Account_viewViewController alloc] initWithNibName:@"New_Account_viewViewController" bundle:nil];
        
        [self presentViewController:newAccountViewController animated:TRUE completion:nil];
        
    }
    
    
}









// **** called when the user hits return on the keyboard **** //
- (BOOL)textFieldShouldReturn:(UITextField *)textField{
    
    
    // **** resigning the textfields **** //
    [userName resignFirstResponder];
    [passWord resignFirstResponder];
    
    return true;
}








- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
