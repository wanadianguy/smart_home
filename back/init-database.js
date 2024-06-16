db = db.getSiblingDB("smart-home-controller");

db.createCollection('averages-temperatures', { capped: false });
db.createCollection('luminosities', { capped: false });
db.createCollection('movements', { capped: false });
db.createCollection('temperatures', { capped: false });
db.createCollection('sensors', { capped: false });

db.getCollection('sensors').insertMany([
    {sensorId: "sensorId1", type: "Temperature", description: ""},
    {sensorId: "sensorId2", type: "Temperature", description: ""},
]);

db.getCollection('temperatures').insertMany([
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T01:00:00"), degree: 14},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T01:00:00"), degree: 15},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T04:00:00"), degree: 13},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T04:00:00"), degree: 14},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T07:00:00"), degree: 15},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T07:00:00"), degree: 16},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T10:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T10:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T13:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T13:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T16:00:00"), degree: 18},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T16:00:00"), degree: 19},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T19:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T19:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-27T22:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-27T22:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T01:00:00"), degree: 15},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T01:00:00"), degree: 16},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T04:00:00"), degree: 14},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T04:00:00"), degree: 15},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T07:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T07:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T10:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T10:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T13:00:00"), degree: 18},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T13:00:00"), degree: 19},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T16:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T16:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T19:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T19:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-28T22:00:00"), degree: 15},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-28T22:00:00"), degree: 16},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T01:00:00"), degree: 14},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T01:00:00"), degree: 15},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T04:00:00"), degree: 13},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T04:00:00"), degree: 14},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T07:00:00"), degree: 15},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T07:00:00"), degree: 16},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T10:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T10:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T13:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T13:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T16:00:00"), degree: 18},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T16:00:00"), degree: 19},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T19:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T19:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-29T22:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-29T22:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T01:00:00"), degree: 15},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T01:00:00"), degree: 16},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T04:00:00"), degree: 14},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T04:00:00"), degree: 15},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T07:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T07:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T10:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T10:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T13:00:00"), degree: 18},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T13:00:00"), degree: 19},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T16:00:00"), degree: 17},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T16:00:00"), degree: 18},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T19:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T19:00:00"), degree: 17},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-30T22:00:00"), degree: 15},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-30T22:00:00"), degree: 16},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-31T01:00:00"), degree: 15},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-31T01:00:00"), degree: 16},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-31T04:00:00"), degree: 14},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-31T04:00:00"), degree: 15},
    {sensorId: "sensorId1", dateTime: new Date("2024-05-31T07:00:00"), degree: 16},
    {sensorId: "sensorId2", dateTime: new Date("2024-05-31T07:00:00"), degree: 17},
]);

db.getCollection('averages-temperatures').insertMany([
    {sensorId: "sensorId1", date: new Date("2024-05-27"), averageDegree: 15.25},
    {sensorId: "sensorId2", date: new Date("2024-05-27"), averageDegree: 16.25},
    {sensorId: "sensorId1", date: new Date("2024-05-28"), averageDegree: 15.25},
    {sensorId: "sensorId2", date: new Date("2024-05-28"), averageDegree: 16.25},
    {sensorId: "sensorId1", date: new Date("2024-05-29"), averageDegree: 15.25},
    {sensorId: "sensorId2", date: new Date("2024-05-29"), averageDegree: 16.25},
    {sensorId: "sensorId1", date: new Date("2024-05-30"), averageDegree: 16.0},
    {sensorId: "sensorId2", date: new Date("2024-05-30"), averageDegree: 17.0},
    {sensorId: "sensorId1", date: new Date("2024-05-31"), averageDegree: 15.25},
    {sensorId: "sensorId2", date: new Date("2024-05-31"), averageDegree: 16.25},
]);

db.getCollection('movements').insertMany([
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T00:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T00:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T03:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T03:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T06:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T06:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T09:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T09:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T12:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T12:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T15:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T15:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T18:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T18:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-27T21:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-27T21:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T00:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T00:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T03:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T03:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T06:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T06:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T09:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T09:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T12:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T12:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T15:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T15:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T18:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T18:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-28T21:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-28T21:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T00:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T00:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T03:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T03:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T06:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T06:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T09:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T09:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T12:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T12:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T15:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T15:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T18:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T18:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-29T21:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-29T21:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T00:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T00:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T03:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T03:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T06:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T06:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T09:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T09:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T12:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T12:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T15:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T15:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T18:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T18:00:00"), isTriggered: true},
    {sensorId: "Movement-esp32-002", dateTime: new Date("2024-05-30T21:00:00"), isTriggered: false},
    {sensorId: "Movement-esp32-003", dateTime: new Date("2024-05-30T21:00:00"), isTriggered: false},
]);

