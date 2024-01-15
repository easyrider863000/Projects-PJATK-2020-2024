const Sequelize = require('sequelize');
const sequelize = require('../../config/sequelize/sequelize');

const Department = sequelize.define('Department',{
    _id: {
        type: Sequelize.INTEGER,
        autoIncrement: true,
        allowNull: false,
        primaryKey: true
    },
    name: {
        type: Sequelize.STRING,
        allowNull:false,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    },
    description: {
        type: Sequelize.STRING,
        allowNull:false,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    },
    emp_id: {
        type: Sequelize.INTEGER,
        allowNull:true,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    },
    prj_id: {
        type: Sequelize.INTEGER,
        allowNull:true,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    }
});

module.exports = Department;
