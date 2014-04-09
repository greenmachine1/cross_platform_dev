//
//  UserInfo.h
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <Parse/Parse.h>

#import "Reachability.h"

typedef void (^PassedInInfo)(PFObject *, int);
@interface UserInfo : UIViewController<UITableViewDataSource, UITableViewDelegate, UIAlertViewDelegate>
{
    
    IBOutlet UITableView *userInfoTableView;
    
    NSUserDefaults *defaults;
    
    PFQuery *query;
    
    PFUser *user;
    
    NSMutableArray *userInfoArray;
    
    NSMutableArray *numberOfMembers;
    
    NSMutableArray *idsOfBands;
    
    Reachability *reachability;
    
    int selectedIndex;
    
    PassedInInfo passedInInfo;
    
}

@property (nonatomic, strong)PassedInInfo passedInInfo;

@end
