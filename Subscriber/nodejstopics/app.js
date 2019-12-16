var express = require("express"); //Hier wird express eingebunden
var app = express(); //Hier wird die express Variable initialisiert. Mit dieser variable wirst du fast alles vom Server schreiben
var fs = require('fs');

app.use(express);
app.use(express.json);

var dataFromMqtt = {
    table: []
};
//mit MqTT:
var jsonDataFromMqtt;
var mqtt = require('mqtt');
var client  = mqtt.connect({ host: 'docker.htl-wels.at', username: 'bhit5', password: 'niceTime'});
//const topicsMqtt = ["topic1", "topic2", "topic3"];


app.get('/', function(req, res) {
    res.send('hello world');
});
app.listen(5000);

client.on('connect', function () {
    client.subscribe(topic);

    setInterval(function() {
        client.publish(topic, Date.now().toString());
        console.log('hello');
    }, 3000);
});
client.subscribe('bhit5/topic1');
client.subscribe('bhit5/topic2');
client.subscribe('bhit5/topic3');
check = [0, 0, 0];

client.on('message', function (topic, message, packet) {
    // message is Buffer

    if (topic === 'bhit5/topic1') {
        console.log("The message is: " + topic);
        console.log("The message is: " + message);
        dataFromMqtt.table.push(message.toString());
        check[0] = 1;
    }

    if (topic === 'bhit5/topic2') {
        console.log("The message is: " + topic);
        console.log("The message is: " + message);
        dataFromMqtt.table.push(message.toString());
        check[1] = 1;
    }

    if (topic === "bhit5/topic3") {
        console.log("The message is: " + topic);
        console.log("The message is: " + message);
        dataFromMqtt.table.push(message.toString());
        check[2] = 1;
    }
    if (check[0] === 1 && check[1] === 1 && check[2] === 1){
        jsonDataFromMqtt = JSON.stringify(dataFromMqtt);
        fs.writeFileSync('mqttPenzinger.json', jsonDataFromMqtt, function(err) {
                if (err) throw err;
                console.log('complete');
            }
        );
        for (let i = 0; i < check.length; i++) {
            check[i] = 0;
        }
    }
});


/*app.listen(app.get('port'), function() { //Hier wird der Server gestartet
    console.log("Node app is running at localhost:" + app.get('port')); //Kurze Konsolenausgabe um zu sehen ob der Server Läuft
});

module.exports = app;*/

/*
//app.use('/', indexRouter);
//app.use('/users', usersRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.json({ error: err })
});


*/
//sagt server er soll ds verwenden
// express.json sagt server wie er mit json arbeitet

//var indexRouter = require('./routes/index');
//var usersRouter = require('./routes/users');
/*
var data={
  "oil-temperature": 7,
  "unit": "F",
  "oil-pressure": 1,
  "viscosity": 1,
  "temperature": 1,
  "latitude": 1,
  "longitude": 1
}



function random(low, high) {
  return Math.random() * (high - low) + low
}

app.get("/oilstats", function(req, res){
  data["oil-temperature"]=random(60,70);
  data["oil-pressure"]=random(0,15);
  data.viscosity=random(5,15);
  data.temperature=random(50,100);
  data.latitude=random(0,100);
  data.longitude=random(0,100);
  res.send(data);
});
*/
