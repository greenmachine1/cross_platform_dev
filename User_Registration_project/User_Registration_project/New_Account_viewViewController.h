//
//  New_Account_viewViewController.h
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface New_Account_viewViewController : UIViewController<UITextFieldDelegate>
{
    
    IBOutlet UITextField *userName;
    IBOutlet UITextField *passWord;
    IBOutlet UITextField *email;
    
    NSUserDefaults *defaults;
}
@end
