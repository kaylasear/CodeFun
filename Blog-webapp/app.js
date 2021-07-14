//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");
const ejs = require("ejs");
const mongoose = require("mongoose");

const homeStartingContent = "Start creating posts! Go to the compose page by typing '/compost' in the end of the URL to create a blog post";
const aboutContent = "Hello! My name is Kayla and I am currently a student studying for a master's degree in Computer Science. I created this blog post website for fun! I am an adventurous traveler, active foodie, and laid-back gamer whenever I have some free time";
const contactContent = "Connect with me on linkedin: https://www.linkedin.com/in/kayla-sear-963961139/. My contact info is 111-111-1111. Feel free to message me at fakeemail@email.com";

const app = express();

app.set('view engine', 'ejs');

app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));

// connect to mongo DB
mongoose.connect("mongodb+srv://admin-kayla:test123@cluster0.ktb52.mongodb.net/blogDB", {useUnifiedTopology: true,
useNewUrlParser: true, useCreateIndex: true, useFindAndModify: false });


// create schema
const postSchema = {
  title: String,
  content: String
};

// create mongoose model based on schema
const Post = mongoose.model("Post", postSchema);



// app.get() methods

app.get("/", function(req, res){
  Post.find({}, function(err, posts){
    res.render("home", {
      startingContent: homeStartingContent,
      posts: posts});
  });
});

app.get("/about", function(req, res){
  res.render("about", {aboutContent: aboutContent});
});

app.get("/contact", function(req, res){
  res.render("contact", {contactContent: contactContent});
});

app.get("/compose", function(req, res){
  res.render("compose");
});


// post() methods

app.post("/compose", function(req, res){

  // create new post document using mongoose model
  const post = new Post({
    title: req.body.postTitle,
    content: req.body.postBody
  });

  post.save(function(err, result){
    res.redirect("/");
  });


});

app.get("/posts/:postId", function(req, res){

  const requestedPostId = req.params.postId;

  Post.findOne({_id: requestedPostId}, function(err, post){
    res.render("post", {
      title: post.title,
      content: post.content
    });
  });

});


// connect to heroku's port
let port = process.env.PORT;
if (port == null || port == "") {
  port = 3000;
}


app.listen(port, function() {
  console.log("Server has started succesfully");
});
