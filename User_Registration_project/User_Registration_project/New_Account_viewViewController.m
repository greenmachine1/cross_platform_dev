//
//  New_Account_viewViewController.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "New_Account_viewViewController.h"

#import "UserInfo.h"

#import <Parse/Parse.h>

@interface New_Account_viewViewController ()

@end

@implementation New_Account_viewViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        
        defaults = [NSUserDefaults standardUserDefaults];
        
        reachability = [Reachability reachabilityWithHostname:@"http://www.yahoo.com"];
        
        reachability.reachableOnWWAN = YES;
        
        // **** using NSNotificationCenter **** //
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reachabilityMethod:) name:kReachabilityChangedNotification object:nil];
        
        [reachability stopNotifier];
        
        
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    
    [userName setDelegate:self];
    [passWord setDelegate:self];
    [email setDelegate:self];
    
    userName.clearsOnBeginEditing = true;
    passWord.clearsOnBeginEditing = true;
    email.clearsOnBeginEditing = true;
    
    userNameLabel.hidden = NO;
    passwordLabel.hidden = NO;
    emailLabel.hidden = NO;
    
    firstLine.hidden = YES;
    secondLine.hidden = YES;
    thirdLine.hidden = YES;
}


// **** when connectivity changes **** //
-(void)reachabilityMethod:(NSNotification *)notify{
    
    // **** if there is no connectivity **** //
    if(reachability.isReachable == 0){
        
        firstLine.hidden = NO;
        secondLine.hidden = NO;
        thirdLine.hidden = NO;
        
        userNameLabel.hidden = YES;
        passwordLabel.hidden = YES;
        emailLabel.hidden = YES;
        
        userName.hidden = YES;
        passWord.hidden = YES;
        email.hidden = YES;
        
        createAccountButton.hidden = YES;
        
        // **** I want the user to be able to cancel out **** //
        // **** and go back to the main screen even though **** //
        // **** they dont have connectivity which is why **** //
        // **** im not hidding the cancel button **** //
        
        
        
    // **** if there is connectivity **** //
    }else{
        
        firstLine.hidden = YES;
        secondLine.hidden = YES;
        thirdLine.hidden = YES;
        
        userNameLabel.hidden = NO;
        passwordLabel.hidden = NO;
        emailLabel.hidden = NO;
        
        userName.hidden = NO;
        passWord.hidden = NO;
        email.hidden = NO;
        
        createAccountButton.hidden = NO;
        
        
    }
    
}




// **** user clicks done or cancel **** //
-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    if(button.tag == 0){
        
        if(!([userName.text isEqualToString:@""] || ([passWord.text isEqualToString:@""]))){
        
            // **** verifying the email address has the "@" and "." **** //
            // **** somewhere in the string **** //
            NSString *emailString = [[NSString alloc] initWithString:passWord.text];
        
            // **** checking the string to make sure its valid **** //
            if((([emailString rangeOfString:@"@"].location == NSNotFound) && ([emailString rangeOfString:@"."].location == NSNotFound)) || ([email isEqual:@""])){
            
                UIAlertView *newAlert = [[UIAlertView alloc] initWithTitle:@"Email Address Not Valid" message:@"Please enter in a valid Email Address" delegate:self cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
            
                [newAlert show];
        
            }else{
        
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
                
                
                        // **** go to userinfo screen **** //
                        UserInfo *newUserInfoScreen = [[UserInfo alloc] initWithNibName:@"UserInfo" bundle:nil];
                        [self presentViewController:newUserInfoScreen animated:TRUE completion:nil];
                
                
                        // **** something happened and it work **** //
                    }else{
                
                        NSLog(@"There was an error in creating this account");
                        NSLog(@"%@", error);
                
                    }
            
                }];
            }
            
        // **** user name and pass word fields are blank **** //
        }else{
            
            UIAlertView *newAlert = [[UIAlertView alloc] initWithTitle:@"User name and Password Invalid" message:@"Please enter your User name and Password" delegate:self cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
            
            [newAlert show];
            
        }
        
        
    
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
