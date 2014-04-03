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
        
        defaults = [NSUserDefaults standardUserDefaults];
        
        
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    if([defaults objectForKey:@"userName"] != NULL){
        
        NSLog(@"%@", [defaults objectForKey:@"userName"]);
        NSLog(@"%@", [defaults objectForKey:@"userEmail"]);
    }
    
    
    
}




// **** how many rows are in the list **** //
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return 2;
}



// **** the contents of that list **** //
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *simpleTableIdentifier = @"SimpleTableItem";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:simpleTableIdentifier];
    
    if(cell == nil){
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:simpleTableIdentifier];
    }
    
    cell.textLabel.text = @"Yep";
    
    return cell;
}





-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    
    // **** adding band info **** //
    if(button.tag == 0){
        
        
    // **** logging the person out **** //
    }else if (button.tag == 1){
        
        
        // **** logging out the user **** //
        [PFUser logOut];
        
        [defaults removeObjectForKey:@"userName"];
        [defaults removeObjectForKey:@"userEmail"];
        
        [self dismissViewControllerAnimated:TRUE completion:nil];
        
    }
    
    
}




- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
