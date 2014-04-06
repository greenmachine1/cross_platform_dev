//
//  UserInfo.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "UserInfo.h"

#import "AddBandInfo.h"

#import "customCell.h"



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
        
        userInfoArray = [[NSMutableArray alloc] init];
        
        numberOfMembers = [[NSMutableArray alloc] init];
        
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
    [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
        
        [userInfoArray removeAllObjects];
        
        [numberOfMembers removeAllObjects];
        
        
        // **** an error has happened
        if(error){
            
            
        }else{
            
            for(PFObject *object in objects){
                NSLog(@"%@", object);
                
                
                [userInfoArray addObject:[object objectForKey:@"bandName"]];
                [numberOfMembers addObject:[object objectForKey:@"bandSize"]];
                
                
                
                
                [userInfoTableView reloadData];
            }
            
        }
        
        
    }];
    
}




// **** how many rows are in the list **** //
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return userInfoArray.count;
}



// **** the contents of that list **** //
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *simpleIdentifier = @"SimpleCell";
    
    customCell *cell = (customCell *)[tableView dequeueReusableCellWithIdentifier:simpleIdentifier];
    if(cell == nil){
        
        NSArray *nib = [[NSBundle mainBundle] loadNibNamed:@"customCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    
    NSString *tempBandName = [[NSString alloc] initWithFormat:@"Band Name: %@", [userInfoArray objectAtIndex:indexPath.row]];
    
    NSString *tempNumberOfMembers = [[NSString alloc ] initWithFormat:@"Number of Members: %@", [numberOfMembers objectAtIndex:indexPath.row]];
    
    cell.bandName.text = tempBandName;
    cell.numberOfMembers.text = tempNumberOfMembers;
    
    return cell;
}






- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    return 130;
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
