//
//  AddBandInfo.h
//  User_Registration_project
//
//  Created by Cory Green on 4/1/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <Parse/Parse.h>



@interface AddBandInfo : UIViewController<UITextFieldDelegate>
{
    
    IBOutlet UITextField *nameOfBandText;
    IBOutlet UITextField *numberOfMemebers;
    
    PFUser *user;
    
}



@end
