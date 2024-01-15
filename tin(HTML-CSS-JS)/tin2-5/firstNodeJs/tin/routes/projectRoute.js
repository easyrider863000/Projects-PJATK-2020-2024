const express = require('express');
const router = express.Router();

const projectController = require('../controllers/projectController');

router.get('/',projectController.showProjectList);
router.get('/add',projectController.showAddProjectForm);
router.get('/edit/:prjId',projectController.showEditProjectForm);
router.get('/details/:prjId',projectController.showProjectDetails);

router.post('/add',projectController.addProject);
router.post('/edit',projectController.updateProject);
router.get('/delete/:prjId',projectController.deleteProject);

module.exports = router;