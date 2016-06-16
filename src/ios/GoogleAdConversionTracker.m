#import "GoogleAdConversionTracker.h"
#import "ACTReporter.h"
#import <Cordova/CDV.h>

@implementation GoogleAdConversionTracker

- (void)trackConversion:(CDVInvokedUrlCommand*)command
{
    NSString *conversionId = [command argumentAtIndex:0];
    NSString *trackingLabel = [command argumentAtIndex:1];
    NSString *trackingValue = [command argumentAtIndex:2];
    BOOL repeatable = [[command argumentAtIndex:3] boolValue];

    [self.commandDelegate runInBackground:^{

      // see: https://developers.google.com/app-conversion-tracking/
      [ACTConversionReporter reportWithConversionID:conversionId label:trackingLabel value:trackingValue isRepeatable:repeatable];

      CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
      [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)trackRemarketingConversion:(CDVInvokedUrlCommand*)command
{
    NSString *conversionId = [command argumentAtIndex:0];
    NSDictionary* customParameters = [command argumentAtIndex:1];

    [self.commandDelegate runInBackground:^{
        [ACTRemarketingReporter reportWithConversionID:conversionId customParameters:customParameters];

        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end
