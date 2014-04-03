//
//  UserInfo.h
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <Parse/Parse.h>

@interface UserInfo : UIViewController<UITableViewDataSource, UITableViewDelegate>
{
    
    IBOutlet UITableView *userInfoTableView;
    
    NSUserDefaults *defaults;
    
    PFQuery *query;
    
    PFUser *user;
    
    NSMutableArray *userInfoArray;
    
    NSMutableArray *numberOfMembers;
    
}
@end
