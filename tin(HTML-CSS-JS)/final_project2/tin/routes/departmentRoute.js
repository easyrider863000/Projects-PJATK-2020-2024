const express = require('express');
const router = express.Router();

const departmentController = require('../controllers/departmentController');

router.get('/',departmentController.showDepartmentList);
router.get('/add',departmentController.showAddDepartmentForm);
router.get('/edit/:depId',departmentController.showEditDepartmentForm);
router.get('/details/:depId',departmentController.showDepartmentDetails);

router.post('/add',departmentController.addDepartment);
router.post('/edit',departmentController.updateDepartment);
router.get('/delete/:depId',departmentController.deleteDepartment);

module.exports = router;