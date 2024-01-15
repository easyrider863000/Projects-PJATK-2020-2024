const express = require('express');
const router = express.Router();

const prjApiController = require('../../api/ProjectAPI');

router.get('/', prjApiController.getProjects);
router.get('/:prjId', prjApiController.getProjectById);
router.post('/', prjApiController.createProject);
router.put('/:prjId', prjApiController.updateProject);
router.delete('/:prjId', prjApiController.deleteProject);

module.exports = router;