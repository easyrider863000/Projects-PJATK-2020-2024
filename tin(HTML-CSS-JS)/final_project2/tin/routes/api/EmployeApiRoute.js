const express = require('express');
const router = express.Router();

const empApiController = require('../../api/EmployeAPI');

router.get('/', empApiController.getEmployee);
router.get('/:empId', empApiController.getEmployeById);
router.post('/', empApiController.createEmploye);
router.put('/:empId', empApiController.updateEmploye);
router.delete('/:empId', empApiController.deleteEmploye);

module.exports = router;