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

#import "Reachability.h"

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
        
        reachability = [Reachability reachabilityWithHostname:@"http://www.yahoo.com"];
        
        reachability.reachableOnWWAN = YES;
        
        // **** using NSNotificationCenter **** //
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reachabilityMethod:) name:kReachabilityChangedNotification object:nil];
        
        [reachability stopNotifier];
        
        
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





// **** changes in connectivity **** //
-(void)reachabilityMethod:(NSNotification *)notify{
    
    // **** if the connectivity becomes available **** //
    // **** we need to reload the data **** //
    if(reachability.isReachable == 1){
        
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
                    
                    NSNumber *numberOfMemebersInBand = [object objectForKey:@"bandSize"];
                    
                    [userInfoArray addObject:[object objectForKey:@"bandName"]];
                    
                    [numberOfMembers addObject:numberOfMemebersInBand];
                    
                    
                    [userInfoTableView reloadData];
                }
                
            }
            
            
        }];
        
        
    }
    
    
}




-(void)viewDidAppear:(BOOL)animated{
    
    // **** if there is a connection **** //
    if(reachability.isReachable == 1){
    
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
                
                    NSNumber *numberOfMemebersInBand = [object objectForKey:@"bandSize"];
                
                    [userInfoArray addObject:[object objectForKey:@"bandName"]];
                
                    [numberOfMembers addObject:numberOfMemebersInBand];
                
                
                    [userInfoTableView reloadData];
                }
            
            }
        
        
        }];
        
    // **** if there is not **** //
    }else{
        
        
        
        
        
        
    }
    
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





// **** the height of each cell **** //
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    return 130;
}


// **** user makes a selection **** //
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    
    
    
}






// **** create new band info or logout **** //
-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    
    // **** adding band info **** //
    if(button.tag == 0){
        
        // **** if there is connectivity **** //
        if(reachability.isReachable == 1){
        
            AddBandInfo *newBandInfo = [[AddBandInfo alloc] initWithNibName:@"AddBandInfo" bundle:nil];
            [self presentViewController:newBandInfo animated:TRUE completion:nil];
        
        // **** if there is no connectivity **** //
        }else{
            
            UIAlertView *newAlert = [[UIAlertView alloc] initWithTitle:@"No Connection" message:@"You arent connected to the server, however, you can still create an entry and it will be delivered once connection is reestablished." delegate:self cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
            
            [newAlert show];
            
            AddBandInfo *newBandInfo = [[AddBandInfo alloc] initWithNibName:@"AddBandInfo" bundle:nil];
            [self presentViewController:newBandInfo animated:TRUE completion:nil];
            
        }
        
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
