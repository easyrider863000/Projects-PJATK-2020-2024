const ProjectRepository = require('../repository/sequelize/ProjectRepository');

exports.showProjectList = (req,res,next)=>{
    ProjectRepository.getProjects()
        .then(prjs=>{
            res.render('pages/projects/list',{ 
                prjs: prjs,
                navLocation: 'prj'
            });
        });
}
exports.showAddProjectForm = (req,res,next)=>{
    res.render('pages/projects/form',{ 
        prj: {},
        pageTitle: 'Nowy projekt',
        formMode: 'createNew',
        btnLabel: 'Dodaj projekt',
        formAction: '/projects/add',
        navLocation: 'prj',
        validationErrors: {}
    });
}
exports.showEditProjectForm = (req,res,next)=>{
    const prjId = req.params.prjId;
    ProjectRepository.getProjectById(prjId)
        .then(prj=>{
            res.render('pages/projects/form',{ 
                prj: prj,
                pageTitle: 'Edycja projektu',
                formMode: 'edit',
                btnLabel: 'Edytuj projekt',
                formAction: '/projects/edit',
                navLocation: 'prj' 
            });
        });
}
exports.showProjectDetails = (req,res,next)=>{
    const prjId = req.params.prjId;
    ProjectRepository.getProjectById(prjId)
        .then(prj=>{
            res.render('pages/projects/form',{ 
                prj: prj,
                pageTitle: 'Szczegoly projektu',
                formMode: 'showDetails',
                formAction: '',
                navLocation: 'prj' 
            });
        });
}

exports.addProject = (req,res,next)=>{
    const prjData = {...req.body};
    ProjectRepository.createProject(prjData)
        .then(result=>{
            res.redirect('/projects');
        })
        .catch(err=>{
            res.render('pages/projects/form-error',{
                prj: prjData,
                pageTitle: 'Nowy projekt',
                formMode: 'createNew',
                btnLabel: 'Dodaj projekt',
                formAction: '/projects/add',
                navLocation: 'prj',
                validationErrors: err.errors 
            });
        });
};
exports.updateProject = (req,res,next)=>{
    const prjId = req.body._id;
    const prjData = {...req.body};
    ProjectRepository.updateProject(prjId, prjData)
        .then(result=>{
            res.redirect('/projects');
        })
        .catch(err=>{
            res.render('pages/projects/form-error',{
                prj: prjData,
                pageTitle: 'Edycja projektu',
                formMode: 'edit',
                btnLabel: 'Edytuj projekt',
                formAction: '/projects/edit',
                navLocation: 'prj',
                validationErrors: err.errors
            });
        });
};
exports.deleteProject = (req,res,next)=>{
    const prjId = req.params.prjId;
    ProjectRepository.deleteProject(prjId)
        .then(result=>{
            res.redirect('/projects');
        });
};