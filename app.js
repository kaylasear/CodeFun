//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const _ = require("lodash");


const app = express();

app.set('view engine', 'ejs');

app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));

// connect to mongo cloud server, get rid of deprecated warnings
mongoose.connect("mongodb+srv://admin-kayla:Test123@cluster0.ktb52.mongodb.net/todolistDB", {useUnifiedTopology: true,
useNewUrlParser: true, useCreateIndex: true, useFindAndModify: false });

// create a schemea
const itemsSchema = {
  name: String
};


// create Mongoose model based on the schema
const Item = mongoose.model("Item", itemsSchema);


// create documents
const item1 = new Item({
  name: "Welcome to your todolist!",
});

const item2 = new Item ({
  name: "Hit the + button to add a new item"
});

const item3 = new Item({
  name: "<--- Hit this to delete an item"
});


const defaultItems = [item1, item2, item3];

  //creating a new schema, it's going to have an array of itemsSchema
  const listSchema = {
    name: String,
    items: [itemsSchema]
  };

  const List = mongoose.model("List", listSchema);



// Deleting item by id from the collection
// Item.deleteOne({_id: "60c836df5fd8a912f4cbb846"}, function(err){
//   if (err) {
//     console.log(err);
//   } else {
//     console.log("Successfully deleted the defualt items in todolist DB");
//   }
// });




app.get("/", function(req, res) {


  // find everything in items collection
  Item.find({}, function(err, foundItems){

    // if it's emtpy, then add the default items
    if (foundItems.length === 0) {
      Item.insertMany(defaultItems, function(err){
      if (err) {
        console.log(err);
      } else {
        console.log("Successfully added the default items in todolist DB");
      }
      res.redirect("/"); // redirect to the home page
    });
  } else {
    res.render("list", {listTitle: "Today", newListItems: foundItems});
  }
  
  });

});


app.get("/:customListName", function(req, res){
  const customListName = _.capitalize(req.params.customListName);

  List.findOne({name: customListName}, function(err, foundList){
    if (!err){
      if (!foundList){
        // create a new list
        const list = new List({
          name: customListName,
          items: defaultItems
        });
      
        list.save(function(err, result){
          res.redirect("/" + customListName);
        });
    
      } else {
        // show an existing list
        res.render("list", {listTitle: foundList.name, newListItems: foundList.items});
      }
    }
  });



});


app.post("/", function(req, res){

  const itemName = req.body.newItem;
  const listName = req.body.list;

  // create a new item document

  const item = new Item({
    name: itemName
  });

  if (listName === "Today") {
    // save the item into the today collection
    item.save(function(err, result){
      res.redirect("/");
    });

  } else {
    // find the custom list and save the item into that collection
    List.findOne({name: listName}, function(err, foundList){
      foundList.items.push(item);
      foundList.save(function(err, result){
        res.redirect("/" + listName);
      });

    });
  }


});


app.post("/delete", function(req, res){
  const checkedItemID = req.body.checkbox;
  const listName = req.body.listName;

  if (listName === "Today") {
    Item.findByIdAndRemove(checkedItemID, function(err){
      if (!err) {
        console.log("Sucessfully deleted the checked item");
        res.redirect("/");
      } 
    });
  } else {
    // find the item by id in the items array and remove it
    List.findOneAndUpdate(
      {name: listName},
      {$pull: {items: {_id: checkedItemID}}},
      function(err, foundList){
        if (!err){
          res.redirect("/" + listName);
        }
      });
  }


});


app.get("/about", function(req, res){
  res.render("about");
});

app.listen(3000, function() {
  console.log("Server started on port 3000");
});
