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
