//
//  customCell.h
//  User_Registration_project
//
//  Created by Cory Green on 4/3/14.
//  Copyright (c) 2014 Cory Green. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface customCell : UITableViewCell
{
    IBOutlet UILabel *bandName;
    IBOutlet UILabel *numberOfMembers;
    
}

@property (nonatomic, strong)UILabel *bandName;
@property (nonatomic, strong)UILabel *numberOfMembers;
@end
