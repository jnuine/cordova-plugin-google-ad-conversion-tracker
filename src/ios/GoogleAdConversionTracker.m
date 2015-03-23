#import "GoogleAdConversionTracker.h"
#import "ACTReporter.h"
#import <Cordova/CDV.h>

@implementation GoogleAdConversionTracker

- (void)trackConversion:(CDVInvokedUrlCommand*)command
{
    NSString *conversionId = [command.arguments objectAtIndex:0];
    NSString *trackingLabel = [command.arguments objectAtIndex:1];
    NSString *trackingValue = [command.arguments objectAtIndex:2];
    BOOL repeatable = [[command.arguments objectAtIndex:3] boolValue];

    [self.commandDelegate runInBackground:^{

      // see: https://developers.google.com/app-conversion-tracking/
      [ACTConversionReporter reportWithConversionID:conversionId label:trackingLabel value:trackingValue isRepeatable:repeatable];

      CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
      [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end
