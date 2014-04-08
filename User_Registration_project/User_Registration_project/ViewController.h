//
//  ViewController.h
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "Reachability.h"

#import <Parse/Parse.h>

@interface ViewController : UIViewController<UITextFieldDelegate>
{
    IBOutlet UITextField *userName;
    IBOutlet UITextField *passWord;
    
    IBOutlet UILabel *wifiLabel;
    IBOutlet UILabel *cellularLabel;
    
    IBOutlet UIButton *loginButton;
    IBOutlet UIButton *createAccountButton;
    
    PFUser *currentUser;
    
    NSUserDefaults *defaults;
    
    Reachability *newReachability;
    
}
@end
