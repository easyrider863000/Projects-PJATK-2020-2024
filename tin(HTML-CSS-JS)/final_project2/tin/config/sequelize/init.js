const sequelize = require('./sequelize');

const Employe = require('../../model/sequelize/Employe');
const Department = require('../../model/sequelize/Department');
const Project = require('../../model/sequelize/Project');

module.exports = () => {
    Employe.hasMany(Department, {as: 'departments', foreignKey: {name: 'emp_id', allowNull: false}, constraints: true, onDelete: 'CASCADE' });
    Department.belongsTo(Employe, {as: 'employee', foreignKey: {name: 'emp_id', allowNull: false} } );
    Project.hasMany(Department, {as: 'departments', foreignKey: {name: 'prj_id', allowNull: false}, constraints: true, onDelete: 'CASCADE' });
    Department.belongsTo(Project, {as: 'projects', foreignKey: {name: 'prj_id', allowNull: false } });

    let allEmps, allPrjs;
    return sequelize
        .sync({force: true})
        .then( () => {
            return Employe.findAll();
        })
        .then (emps => {
            if ( !emps || emps.length == 0 ) {
                return Employe.bulkCreate ([
                    {firstName: 'Jan', secondName: 'Kowalski', email: 'jan.kowalski@acme.com',phoneNumber:'789756123',birthdate:null},
                    {firstName: 'Adam', secondName: 'Zieliński', email: 'adam.zielinski@acme.com',phoneNumber:'789456223',birthdate:null},
                    {firstName: 'Marian', secondName: 'Nowak', email: 'marian.nowak@acme.com',phoneNumber:'789416123',birthdate:null},
                ])
                .then( () => {
                    return Employe.findAll();
                });
            } else {
                return emps;
            }
        })
        .then(emps => {
            allEmps = emps;
            return Project.findAll();
        })
        .then( prjs => {
            if(!prjs || prjs.length == 0 ) {
                return Project.bulkCreate ([
                    { name: 'HR', description: 'sfhgdjhfkjghl gfcvghv tytf gdyjtrde rwesht', taskCount:5 },
                    { name: 'Sales', description: 'sfhgdjh kjij jh hfkjghl gfcvghv tytf gdyjtrde rwesht', taskCount:1 }
                ])
                .then( () => {
                    return Employe.findAll();
                });
            } else {
                return prjs;
            }
        })
        .then( prjs => {
            allPrjs = prjs;
            return Department.findAll();
        })
        .then( depts => {
            if (!depts || depts.length == 0 ) {
                return Department.bulkCreate ([
                    {emp_id: allEmps[0]._id, prj_id: allPrjs[0]._id, name: "prj1", description: 'dsdg hfd tyer reew wqwwret ty'},
                    {emp_id: allEmps[1]._id, prj_id: allPrjs[0]._id, name: "prj2", description: 'dsgfdg hfd tyer reiw wret ty'},
                    {emp_id: allEmps[0]._id, prj_id: allPrjs[1]._id, name: "prj3", description: 'dg hfd tyer reew wkkret ty'}
                ]);
            } else {
                return depts;
            }
        });
};
