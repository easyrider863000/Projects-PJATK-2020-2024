const EmployeRepository = require('../repository/sequelize/EmployeRepository');

exports.getEmployee = (req, res, next) => {
    EmployeRepository.getEmployee()
        .then(emps => {
            res.status (200).json (emps);
        })
        .catch (err => {
            console.log(err);
        });
};

exports.getEmployeById = (req, res, next) => {
    const empId = req.params.empId;
    EmployeRepository.getEmployeById(empId)
        .then (emp => {
            if (!emp) {
                res.status(404).json({
                    message: 'Employe with id: '+empId+' not found'
                })
            } else {
                res.status(200).json(emp);
            }
        });
};

exports.createEmploye = (req, res, next) => {
    EmployeRepository.createEmploye(req.body)
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

exports.updateEmploye = (req, res, next) => {
    const empId = req.params.empId;
    EmployeRepository.updateEmploye (empId, req.body)
        .then (result => {
            res.status(200).json ({message: 'Employe updated!', emp: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next (err);
        });
};

exports.deleteEmploye = (req, res, next) => {
    const empId = req.params.empId;
    EmployeRepository.deleteEmploye(empId)
        .then(result => {
            res.status(200).json({message: 'Removed employe', emp: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};