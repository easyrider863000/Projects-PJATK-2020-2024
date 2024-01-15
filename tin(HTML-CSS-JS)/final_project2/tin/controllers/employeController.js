const EmployeRepository = require('../repository/sequelize/EmployeRepository');

exports.showEmployeList = (req,res,next)=>{
    EmployeRepository.getEmployee()
        .then(emps=>{
            res.render('pages/employee/list',{ 
                emps: emps,
                navLocation: 'emp'
            });
        });
}
exports.showAddEmployeForm = (req,res,next)=>{
    res.render('pages/employee/form',{ 
        emp: {},
        pageTitle: 'Nowy pracownik',
        formMode: 'createNew',
        btnLabel: 'Dodaj pracownika',
        formAction: '/employee/add',
        navLocation: 'emp' 
    });
}
exports.showEditEmployeForm = (req,res,next)=>{
    const empId = req.params.empId;
    EmployeRepository.getEmployeById(empId)
        .then(emp=>{
            res.render('pages/employee/form',{ 
                emp: emp,
                pageTitle: 'Edycja pracownik',
                formMode: 'edit',
                btnLabel: 'Edytuj pracownika',
                formAction: '/employee/edit',
                navLocation: 'emp' 
            });
        });
}
exports.showEmployeDetails = (req,res,next)=>{
    const empId = req.params.empId;
    EmployeRepository.getEmployeById(empId)
        .then(emp=>{
            res.render('pages/employee/form',{ 
                emp: emp,
                pageTitle: 'Szczegoly pracownika',
                formMode: 'showDetails',
                formAction: '',
                navLocation: 'emp' 
            });
        });
}

exports.addEmploye = (req,res,next)=>{
    const empData = {...req.body};
    EmployeRepository.createEmploye(empData)
        .then(result=>{
            res.redirect('/employee');
        })
        .catch(err=>{
            res.render('pages/employee/form-error',{ 
                emp: empData,
                pageTitle: 'Szczegoly pracownika',
                formMode: 'showDetails',
                formAction: '',
                navLocation: 'emp',
                validationErrors: err.errors 
            });
        });
};
exports.updateEmploye = (req,res,next)=>{
    const empId = req.body._id;
    const empData = {...req.body};
    EmployeRepository.updateEmploye(empId, empData)
        .then(result=>{
            res.redirect('/employee');
        })
        .catch(err=>{
            res.render('pages/employee/form-error',{ 
                emp: empData,
                pageTitle: 'Edycja pracownik',
                formMode: 'edit',
                btnLabel: 'Edytuj pracownika',
                formAction: '/employee/edit',
                navLocation: 'emp',
                validationErrors: err.errors 
            });
        });
};
exports.deleteEmploye = (req,res,next)=>{
    const empId = req.params.empId;
    EmployeRepository.deleteEmploye(empId)
        .then(result=>{
            res.redirect('/employee');
        });
};