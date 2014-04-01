//
//  UserInfo.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "UserInfo.h"

#import <Parse/Parse.h>

@interface UserInfo ()

@end

@implementation UserInfo

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
}

-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    if(button.tag == 0){
        
        // **** logging out the user **** //
        [PFUser logOut];
        
        [self dismissViewControllerAnimated:TRUE completion:nil];
        
        
    }else if (button.tag == 1){
        
        
    }
    
    
}




- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
