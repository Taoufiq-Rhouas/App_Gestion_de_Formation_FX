const path = require("path");
const express = require("express");
const ejs = require("ejs");
const mysql = require("mysql");
const bodyParser = require("body-parser");
const app = express();

const connection = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "javafx2",
});

connection.connect(function (error) {
  if (!!error) console.log(error);
  else console.log("Database Connected!");
});

//set views file
app.set('views',path.join(__dirname,'views'));
			
//set view engine
app.set('view engine', 'ejs');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.get('/',(req, res) => {
    let sql = "SELECT * FROM formation";
    let query = connection.query(sql, (err, rows) => {
        if(err) throw err;
        res.render('user_index', {
            title : 'Liste des formations',
            formations : rows
        });
    });
});

// Server Listening
app.listen(3000, () => {
  console.log("Server is running at port 3000");
});
