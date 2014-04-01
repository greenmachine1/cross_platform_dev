//
//  ViewController.m
//  User_Registration_project
//
//  Created by Cory Green on 3/31/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
    
    // **** setting the textFieldDelegate to self **** //
    [userName setDelegate:self];
    [passWord setDelegate:self];
    
    
}






// **** called when the user hits 'new account' **** //
-(IBAction)onClick:(id)sender{
    
    UIButton *button = (UIButton *)sender;
    if(button.tag == 0){
        
        
        NSLog(@"%@", userName.text);
        NSLog(@"%@", passWord.text);
        
        
        
        
    }else if(button.tag == 1){
        
        
        
        
        
    }
    
    
}


// **** called when the user hits return on the keyboard **** //
- (BOOL)textFieldShouldReturn:(UITextField *)textField{
    
    [userName resignFirstResponder];
    [passWord resignFirstResponder];
    
    return true;
}








- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
