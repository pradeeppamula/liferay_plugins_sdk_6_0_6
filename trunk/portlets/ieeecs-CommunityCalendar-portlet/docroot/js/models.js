
var EventItemModel = Backbone.Model.extend({
    defaults: {
        "urlTitle": "",
        "dateTimeS": 0,
        "eventEndDateTimeMS": 0,
        "imagePath": null,
        "allDay": false,
        "dateTime": "",
        "type": "",
        "url": "",
        "id": 0,
        "eventStartDateTime": "",
        "groupId": 0,
        "eventStartDateTimeS": 0,
        "eventEndDateTime": "",
        "title": "",
        "dateTimeMS": 0,
        "start": 0,
        "description": "",
        "subType": "",
        "eventStartDateTimeMS": 0,
        "target": null,
        "channel": "",
        "eventEndDateTimeS": 0,
        "end": 0,
        "eventMonth":0,
        "eventYear":0,
        "eventLocation":""
    }
}); 

var EventItemCollection = Backbone.Collection.extend({
    model: EventItemModel
}); 