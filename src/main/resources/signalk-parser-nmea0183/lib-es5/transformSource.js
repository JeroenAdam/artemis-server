'use strict';

/**
 * transformSource.js
 *
 * Checks if the "source" key in each update is an object,
 * creates the object if it's a string.
 *
 * @param "data": signalk delta object
 */

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

module.exports = function transformSource(data, sentence, talker) {
  if ((typeof data === 'undefined' ? 'undefined' : _typeof(data)) !== 'object' || data === null) {
    return data;
  }

  if (!Array.isArray(data.updates)) {
    return data;
  }

  data.updates = data.updates.map(function (update) {
    if (_typeof(update.source) === 'object' && update.source !== null) {
      return update;
    }

    var _source = update.source;
    var tagSentence = _source.split(':')[1];
    var tagTalker = _source.split(':')[0];

    if (talker === 'nmea0183') {
      talker = 'SK';
    }

    update.source = {
      sentence: tagSentence || sentence,
      talker: tagTalker || talker,
      type: 'NMEA0183'
    };

    return update;
  });

  return data;
};