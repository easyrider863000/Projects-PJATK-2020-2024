const express = require('express');
const router = express.Router();

const employeController = require('../controllers/employeController');

router.get('/',employeController.showEmployeList);
router.get('/add',employeController.showAddEmployeForm);
router.get('/edit/:empId',employeController.showEditEmployeForm);
router.get('/details/:empId',employeController.showEmployeDetails);

router.post('/add',employeController.addEmploye);
router.post('/edit',employeController.updateEmploye);
router.get('/delete/:empId',employeController.deleteEmploye);

module.exports = router;