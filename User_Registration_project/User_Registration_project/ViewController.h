//
//  ViewController.h
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <Parse/Parse.h>

@interface ViewController : UIViewController<UITextFieldDelegate>
{
    IBOutlet UITextField *userName;
    IBOutlet UITextField *passWord;
    
    PFUser *currentUser;
    
    NSUserDefaults *defaults;
    
}
@end
