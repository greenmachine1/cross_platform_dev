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
        
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    
    [nameOfBandText setDelegate:self];
    [numberOfMemebers setDelegate:self];
}



// **** the return button is hit **** //
- (BOOL)textFieldShouldReturn:(UITextField *)textField{
    
    [nameOfBandText resignFirstResponder];
    [numberOfMemebers resignFirstResponder];
    
    
    return TRUE;
}



// **** done and cancel buttons **** //
-(IBAction)onClick:(id)sender{
    UIButton *button = (UIButton *)sender;
    
    
    // **** done **** //
    if(button.tag == 0){
        
        
        
        [self dismissViewControllerAnimated:TRUE completion:nil];
        
    // **** cancel **** //
    }else if (button.tag == 1){
        
        
        
        [self dismissViewControllerAnimated:TRUE completion:nil];
        
    }
    
    
}






- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
