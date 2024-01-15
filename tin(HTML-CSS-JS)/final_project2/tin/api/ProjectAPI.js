const ProjectRepository = require('../repository/sequelize/ProjectRepository');

exports.getProjects = (req, res, next) => {
    ProjectRepository.getProjects()
        .then(prjs => {
            res.status (200).json (prjs);
        })
        .catch (err => {
            console.log(err);
        });
};

exports.getProjectById = (req, res, next) => {
    const prjId = req.params.prjId;
    ProjectRepository.getProjectById(prjId)
        .then (prj => {
            if (!prj) {
                res.status(404).json({
                    message: 'Project with id: '+prjId+' not found'
                })
            } else {
                res.status(200).json(prj);
            }
        });
};

exports.createProject = (req, res, next) => {
    ProjectRepository.createProject(req.body)
        .then(newObj => {
            res.status (201).json (newObj);
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};

exports.updateProject = (req, res, next) => {
    const prjId = req.params.prjId;
    ProjectRepository.updateProject (prjId, req.body)
        .then (result => {
            res.status(200).json ({message: 'Project updated!', prj: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next (err);
        });
};

exports.deleteProject = (req, res, next) => {
    const prjId = req.params.prjId;
    ProjectRepository.deleteProject(prjId)
        .then(result => {
            res.status(200).json({message: 'Removed project', prj: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};