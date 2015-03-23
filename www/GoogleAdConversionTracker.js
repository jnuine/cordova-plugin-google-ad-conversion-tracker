function GoogleAdConversionTracker () {}

GoogleAdConversionTracker.prototype.trackConversion = function (conversionId, label, value, repeatable, success, fail) {
  cordova.exec(success, fail, 'GoogleAdConversionTracker', 'trackConversion', [conversionId, label, value, repeatable]);
};

module.exports = new GoogleAdConversionTracker();
