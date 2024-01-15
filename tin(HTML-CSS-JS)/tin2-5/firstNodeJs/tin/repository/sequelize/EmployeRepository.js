const Employe = require("../../model/sequelize/Employe");
const Project = require("../../model/sequelize/Project");
const Department = require("../../model/sequelize/Department");

exports.getEmployee = () =>{
    return Employe.findAll();
}

exports.getEmployeById = (empId) =>{
    return Employe.findByPk(empId,
        {
            include: [{
                model: Department,
                as: 'departments',
                include: [{
                    model: Project,
                    as: 'projects'
                }]
            }]
        });
};

exports.createEmploye = (newEmpData) =>{
    return Employe.create({
        firstName: newEmpData.firstName,
        secondName: newEmpData.secondName,
        email: newEmpData.email,
        phoneNumber: newEmpData.phoneNumber,
        birthdate: newEmpData.birthdate
    });
};

exports.updateEmploye = (empId, empData) =>{
    return Employe.update(empData, {where: {_id: empId}});
};

exports.deleteEmploye = (empId) =>{
    return Employe.destroy({
        where: {_id: empId}
    });
};