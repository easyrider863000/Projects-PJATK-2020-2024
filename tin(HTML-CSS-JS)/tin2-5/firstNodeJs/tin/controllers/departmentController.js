const DepartmentRepository = require('../repository/sequelize/DepartmentRepository');
const EmployeRepository = require('../repository/sequelize/EmployeRepository');
const ProjectRepository = require('../repository/sequelize/ProjectRepository');

exports.showDepartmentList = (req,res,next)=>{
    DepartmentRepository.getDepartments()
        .then(deps=>{
            res.render('pages/departments/list',{ 
                deps: deps,
                navLocation: 'dep'
            });
        });
}
exports.showAddDepartmentForm = (req,res,next)=>{
    let allEmps, allPrjs;
    EmployeRepository.getEmployee()
        .then(emps=>{
            allEmps = emps;
            return ProjectRepository.getProjects();
        })
        .then(prjs=>{
            allPrjs = prjs;
            res.render('pages/departments/form',{ 
                dep: {},
                allEmps: allEmps,
                allPrjs: allPrjs,
                pageTitle: 'Nowy department',
                formMode: 'createNew',
                btnLabel: 'Dodaj department',
                formAction: '/departments/add',
                navLocation: 'dep'
            });
        });
}
exports.showEditDepartmentForm = (req,res,next)=>{
    const depId = req.params.depId;
    let allEmps, allPrjs;
    EmployeRepository.getEmployee()
        .then(emps=>{
            allEmps = emps;
            return ProjectRepository.getProjects();
        })
        .then(prjs=>{
            allPrjs = prjs;
            return DepartmentRepository.getDepartmentById(depId)
                .then(dep=>{
                res.render('pages/departments/form',{ 
                    dep: dep,
                    allEmps: allEmps,
                    allPrjs: allPrjs,
                    pageTitle: 'Edycja departmentu',
                    formMode: 'edit',
                    btnLabel: 'Edytuj department',
                    formAction: '/departments/edit',
                    navLocation: 'dep' 
                });
            });
        });
}
exports.showDepartmentDetails = (req,res,next)=>{
    const depId = req.params.depId;
    let allEmps, allPrjs;
    EmployeRepository.getEmployee()
        .then(emps=>{
            allEmps = emps;
            return ProjectRepository.getProjects();
        })
        .then(prjs=>{
            allPrjs = prjs;
            return DepartmentRepository.getDepartmentById(depId)
                .then(dep=>{
                    res.render('pages/departments/form',{ 
                        dep: dep,
                        allEmps: allEmps,
                        allPrjs: allPrjs,
                        pageTitle: 'Szczegoly departmentu',
                        formMode: 'showDetails',
                        formAction: '',
                        navLocation: 'dep'
                });
            });
        });
}

exports.addDepartment = (req,res,next)=>{
    const depData = {...req.body};
    DepartmentRepository.createDepartment(depData)
        .then(result=>{
            res.redirect('/departments');
        })
        .catch(err=>{
            let allEmps, allPrjs;
            EmployeRepository.getEmployee()
                .then(emps=>{
                    allEmps = emps;
                    return ProjectRepository.getProjects();
                })
                .then(prjs=>{
                    allPrjs = prjs;
                    res.render('pages/departments/form-error',{ 
                        dep: depData,
                        allEmps: allEmps,
                        allPrjs: allPrjs,
                        pageTitle: 'Nowy department',
                        formMode: 'createNew',
                        btnLabel: 'Dodaj department',
                        formAction: '/departments/add',
                        navLocation: 'dep',
                        validationErrors: err.errors
                    });
                });
        });
};
exports.updateDepartment = (req,res,next)=>{
    const depId = req.body._id;
    const depData = {...req.body};
    DepartmentRepository.updateDepartment(depId, depData)
        .then(result=>{
            res.redirect('/departments');
        })
        .catch(err=>{
            let allEmps, allPrjs;
            EmployeRepository.getEmployee()
                .then(emps=>{
                    allEmps = emps;
                    return ProjectRepository.getProjects();
                })
                .then(prjs=>{
                    allPrjs = prjs;
                    console.log(depData);
                    res.render('pages/departments/form-error',{ 
                        dep: depData,
                        allEmps: allEmps,
                        allPrjs: allPrjs,
                        pageTitle: 'Edycja departmentu',
                        formMode: 'edit',
                        btnLabel: 'Edytuj department',
                        formAction: '/departments/edit',
                        navLocation: 'dep',
                        validationErrors: err.errors
                    });
                });
        });
};
exports.deleteDepartment = (req,res,next)=>{
    const depId = req.params.depId;
    DepartmentRepository.deleteDepartment(depId)
        .then(result=>{
            res.redirect('/departments');
        });
};