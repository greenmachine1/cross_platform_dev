//
//  AppDelegate.h
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "Reachability.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate>
{
    Reachability *newReachability;
}

@property (strong, nonatomic) UIWindow *window;

@end
