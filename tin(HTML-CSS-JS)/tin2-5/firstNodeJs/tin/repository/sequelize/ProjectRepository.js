const Employe = require("../../model/sequelize/Employe");
const Project = require("../../model/sequelize/Project");
const Department = require("../../model/sequelize/Department");

exports.getProjects = () =>{
    return Project.findAll();
}

exports.getProjectById = (prjId) =>{
    return Project.findByPk(prjId,
        {
            include: [{
                model: Department,
                as: 'departments',
                include: [{
                    model: Employe,
                    as: 'employee'
                }]
            }]
        });
};

exports.createProject = (newPrjData) =>{
    return Project.create({
        name: newPrjData.name,
        description: newPrjData.description,
        taskCount: newPrjData.taskCount
    });
};

exports.updateProject = (prjId, prjData) =>{
    return Project.update(prjData, {where: {_id: prjId}});
};

exports.deleteProject = (prjId) =>{
    return Project.destroy({
        where: {_id: prjId}
    });
};