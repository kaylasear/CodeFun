const express = require("express");
const https = require("https");
const bodyParser = require("body-parser");

const app = express();

app.use(bodyParser.urlencoded({extended: true}));


app.get("/", function(req, res) {
    res.sendFile(__dirname + "/index.html");
    
})

app.post("/", function(req, res){

    const query = req.body.cityName;
    const key = "0034bf9bf0b021228208b8268310b8ea";
    const unit = "imperial";
    const url = "https://api.openweathermap.org/data/2.5/weather?q=" + query + "&appid=" + key +"&units=" + unit;

    https.get(url, function(response){
        //console.log(response.statusCode);

        response.on("data", function(data){

            // saving data from weather url into constant variables
            const weatherData = JSON.parse(data);
            const temp = weatherData.main.temp;
            const weatherDescription = weatherData.weather[0].description;
            const icon = weatherData.weather[0].icon;
            const imageURL = "http://openweathermap.org/img/wn/" + icon +"@2x.png"

            // displaying the weather info using write() and html
            res.write("<p>The weather is currenty " + weatherDescription + "<p>");
            res.write("<h1>The temperature in " + query+ " is " + temp + " degress F</h1>");
            res.write("<img src=" + imageURL + ">");

            // can only have 1 send request
            res.send();


        });
    });

})




app.listen(3000, function() {
    console.log("Server is running on port 3000");
})