function GoogleAdConversionTracker () {}

GoogleAdConversionTracker.prototype.trackConversion = function (conversionId, label, value, repeatable, success, fail) {
  cordova.exec(success, fail, 'GoogleAdConversionTracker', 'trackConversion', [conversionId, label, value, repeatable]);
};

GoogleAdConversionTracker.prototype.trackRemarketingConversion = function(conversionId, params, success, fail) {
  cordova.exec(success, fail, 'GoogleAdConversionTracker', 'trackRemarketingConversion', [conversionId, params]);
};

module.exports = new GoogleAdConversionTracker();
