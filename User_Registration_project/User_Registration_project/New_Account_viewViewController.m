//
//  New_Account_viewViewController.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "New_Account_viewViewController.h"

#import <Parse/Parse.h>

@interface New_Account_viewViewController ()

@end

@implementation New_Account_viewViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    
    [userName setDelegate:self];
    [passWord setDelegate:self];
    [email setDelegate:self];
}




// **** user clicks done or cancel **** //
-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    if(button.tag == 0){
        
        
        // **** setting up the user account info **** //
        PFUser *user = [PFUser user];
        user.username = userName.text;
        user.password = passWord.text;
        user.email = email.text;
        
        
        // **** the sign up portion **** //
        [user signUpInBackgroundWithBlock:^(BOOL succeeded, NSError *error) {
            
            // **** successful creation of user **** //
            if(!error){
                NSLog(@"Successful creation of account");
                
                
                // **** go back to the main screen **** //
                [self dismissViewControllerAnimated:TRUE completion:nil];
                
            // **** something happened and it work **** //
            }else{
                
                NSLog(@"There was an error in creating this account");
                NSLog(@"%@", error);
                
            }
            
        }];
        
        
        
        
        
    }else if (button.tag == 1){
        
        [self dismissViewControllerAnimated:TRUE completion:nil];
        
    }

    
}


// **** setting up the return key in the keyboard **** //
- (BOOL)textFieldShouldReturn:(UITextField *)textField{
    
    [userName resignFirstResponder];
    [passWord resignFirstResponder];
    [email resignFirstResponder];
    
    
    return  TRUE;
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
