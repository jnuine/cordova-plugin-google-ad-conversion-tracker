#import <Cordova/CDV.h>

@interface GoogleAdConversionTracker : CDVPlugin

- (void)trackConversion:(CDVInvokedUrlCommand*)command;
- (void)trackRemarketingConversion:(CDVInvokedUrlCommand*)command;

@end
