var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var employeRouter = require('./routes/employeRoute');
var departmentRouter = require('./routes/departmentRoute');
var projectRouter = require('./routes/projectRoute');

const empApiRouter = require('./routes/api/EmployeApiRoute');
const depApiRouter = require('./routes/api/DepartmentApiRoute');
const prjApiRouter = require('./routes/api/ProjectApiRoute');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);

app.use('/employee', employeRouter);
app.use('/departments', departmentRouter);
app.use('/projects', projectRouter);

app.use('/api/employee',empApiRouter);
app.use('/api/departments',depApiRouter);
app.use('/api/projects',prjApiRouter);

const sequelizeInit = require('./config/sequelize/init');
sequelizeInit().catch(err =>{
  console.log(err);
});

// catch 404 and forward to error handler
/*app.use(function(req, res, next) {
  next(createError(404));
});*/

// error handler
/*app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});*/

module.exports = app;
