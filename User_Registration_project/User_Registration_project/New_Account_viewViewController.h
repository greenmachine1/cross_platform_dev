//
//  New_Account_viewViewController.h
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "Reachability.h"

@interface New_Account_viewViewController : UIViewController<UITextFieldDelegate>
{
    
    IBOutlet UITextField *userName;
    IBOutlet UITextField *passWord;
    IBOutlet UITextField *email;
    
    IBOutlet UILabel *firstLine;
    IBOutlet UILabel *secondLine;
    IBOutlet UILabel *thirdLine;
    
    IBOutlet UIButton *createAccountButton;
    IBOutlet UIButton *cancelButton;
    
    NSUserDefaults *defaults;
    
    Reachability *reachability;
}
@end
