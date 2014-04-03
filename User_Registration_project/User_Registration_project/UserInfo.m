//
//  UserInfo.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "UserInfo.h"

#import "AddBandInfo.h"

#import <Parse/Parse.h>

@interface UserInfo ()

@end

@implementation UserInfo

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        
        defaults = [NSUserDefaults standardUserDefaults];
        
        user = [PFUser currentUser];
        
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

-(void)viewDidAppear:(BOOL)animated{
    
    query = [PFQuery queryWithClassName:@"Post"];
    [query whereKey:@"user" equalTo:user];
    
    userInfoArray = [query findObjects];
    
    
    if(userInfoArray != nil){
        
        NSLog(@"%@", userInfoArray);
        NSLog(@"%lu", (unsigned long)[userInfoArray count]);
        
        
        [userInfoTableView reloadData];
    }
    
}




// **** how many rows are in the list **** //
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return userInfoArray.count;
}



// **** the contents of that list **** //
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    
    
    static NSString *simpleTableIdentifier = @"SimpleTableItem";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:simpleTableIdentifier];
    
    if(cell == nil){
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:simpleTableIdentifier];
    }
    
    
    if(userInfoArray != nil){
        
        NSString *tempString = [[NSString alloc] initWithFormat:@"%@", [userInfoArray objectAtIndex:indexPath.row]];
        cell.textLabel.text = tempString;
        
    }else{
        cell.textLabel.text = @"";
    }
    return cell;
    
}





-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    
    // **** adding band info **** //
    if(button.tag == 0){
        
        AddBandInfo *newBandInfo = [[AddBandInfo alloc] initWithNibName:@"AddBandInfo" bundle:nil];
        [self presentViewController:newBandInfo animated:TRUE completion:nil];
        
        
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
