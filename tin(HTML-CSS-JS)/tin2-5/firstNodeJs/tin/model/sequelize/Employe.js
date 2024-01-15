const Sequelize = require('sequelize');
const sequelize = require('../../config/sequelize/sequelize');

const Employe = sequelize.define('Employe',{
    _id: {
        type: Sequelize.INTEGER,
        autoIncrement: true,
        allowNull: false,
        primaryKey: true
    },
    firstName: {
        type: Sequelize.STRING,
        allowNull:false,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    },
    secondName: {
        type: Sequelize.STRING,
        allowNull:false,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    },
    email: {
        type: Sequelize.STRING,
        allowNull:false,
        unique:true,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            },
            isEmail:{
                msg:"Pole powinno zawierac prawidlowy adres email"
            }
        }
    },
    phoneNumber: {
        type: Sequelize.STRING,
        allowNull:false,
        unique:true,
        validate:{
            notEmpty:{
                msg:"Pole jest wymagane"
            }
        }
    },
    birthdate: {
        type: Sequelize.DATE,
        allowNull:true
    }
});

module.exports = Employe;