db.getCollection('luminosities').insertMany([
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-27T00:00:00"), percentOfLuminosity: 0.70},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T00:00:00"), percentOfLuminosity: 0.55},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-27T03:00:00"), percentOfLuminosity: 0.65},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T03:00:00"), percentOfLuminosity: 0.60},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-27T06:00:00"), percentOfLuminosity: 0.75},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T06:00:00"), percentOfLuminosity: 0.50},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-27T09:00:00"), percentOfLuminosity: 0.80},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T09:00:00"), percentOfLuminosity: 0.45},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-27T12:00:00"), percentOfLuminosity: 0.85},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T12:00:00"), percentOfLuminosity: 0.40},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T15:00:00"), percentOfLuminosity: 0.35},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T15:00:00"), percentOfLuminosity: 0.28},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-27T18:00:00"), percentOfLuminosity: 0.95},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T18:00:00"), percentOfLuminosity: 0.30},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-27T21:00:00"), percentOfLuminosity: 1.0},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-27T21:00:00"), percentOfLuminosity: 0.25},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T00:00:00"), percentOfLuminosity: 0.70},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T00:00:00"), percentOfLuminosity: 0.55},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T03:00:00"), percentOfLuminosity: 0.65},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T03:00:00"), percentOfLuminosity: 0.60},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T06:00:00"), percentOfLuminosity: 0.75},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T06:00:00"), percentOfLuminosity: 0.50},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T09:00:00"), percentOfLuminosity: 0.80},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T09:00:00"), percentOfLuminosity: 0.45},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T12:00:00"), percentOfLuminosity: 0.85},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T12:00:00"), percentOfLuminosity: 0.40},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T15:00:00"), percentOfLuminosity: 0.90},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T15:00:00"), percentOfLuminosity: 0.35},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T18:00:00"), percentOfLuminosity: 0.95},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T18:00:00"), percentOfLuminosity: 0.30},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-28T21:00:00"), percentOfLuminosity: 1.0},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-28T21:00:00"), percentOfLuminosity: 0.25},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T00:00:00"), percentOfLuminosity: 0.70},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T00:00:00"), percentOfLuminosity: 0.55},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T03:00:00"), percentOfLuminosity: 0.65},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T03:00:00"), percentOfLuminosity: 0.60},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T06:00:00"), percentOfLuminosity: 0.75},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T06:00:00"), percentOfLuminosity: 0.50},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T09:00:00"), percentOfLuminosity: 0.80},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T09:00:00"), percentOfLuminosity: 0.45},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T12:00:00"), percentOfLuminosity: 0.85},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T12:00:00"), percentOfLuminosity: 0.40},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T15:00:00"), percentOfLuminosity: 0.90},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T15:00:00"), percentOfLuminosity: 0.35},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T18:00:00"), percentOfLuminosity: 0.95},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T18:00:00"), percentOfLuminosity: 0.30},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-29T21:00:00"), percentOfLuminosity: 1.0},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-29T21:00:00"), percentOfLuminosity: 0.25},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T00:00:00"), percentOfLuminosity: 0.70},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T00:00:00"), percentOfLuminosity: 0.55},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T03:00:00"), percentOfLuminosity: 0.65},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T03:00:00"), percentOfLuminosity: 0.60},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T06:00:00"), percentOfLuminosity: 0.75},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T06:00:00"), percentOfLuminosity: 0.50},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T09:00:00"), percentOfLuminosity: 0.80},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T09:00:00"), percentOfLuminosity: 0.45},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T12:00:00"), percentOfLuminosity: 0.85},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T12:00:00"), percentOfLuminosity: 0.40},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T15:00:00"), percentOfLuminosity: 0.90},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T15:00:00"), percentOfLuminosity: 0.35},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T18:00:00"), percentOfLuminosity: 0.95},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T18:00:00"), percentOfLuminosity: 0.30},
    {sensorId: "Spectral-esp32-001", dateTime: new Date("2024-05-30T21:00:00"), percentOfLuminosity: 1.0},
    {sensorId: "Spectral-esp32-004", dateTime: new Date("2024-05-30T21:00:00"), percentOfLuminosity: 0.25},
]);