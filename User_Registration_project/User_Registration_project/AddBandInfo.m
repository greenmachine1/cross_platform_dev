//
//  AddBandInfo.m
//  User_Registration_project
//
//  Created by Cory Green on 4/1/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "AddBandInfo.h"

@interface AddBandInfo ()

@end

@implementation AddBandInfo


- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        
        user = [PFUser currentUser];
        
        cameFromEdit = FALSE;
        
    }
    return self;
}





- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    
    
        [nameOfBandText setDelegate:self];
        [numberOfMemebers setDelegate:self];
    
        nameOfBandText.clearsOnBeginEditing = true;
        numberOfMemebers.clearsOnBeginEditing = true;
    
        [nameOfBandText setText:@"Name of Band"];
    
    
    
    
}


// **** passing in the PFObject and deciding where this call came from **** //
-(void)passInObject:(PFObject *)object didComeFromEdit:(BOOL)comeFromEdit{
    
    cameFromEdit = comeFromEdit;
    
    editableObject = object;
    
    if(comeFromEdit == TRUE){
    
        NSString *bandName = [object objectForKey:@"bandName"];
    
        NSNumber *bandSize = [object objectForKey:@"bandSize"];
    
        NSString *bandSizeString = [[NSString alloc] initWithFormat:@"%@", bandSize];
    
        [nameOfBandText setText:bandName];
        
        [numberOfMemebers setText:bandSizeString];

    
        NSLog(@"in here");
    }
}






// **** the return button is hit **** //
- (BOOL)textFieldShouldReturn:(UITextField *)textField{
    
    [nameOfBandText resignFirstResponder];

    return TRUE;
}





// **** done and cancel buttons **** //
-(IBAction)onClick:(id)sender{
    UIButton *button = (UIButton *)sender;
    
    
    // **** done **** //
    if(button.tag == 0){
        
        // **** if it did not come from Edit **** //
        if(cameFromEdit == 0){
        
            NSNumber *numberOfBandMemembers = [NSNumber numberWithInt:numberOfMemebers.text.intValue];
        
            PFObject *post = [PFObject objectWithClassName:@"Post"];
            post[@"bandName"] = nameOfBandText.text;
            post[@"bandSize"] = numberOfBandMemembers;
            post[@"user"] = user;
        
            
            
            // **** just in case the user looses connectivity **** //
            [post saveInBackground];
        
        
            [self dismissViewControllerAnimated:TRUE completion:nil];
            
        // **** if it came from editing band info **** //
        // **** updating the current entry **** //
        }else{
        
            NSLog(@"%@", editableObject);
            
            NSLog(@"%@", editableObject.objectId);
            
            NSNumber *numberOfBandMemembers = [NSNumber numberWithInt:numberOfMemebers.text.intValue];
            
            PFQuery *query = [PFQuery queryWithClassName:@"Post"];
            
            [query getObjectInBackgroundWithId:editableObject.objectId block:^(PFObject *object, NSError *error) {
                
                object[@"bandName"] = nameOfBandText.text;
                object[@"bandSize"] = numberOfBandMemembers;
                object[@"user"] = user;
                
                [object saveInBackground];
                
            }];
            
            [self dismissViewControllerAnimated:TRUE completion:nil];
            
        }
    // **** cancel **** //
    }else if (button.tag == 1){
        
        [self dismissViewControllerAnimated:TRUE completion:nil];
        
        
    // **** this is for the done button on the **** //
    // **** enter how many band memebers field **** //
    }else if (button.tag == 2){
        
        [numberOfMemebers resignFirstResponder];
        
    }
}






- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
