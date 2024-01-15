const Sequelize = require('sequelize');
const sequelize = require('../../config/sequelize/sequelize');

const Project = sequelize.define('Project',{
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
    taskCount: {
        type: Sequelize.INTEGER,
        allowNull:true,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    }
});

module.exports = Project;
