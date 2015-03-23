#import <Cordova/CDV.h>

@interface GoogleAdConversionTracker : CDVPlugin

- (void)trackConversion:(CDVInvokedUrlCommand*)command;

@end
