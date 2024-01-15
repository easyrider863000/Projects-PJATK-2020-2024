const Sequelize = require('sequelize');

const Employe = require("../../model/sequelize/Employe");
const Project = require("../../model/sequelize/Project");
const Department = require("../../model/sequelize/Department");

exports.getDepartments = () =>{
    return Department.findAll({include: [
        {
            model: Employe,
            as: 'employee'
        },
        {
            model: Project,
            as: 'projects'
        }
    ]});
};

exports.getDepartmentById = (departmentId) =>{
    return Department.findByPk(departmentId, {include: [
        {
            model: Employe,
            as: 'employee'
        },
        {
            model: Project,
            as: 'projects'
        }
    ]});
};

exports.createDepartment = (data) =>{
    console.log(JSON.stringify(data));

    return Department.create({
        name: data.name,
        description: data.description,
        emp_id: data.emp_id,
        prj_id: data.prj_id
    });
};

exports.updateDepartment = (departmentId, data) =>{
    return Department.update(data, {where: {_id: departmentId}});
};

exports.deleteDepartment = (departmentId) =>{
    return Department.destroy({
        where: {_id: departmentId}
    });
};

exports.deleteManyDepartments = (departmentIds) =>{
    return Department.find({ _id: { [Sequelize.Op.in]: departmentIds }});
};