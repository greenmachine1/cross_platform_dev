//
//  ViewController.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "ViewController.h"

#import "New_Account_viewViewController.h"

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
    
    
    // **** testing the parse framework - sending out some info **** //
    // **** to the cloud server **** //
    PFObject *testObject = [PFObject objectWithClassName:@"TestObject"];
    testObject[@"foo"] = @"bar is great and tasty!";
    [testObject saveInBackground];
    
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
            
            NSLog(@"Authenticating...");
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
